/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Eventos.EventoPaquete;
import java.util.EventListener;

/**
 *
 * @author ERWIN
 */
public interface EscuchadorEventoPaquete extends EventListener{
   public void alRecibir(EventoPaquete e); 
   public void alEnviar(EventoPaquete e);    
}
