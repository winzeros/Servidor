/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Eventos.EventoConexionDesconexion;
import Util.Cliente;
import Util.ListaEscuchadoresConexionDesconexion;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ERWIN
 */
public class ReceptorConexion extends Thread{
    private Servidor servidor;
   
    public ReceptorConexion(Servidor server) {
        this.servidor = server;
       
    }
    
    @Override
    public void run() {
        while(true){
            try {
              
                System.out.println("Esperando clientes "+"\n");
                servidor.server.obtenerArea().append("Esperando clientes "+"\n");
                
                Socket cliente = servidor.getServidor().accept();
             
                 System.out.println("Se conecto el cliente "+ cliente.getPort()+"\n");
                  servidor.server.obtenerArea().append("Se conecto el cliente "+ cliente.getPort()+"\n");
                 Cliente conexion = new Cliente(cliente, null);
                 
                 //servidor.DispararOnConexion(new ConexionEvento(this, conexion));
                 
                 
                 ListaEscuchadoresConexionDesconexion.DispararOnConexion(new EventoConexionDesconexion(this, conexion));
                 Conector escuchador =  new Conector(cliente,servidor);
                 escuchador.start();
                 
                 
                 
  
                 
                
            } catch (IOException ex) {
                Logger.getLogger(ReceptorConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    
}
