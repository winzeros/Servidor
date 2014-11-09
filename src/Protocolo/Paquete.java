/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocolo;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 *
 * @author ERWIN
 */
public class Paquete implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5155364264919600611L;
    /**
     *
     */

    private String Origen;
    private String Destino;
    private Integer instruccion;
    //  private byte tipo;
    private String Data;

    public Paquete(String IPDestino, String origen, int instruccion, String data) {
        this.Destino = IPDestino;
        this.Data = data;
        this.instruccion = instruccion;
        this.Origen = origen;
    }

    public Paquete(String IPDestino, String data) {
        this.Destino = IPDestino;
        this.Data = data;
    }

    public void setDestino(String nDes) {
        this.setDestino(nDes);
    }

    public String getDestino() {
        return Destino;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public void setInstruccion(int instruccion) {
        this.instruccion = instruccion;
    }

    public int getInstruccion() {
        return instruccion;
    }

    public String getOrigen() {
        return Origen;
    }

    public static void main(String[] args) {
        String S = "hora&de&avent&ura";

        StringTokenizer st = new StringTokenizer(S, "&");

        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }

        String SS = ":hora:de:avent:ura";
        String[] neo = SS.split(":");
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

}
