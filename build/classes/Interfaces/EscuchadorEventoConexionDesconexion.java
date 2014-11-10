/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Eventos.EventoConexionDesconexion;
import java.util.EventListener;

/**
 *
 * @author ERWIN
 */
public interface EscuchadorEventoConexionDesconexion extends EventListener{
     public void alConecta(EventoConexionDesconexion e); 
     public void alDesconectar(EventoConexionDesconexion e); 
}
