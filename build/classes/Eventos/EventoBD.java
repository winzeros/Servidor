/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import Protocolo.MensajeBD;
import java.util.EventObject;

/**
 *
 * @author ERWIN
 */
public class EventoBD extends EventObject{
    public MensajeBD mensajeBD;
    public EventoBD(Object source,MensajeBD mensajeBD) {
        super(source);
        this.mensajeBD=mensajeBD;
    }
    
}
