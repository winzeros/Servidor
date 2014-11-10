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
public interface EscuchadorEventoPaqueteBD extends EventListener{
    public void alRecibirPaqueteBD(EventoBD e); 
}
