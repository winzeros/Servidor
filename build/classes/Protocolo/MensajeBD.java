/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocolo;

import java.util.List;

/**
 *
 * @author ERWIN
 */
public class MensajeBD {
 private String keyPort;   
 private String idUsuario;
 private String nombreTabla;
 private List<Object> ListaObjetos;
 private String consulta;
 private String tipo;

    public MensajeBD(String keyPort, String idUsuario, String nombreTabla, List<Object> ListaObjetos, String tipo) {
        this.keyPort = keyPort;
        this.idUsuario = idUsuario;
        this.nombreTabla = nombreTabla;
        this.ListaObjetos = ListaObjetos;
        this.consulta = consulta;
        this.tipo = tipo;
    }

    public MensajeBD(String idUsuario, String nombreTabla, List<Object> ListaObjetos, String tipo) {
        this.idUsuario = idUsuario;
        this.nombreTabla = nombreTabla;
        this.ListaObjetos = ListaObjetos;
        this.tipo = tipo;
    }

    public MensajeBD(String idUsuario, String consulta, String tipo) {
        this.idUsuario = idUsuario;
        this.consulta = consulta;
        this.tipo = tipo;
    }

   

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreTabla
     */
    public String getNombreTabla() {
        return nombreTabla;
    }

    /**
     * @param nombreTabla the nombreTabla to set
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    /**
     * @return the ListaObjetos
     */
    public List<Object> getListaObjetos() {
        return ListaObjetos;
    }

    /**
     * @param ListaObjetos the ListaObjetos to set
     */
    public void setListaObjetos(List<Object> ListaObjetos) {
        this.ListaObjetos = ListaObjetos;
    }

    /**
     * @return the consulta
     */
    public String getConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the keyPort
     */
    public String getKeyPort() {
        return keyPort;
    }

    /**
     * @param keyPort the keyPort to set
     */
    public void setKeyPort(String keyPort) {
        this.keyPort = keyPort;
    }
 
}
