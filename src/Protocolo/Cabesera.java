/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocolo;

import java.io.Serializable;

/**
 *
 * @author Rafael
 */
public class Cabesera implements Serializable{
    /** Nombre del fichero que se transmite. Por defecto "" */
    public String nombreFichero="";

    /** Si este es el último mensaje del fichero en cuestión o hay más después */
    public boolean ultimoMensaje=true;

    /** Cuantos bytes son válidos en el array de bytes */
    public int bytesValidos=0;

    /** Array con bytes leidos del fichero */
    public byte[] contenidoFichero = new byte[LONGITUD_MAXIMA];
    
    /** Número máximo de bytes que se enviaán en cada mensaje */
    public final static int LONGITUD_MAXIMA=1024;
    
}
