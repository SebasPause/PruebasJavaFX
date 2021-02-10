/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sebastian
 */
public class PersTmp {

    private static PersTmp instancia = null;
    private HashMap<String,Usuario> usuarios;
    private Usuario usActual;

    private PersTmp() {
        usuarios = new HashMap();
    }

    public static PersTmp getInstance() {
        if (instancia == null) {
            instancia = new PersTmp();
        }
        return instancia;
    }

    public HashMap<String,Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String,Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public Usuario getUsuarioLogueado(){
        return usActual;
    }
    
    public boolean anadirUsuario(String us, String pw1, String pw2){
        if(!usuarios.containsKey(us) && pw1.equals(pw2)){
            usuarios.put(us,new Usuario(us,pw1));
            return true;
        }
        return false;
    }
    
    public boolean login(String us, String cl){
        if (usuarios.containsKey(us) && usuarios.get(us).datosLoginValidos(cl)){
            usActual = usuarios.get(us);
            return true;
        }
        return false;
    }
    
    public boolean usuarioDisponible(String usuario){
        return usuarios.containsKey(usuario);
    }

}
