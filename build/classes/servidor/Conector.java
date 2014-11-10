/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Eventos.EventoConexionDesconexion;
import Eventos.EventoPaquete;
import Interfaces.EscuchadorEventoPaquete;
import Protocolo.Paquete;
import Util.ListaClientes;
import Util.ListaEscuchadoresConexionDesconexion;
import Util.ListaEscuchadoresPaquete;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ERWIN
 */
public class Conector extends Thread{
        private ObjectInputStream entrada;
    private Socket conexion;
    private Servidor server;
    private boolean conectado;
    private Gson mGson;

    public Conector(Socket cliente, Servidor servidor) {
        this.conectado = true;
        this.conexion = cliente;
        this.server = servidor;
        mGson = new Gson();
    }

    private void MostrarPaquete(Paquete pk) {
        System.out.println("Origen  >" + pk.getOrigen());
        System.out.println("Instruccion  >" + pk.getInstruccion());
        System.out.println("Data  >" + pk.getData());

    }

    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(conexion.getInputStream());
            while (conectado) {
                try {

                    if (entrada != null) {
                        Object sc = entrada.readObject();
                        if (sc instanceof String) {
                            String paquete = (String) sc;//entrada.readObject();
                            Paquete pk = mGson.fromJson(paquete, Paquete.class);
                            pk.setOrigen(String.valueOf(conexion.getPort()));

                            MostrarPaquete(pk);

                            ListaEscuchadoresPaquete.DispatchOnReceive(new EventoPaquete(this, pk));
                        }
                    }
                } catch (IOException ex) {

                    System.out.println("desconectado cliente");
                    ListaEscuchadoresConexionDesconexion.DispararOnDesconectar(new EventoConexionDesconexion(this, ListaClientes.getCliente("" + conexion.getPort())));
                    // System.out.println(ManejadorClientes.getCliente(""+conexion.getPort()));
                    conectado = !conectado;
                    entrada.close();
                    //Logger.getLogger(ssescuchador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                conectado = false;
                //        server.delCliente( String.valueOf(  conexion.getPort() ) );
                if (entrada != null) {
                    entrada.close();
                }
                //    out.close();
                conexion.close();
                System.out.println("Terminado");
            } catch (IOException ioe) {

            }
        }
    }


}
     /*
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

    } */
