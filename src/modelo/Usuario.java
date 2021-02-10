/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 * Clase que contiene la información del usuario
 * 
 * @author Sebastian
 * @version 1.0
 * @since 13 nov 2020
 */
public class Usuario {
    
    /**
     * Variable que contiene el nombre de usuario
     */
    private String nomusuario;
    
    /**
     * Variable que contiene la clave de usuario
     */
    private String clave;
    
    /**
     * Variable que contiene la edad del usuario
     */
    private LocalDate edad;
    
    /**
     * Variable que contiene el nombre del usuario
     */
    private String nombre;
    
    /**
     * Variable que contiene el apellido del usuario
     */
    private String apellido;

    /**
     * Constructor de la clase objeto
     * 
     * @param nomusuario nombre que usará el usuario para identificarse
     * @param clave clave del usuario
     */
    public Usuario(String nomusuario, String clave) {
        this.nomusuario = nomusuario;
        this.clave = clave;
    }

    /**
     * Método que retorna el nombre que usa el usuario para identificarse
     * 
     * @return nombre de usuario
     */
    public String getNomusuario() {
        return nomusuario;
    }

    /**
     * Método que establece el nombre que usa el usuario para identificarse
     * 
     * @param nomusuario nombre de usuario
     */
    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    /**
     * Método que retorna la edad del usuario
     * 
     * @return edad del usuario
     */
    public LocalDate getEdad() {
        return edad;
    }

    /**
     * Método que establece la edad del usuario
     * 
     * @param edad edad del usuario
     */
    public void setEdad(LocalDate edad) {
        this.edad = edad;
    }

    /**
     * Método que retorna el nombre del usuario
     * 
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del usuario
     * 
     * @param nombre nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que retorna el apellido del usuario
     * 
     * @return apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que establece el apellido del usuario
     * 
     * @param apellido apellido del usuario
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Método que retorna la calve de acceso del usuario
     * 
     * @return clave de acceso del usuario
     */
    public String getClave() {
        return clave;
    }

    /**
     * Método que establece la clave de acceso del usuario
     * 
     * @param clave clave de acceso del usuario
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Método que detecta si una clave pasada por parámetro es igual a la que 
     * tiene el usuario
     * 
     * @param cl clave a comprobar
     * @return verdadero si es igual y falso si no lo es
     */
    public boolean datosLoginValidos(String cl) {
        if(cl.equals(clave))
            return true;
        return false;
    }
    
    public boolean cambiaClave(String clActual,String clNueva, String clRepetir){
        if(clActual.equals(clave) && clNueva.equals(clRepetir)){
            clave = clNueva;
            return true;
        }
        return false;
    }
    
}
