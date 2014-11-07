/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Protocolo.Paquete;
import Eventos.MiEvento;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ERWIN
 */
public class Conector extends Thread{
     
        Socket clienteSocket;        
        boolean Continue = true;
        
        
        Conector(Socket s)
        {
            clienteSocket = s;
            
        }
       
        public void run()
        {            
            DataInputStream in = null; 
            DataOutputStream out = null;
 
            try
            {                                
                in = new DataInputStream(clienteSocket.getInputStream());
                out = new DataOutputStream(clienteSocket.getOutputStream());
                
                while(Continue)
                {                    
                    String clienteCommando = in.readUTF();                                     
                    Servidor.DispatchOnReceive(new MiEvento(this,new Paquete(String.valueOf(clienteSocket.getLocalPort()),clienteCommando)));
                 
                    System.out.println(clienteCommando);
                    if(clienteCommando.equalsIgnoreCase("Salir"))
                    {
                         Continue = false;   
                     }
                    else
                    {
                       
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            finally
            {
                
                try
                {                    
                    in.close();
                    out.close();
                    clienteSocket.close();
                    System.out.println("Terminado");
                }
                catch(IOException ioe)
                {
                    
                }
            }
        }

    } 
