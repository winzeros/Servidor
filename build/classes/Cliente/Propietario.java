/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ERWIN
 */
public class Propietario implements Serializable{
    
    private static final long serialVersionUID = -5155364264919600611L;
    
     private String correo;
     private String nombreCompleto;
     private String nombreUsuario;
     private String contrasena;
     private String fechaCreacion;
     private String fechaActualizacion;

    public Propietario(String correo, String nombreCompleto, String nombreUsuario, String contrasena, String fechaCreacion, String fechaActualizacion) {
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombreClase(Object clase){
        String nomCompleto=clase.getClass().getName();
        int posPunto=nomCompleto.indexOf('.');        
        return nomCompleto.substring(posPunto+1);
    
    }
     public static void main(String[] Args){
     //System.out.println(new Date(2014, 11, 9).toString());
         Propietario p=new Propietario(null, null, null, null, null, null);
         Object p1=p;
         System.out.println(p.getNombreClase(p1));
     }
     
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaActualizacion
     */
    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
     
}
