/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Eventos.MiEvento;
import java.util.EventListener;

/**
 *
 * @author ERWIN
 */
public interface EscuchadorEvento extends EventListener{
   public void OnReceive(MiEvento e); 
   public void AlConectar(MiEvento e); 
   public void AlDesconectar(MiEvento e); 
   public void AlEscribir(MiEvento e); 
   public void AlLeer(MiEvento e); 
}
