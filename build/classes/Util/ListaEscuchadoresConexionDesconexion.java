/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Eventos.EventoConexionDesconexion;
import Interfaces.EscuchadorEventoConexionDesconexion;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ERWIN
 */
public class ListaEscuchadoresConexionDesconexion {
   private static final EventListenerList ListaConexiones;
               
     static{
         ListaConexiones = new EventListenerList();
     
     }
     
    public static  void addConexiones(EscuchadorEventoConexionDesconexion l) {
        ListaConexiones.add(EscuchadorEventoConexionDesconexion.class, l);
    }
     

    public static void removerConexiones(EscuchadorEventoConexionDesconexion l) {
        ListaConexiones.remove(EscuchadorEventoConexionDesconexion.class, l);
    } 
    
    
    
    
    public static void DispararOnConexion(EventoConexionDesconexion e) {
        EscuchadorEventoConexionDesconexion[] ls = ListaConexiones.getListeners(EscuchadorEventoConexionDesconexion.class);
        for (EscuchadorEventoConexionDesconexion l : ls) {
            l.alConecta(e);
        }
    }
    
    
     public static void DispararOnDesconectar(EventoConexionDesconexion e) {
        EscuchadorEventoConexionDesconexion[] ls = ListaConexiones.getListeners(EscuchadorEventoConexionDesconexion.class);
        for (EscuchadorEventoConexionDesconexion l : ls) {
            l.alDesconectar(e);
        }
    }
}
