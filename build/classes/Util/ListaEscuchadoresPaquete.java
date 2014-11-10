/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Eventos.EventoPaquete;
import Interfaces.EscuchadorEventoPaquete;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ERWIN
 */
public class ListaEscuchadoresPaquete {
  private static final EventListenerList ListaEscuchadores;

    static {
        ListaEscuchadores = new EventListenerList();

    }

    public static void addReceiveListener(EscuchadorEventoPaquete l) {
        ListaEscuchadores.add(EscuchadorEventoPaquete.class, l);
    }

    public static void removeReceiveListener(EscuchadorEventoPaquete l) {
        ListaEscuchadores.remove(EscuchadorEventoPaquete.class, l);
    }
     public static void DispatchOnReceive(EventoPaquete e) {
        EscuchadorEventoPaquete[] ls = ListaEscuchadores.getListeners(EscuchadorEventoPaquete.class);
        for (EscuchadorEventoPaquete l : ls) {
            l.alRecibir(e);
                        
        }
    }
  
}
