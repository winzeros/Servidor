/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author ERWIN
 */
public class Paquete {
    private String IP;
    private String Dato;

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
