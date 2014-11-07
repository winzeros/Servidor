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
public class MiEvento extends EventObject{
   public Paquete Dato; 

   //solo para resivir un paquete simple 
   public MiEvento(Object source,Paquete p) {
        super(source);
        Dato=p;
    }   
    
        
}
