/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Interfaces.EscuchadorEvento;
import Eventos.MiEvento;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Servidor;

/**
 *
 * @author ERWIN
 */
public class Controlador implements EscuchadorEvento {
    
    private Gson gson=new Gson();
    
    public Controlador() {        
    }
    
    @Override
    public void OnReceive(MiEvento e) {
         try {
            if (e.Dato.getDato().startsWith("Sumar")){
                String[] Param = e.Dato.getDato().split(",");
                int a = Integer.parseInt(Param[1]);
                int b = Integer.parseInt(Param[2]);
                int r = a+b;
                Servidor.enviar(e.Dato.getIP(),String.valueOf(r));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void AlConectar(MiEvento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AlDesconectar(MiEvento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AlEscribir(MiEvento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AlLeer(MiEvento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
