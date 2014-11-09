/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.util.EventObject;
import Protocolo.Paquete;

/**
 *
 * @author ERWIN
 */
public class EventoPaquete extends EventObject{
   public Paquete Dato; 

   //solo para resivir un paquete simple 
   public EventoPaquete(Object source,Paquete p) {
        super(source);
        Dato=p;
    }   
    
        
}
