/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.EventObject;

/**
 *
 * @author ERWIN
 */
public class MiEvento extends EventObject{
   Paquete Dato; 

    public MiEvento(Object source,Paquete p) {
        super(source);
        Dato=p;
    }
        
}
