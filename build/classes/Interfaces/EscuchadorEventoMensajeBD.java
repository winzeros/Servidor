/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Eventos.EventoBD;
import java.util.EventListener;

/**
 *
 * @author ERWIN
 */
public interface EscuchadorEventoMensajeBD extends EventListener{
    public void alRecibirPaqueteGuardar(EventoBD e);
    public void alRecibirPaqueteModificar(EventoBD e);
    public void alRecibirPaqueteEliminar(EventoBD e);
    public void alRecibirPaqueteConsulta(EventoBD e);
    
}
