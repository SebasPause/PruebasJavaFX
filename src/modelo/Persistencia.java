package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistencia {

    private String dirDB;   //dir base de datos
    private static Persistencia sqLite; //implementa singleton
    private PersTmp m = PersTmp.getInstance();
    private Connection c;

    private Persistencia() {
        dirDB = "src/db/encuestas.db";
    }

    public static Persistencia getInstance() {
        if (sqLite == null) {
            sqLite = new Persistencia();
        }
        return sqLite;
    }

    private Connection conectar(String ruta) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private boolean execute(String sql) {
        try {
            Statement stmt = c.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ResultSet executeQuery(String sql) {
        ResultSet r;
        try {
            Statement stmt = c.createStatement();
            r = stmt.executeQuery(sql);
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    
    public void cargarBD(){
        c = conectar(dirDB);
        createTables();
        m.setUsuarios(selectUsuarios());
        try{
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private boolean createTables(){
        ArrayList<String> sql = new ArrayList();
        
        sql.add("CREATE TABLE IF NOT EXISTS Usuario ("+" Nick varchar(255) PRIMARY KEY,"+ " Nombre varchar(255),"
                + " Apellidos varchar(255)," + " Fecha_Nacimiento varchar(255),"+ " Contrasena varchar(255); )"
        );
        
        try{
            for(String i : sql){
                Statement stmt = c.createStatement();
                stmt.execute(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
        
    }
    
    
    public boolean anadeUsuario(Usuario us){
        c = conectar(dirDB);
        String sql;
        String fecha = "'null'";
        if(us.getEdad()!=null){
            fecha = "'" + Date.valueOf(us.getEdad().toString()) + "'";
        }
        
        sql = "INSERT INTO Usuario (" + "Nick,Nombre,Apellidos,Fecha_Nacimiento,Contrasena)"+" values ('"
                +us.getNomusuario()+"','"+us.getNombre()+"','"+us.getApellido()+"','"+fecha+"','"+us.getClave()+"');";
        execute(sql);
        try{
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
        
    }
    
    public boolean actualizaUsuario(Usuario us){
        c = conectar(dirDB);
        String sql;
        String fecha = "'null'";
        if(us.getEdad()!=null){
            fecha = "'" + Date.valueOf(us.getEdad().toString()) + "'";
        }
        
        sql = "UPDATE Usuario SET Nombre = '" +us.getNombre() + "',Apellidos = '"+us.getApellido() +
                "',Fecha_Nacimiento = "+fecha+",Contrasena = '"+us.getClave()+"' WHERE Nick = '"+us.getNomusuario()+"';";
        try{
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
        
    }
    
    private HashMap<String,Usuario> selectUsuarios(){
        String sql;
        Usuario us;
        String ni,n,ap,cl;
        Date d;
        LocalDate ld;
        ResultSet resul;
        HashMap<String,Usuario> lista = new HashMap();
        sql = "SELECT * FROM Usuario " + ";";
        resul = executeQuery(sql);
        try{
            while(resul.next()){
                ni = resul.getString("Nick");
                cl = resul.getString("Contrasena");
                us = new Usuario(ni,cl);
                
                n = resul.getString("Nombre");
                if(!n.equals("null")){
                    us.setNombre(n);
                }

                ap = resul.getString("Apellidos");
                if(!ap.equals("null")){
                    us.setApellido(ap);
                }
                
                if(!resul.getString("Fecha_Nacimiento").equals("null")){
                    d = Date.valueOf(resul.getString("Fecha_Nacimiento"));
                    us.setEdad(d.toLocalDate());
                }
                
                lista.put(ni, us);       
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    

}
