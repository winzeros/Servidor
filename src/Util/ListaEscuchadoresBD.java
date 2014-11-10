/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Eventos.EventoBD;
import Interfaces.EscuchadorEventoMensajeBD;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ERWIN
 */
public class ListaEscuchadoresBD {
private static final EventListenerList ListaEscuchadoresMensajeBD;

    static {
        ListaEscuchadoresMensajeBD = new EventListenerList();

    }

    public static void addReceiveListener(EscuchadorEventoMensajeBD l) {
        ListaEscuchadoresMensajeBD.add(EscuchadorEventoMensajeBD.class, l);
    }

    public static void removeReceiveListener(EscuchadorEventoMensajeBD l) {
        ListaEscuchadoresMensajeBD.remove(EscuchadorEventoMensajeBD.class, l);
    }
     public static void DispararOnMensajeBDGuardar(EventoBD e) {
        EscuchadorEventoMensajeBD[] ls = ListaEscuchadoresMensajeBD.getListeners(EscuchadorEventoMensajeBD.class);
        for (EscuchadorEventoMensajeBD l : ls) {
            l.alRecibirPaqueteGuardar(e);                        
        }
    }
  
     public static void DispararOnMensajeBDModificar(EventoBD e) {
        EscuchadorEventoMensajeBD[] ls = ListaEscuchadoresMensajeBD.getListeners(EscuchadorEventoMensajeBD.class);
        for (EscuchadorEventoMensajeBD l : ls) {
            l.alRecibirPaqueteModificar(e);                        
        }
    }
     
     public static void DispararOnMensajeBDEliminar(EventoBD e) {
        EscuchadorEventoMensajeBD[] ls = ListaEscuchadoresMensajeBD.getListeners(EscuchadorEventoMensajeBD.class);
        for (EscuchadorEventoMensajeBD l : ls) {
            l.alRecibirPaqueteEliminar(e);                        
        }
    }
     
     public static void DispararOnMensajeBDConsulta(EventoBD e) {
        EscuchadorEventoMensajeBD[] ls = ListaEscuchadoresMensajeBD.getListeners(EscuchadorEventoMensajeBD.class);
        for (EscuchadorEventoMensajeBD l : ls) {
            l.alRecibirPaqueteConsulta(e);                        
        }
    }
}
