/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.net.Socket;

/**
 *
 * @author ERWIN
 */
public class Cliente {
    private Socket conexion;
    private String usuario;

    public Cliente(Socket conexion, String usuario) {
        this.conexion = conexion;
        this.usuario = usuario;
    }

    public void setConexion(Socket conexion) {
        this.conexion = conexion;
    }

    public Socket getConexion() {
        return conexion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
         String key = "key :" + String.valueOf(conexion.getPort());
           key = key +"\n" +"usuario :" + getUsuario();
           return key;
    }
    
                    
}
