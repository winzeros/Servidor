/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Controladores.ControladorConexion;
import Controladores.ControladorPaquete;
import Formularios.ServidorSocket;
import Util.ListaClientes;
import Util.ListaEscuchadoresConexionDesconexion;
import Util.ListaEscuchadoresPaquete;
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
public class Servidor {

    private ServerSocket servidor;
    private int puerto;
    private ReceptorConexion conexion;
    private Despachador despachador;
    public ServidorSocket server;

    private ControladorPaquete controlEscuchador;
    private ControladorConexion controlConexion;
    //private ControlDesconexion controlDesconexion;

    //private ControlDatos controlDatos;

    public Servidor(int puerto) {
        this.puerto = puerto;
        try {
            servidor = new ServerSocket(puerto);

            conexion = new ReceptorConexion(this);
           despachador = new Despachador();
            controlEscuchador = new ControladorPaquete(this);

            controlConexion = new ControladorConexion();
            

            ListaEscuchadoresPaquete.addReceiveListener(controlEscuchador);
            ListaEscuchadoresConexionDesconexion.addConexiones(controlConexion);
           

            System.out.println("Iniciando el servidor");

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Servidor(int puerto,ServidorSocket serv) {
        this.puerto = puerto;
        try {
            servidor = new ServerSocket(puerto);
            this.server = serv;//Interfaz grafica cmo parametro
            
            conexion = new ReceptorConexion(this);
            
            despachador = new Despachador();
            
            controlEscuchador = new ControladorPaquete(this);
            controlConexion = new ControladorConexion();
            

            ListaEscuchadoresPaquete.addReceiveListener(controlEscuchador);
            ListaEscuchadoresConexionDesconexion.addConexiones(controlConexion);
            

            
            System.out.println("Iniciando el servidor"+"\n");
            

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarServidor() {
        conexion.start();
    }

    public void Mostrar() {
        ListaClientes.MostrarClientes();
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public static void main(String[] args) {
        Servidor xx = new Servidor(9999);
        xx.iniciarServidor();

    }

    public void enviarN(String nick, String text0) {
        Despachador.enviarN(nick, text0);
    }

    public void enviarTodos(String text) {
        try {
            Despachador.enviarTodos(text);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enviarFichero(String path,String nick){
        Despachador.enviaFichero(path, nick);
    }

}







/*extends Thread{
private ServerSocket ServidorSocket=null;
    static EventListenerList ListaEscuchadores=new EventListenerList();
    static HashMap<String, Socket> clientes;
    private ControladorPaquete controlador=null;
     Socket clienteSocket=null;
    
    public Servidor(int Puerto) 
    {
        try
        {
            ServidorSocket = new ServerSocket(Puerto);
            clientes = new HashMap<String, Socket>();
            controlador = new ControladorPaquete();
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
   
}*/