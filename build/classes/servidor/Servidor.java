/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Controladores.Controlador;
import Interfaces.EscuchadorEvento;
import Eventos.MiEvento;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ERWIN
 */
public class Servidor extends Thread{
private ServerSocket ServidorSocket=null;
    static EventListenerList ListaEscuchadores=new EventListenerList();
    static HashMap<String, Socket> clientes;
    private Controlador controlador=null;
     Socket clienteSocket=null;
    
    public Servidor(int Puerto) 
    {
        try
        {
            ServidorSocket = new ServerSocket(Puerto);
            clientes = new HashMap<String, Socket>();
            controlador = new Controlador();
            this.adicionarEscuchador(controlador);
        }
        catch(IOException ioe)
        {
            System.out.println("No se pudo abrir el socket");
            System.exit(-1);
        }catch(Exception e){
            System.out.println("Se ha producido la sgte excepcion "+ e);
        }
        
        System.out.println("Esperando clientes...");
                
        
    }
    
    void adicionarEscuchador(EscuchadorEvento l) {
        ListaEscuchadores.add(EscuchadorEvento.class, l);
    }

    void elimnarEscuchador(EscuchadorEvento l) {
         ListaEscuchadores.remove(EscuchadorEvento.class, l);
    }

    static protected void DispatchOnReceive(MiEvento e) {
     EscuchadorEvento[] ls = ListaEscuchadores.getListeners(EscuchadorEvento.class);
     for (EscuchadorEvento l : ls) {
      l.OnReceive(e);      
     }

    }
    
    public static void enviar(String cliente, String dato) throws IOException{
        if(clientes.containsKey(cliente)){
            Socket socket = clientes.get(cliente);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            try {
                out.writeUTF(dato);
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void enviarTodos(String dato) throws IOException{
        Collection<Socket> values = clientes.values();
        Iterator<Socket> iterator = values.iterator();
        
        while(iterator.hasNext()){
            Socket socket = iterator.next();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            try {
                out.writeUTF(dato);
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
       while(true)
        {                        
            try
            {
                clienteSocket = ServidorSocket.accept();
                Conector hilo = new Conector(clienteSocket);
                hilo.start();
                System.out.println("Se conecto");
                clientes.put(String.valueOf(clienteSocket.getLocalPort()),clienteSocket);
            }
            catch(IOException ioe)
            {
                
            }
        } 
    }
    public static void main(String args[]){
        Servidor server = new Servidor(2050);
        server.start();
        
    }   
   
}