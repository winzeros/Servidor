/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import Util.Cliente;
import java.util.EventObject;

/**
 *
 * @author ERWIN
 */
public class EventoConexionDesconexion extends EventObject{
   public Cliente cliente;

    public EventoConexionDesconexion(Object source,Cliente cliente) {
        super(source);
        this.cliente = cliente;
    } 
}
