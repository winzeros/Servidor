/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Eventos.EventoBD;
import Interfaces.EscuchadorEventoPaqueteBD;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ERWIN
 */
public class ListaEscuchadoresMsjBD {
    private static final EventListenerList ListaEscuchadoresMensajeBD;

    static {
        ListaEscuchadoresMensajeBD = new EventListenerList();

    }

    public static void addReceiveListener(EscuchadorEventoPaqueteBD l) {
        ListaEscuchadoresMensajeBD.add(EscuchadorEventoPaqueteBD.class, l);
    }

    public static void removeReceiveListener(EscuchadorEventoPaqueteBD l) {
        ListaEscuchadoresMensajeBD.remove(EscuchadorEventoPaqueteBD.class, l);
    }
     public static void DispararOnRecibirPaqueteBD(EventoBD e) {
        EscuchadorEventoPaqueteBD[] ls = ListaEscuchadoresMensajeBD.getListeners(EscuchadorEventoPaqueteBD.class);
        for (EscuchadorEventoPaqueteBD l : ls) {
            l.alRecibirPaqueteBD(e);                        
        }
    }
  
}
