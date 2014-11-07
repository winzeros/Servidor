/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocolo;

/**
 *
 * @author ERWIN
 */
public class Paquete {
    public String IP;
    public String Dato;

    public Paquete(String IP,String Dato) {
        this.Dato = Dato;
        this.IP=IP;
    }

    public String getDato() {
        return Dato;
    }

    public void setDato(String Dato) {
        this.Dato = Dato;
    }
    
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
      
}
