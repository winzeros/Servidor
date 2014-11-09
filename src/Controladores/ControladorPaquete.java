/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;



import Eventos.EventoConexionDesconexion;
import Eventos.EventoPaquete;
import Interfaces.EscuchadorEventoPaquete;
import Protocolo.Instruccion;
import Protocolo.Paquete;
import Util.ListaClientes;
import Util.ListaEscuchadoresConexionDesconexion;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Servidor;

/**
 *
 * @author ERWIN
 */
public class ControladorPaquete implements EscuchadorEventoPaquete {
    
    private Servidor server;
    //private ControlDatos cdatos;
    private Gson gson=new Gson();
    
    
    public ControladorPaquete(Servidor servidor) { 
        this.server = servidor;
        //cdatos = new ControlDatos();
        gson = new Gson();
    }      

    @Override
    public void alRecibir(EventoPaquete e) {
         
         Paquete pk = e.Dato;
        
        System.out.println("-------EVENTO---------");
        switch (pk.getInstruccion()) {
            case Instruccion.REGISTRARSE: {
             //   Usuario us = mGson.fromJson(pk.getData(), Usuario.class);
                //  Object u = pk.getData();
            //    cdatos.guardarUsuario(us);
                System.out.println("SE REGISTRO CORRECTAMENTE..");
                break;
            }
            case Instruccion.MENSAJES_NUEVOS: {
                break;
            }
            case Instruccion.CERRAR: {
                //if (server.mclientes.existeUsuraio(pk.getOrigen())) {

                ListaEscuchadoresConexionDesconexion.DispararOnDesconectar(new EventoConexionDesconexion(this, ListaClientes.getCliente(pk.getOrigen())));
                break;
            }
            case Instruccion.CHAT: {
                server.enviarN(pk.getDestino(), pk.getData().toString());
                break;
            }
             case Instruccion.ENVIAR_ARCHIVO: {
                 System.out.println(pk.getData().toString());
               // server.enviarN(pk.getDestino(), pk.getData().toString());
                 System.out.println(pk.getOrigen());
                 
                 System.out.println(ListaClientes.getCliente(pk.getOrigen()).getUsuario());
                 
                 //ssdespachador.enviaFichero( pk.getData().toString(),ListaClientes.getCliente(pk.getOrigen()).getNick());
                break;
            }
            
            case Instruccion.LOGIN: {
                try {
                    ListaClientes.RegistrarUsuario(pk.getOrigen(), (String) pk.getData());
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

        }

     
        System.out.println("---------EVENTO-------------");
        
        /*try {
            if (e.Dato.getDato().startsWith("Sumar")){
                String[] Param = e.Dato.getDato().split(",");
                int a = Integer.parseInt(Param[1]);
                int b = Integer.parseInt(Param[2]);
                int r = a+b;
                Servidor.enviar(e.Dato.getIP(),String.valueOf(r));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        
    }

    @Override
    public void alEnviar(EventoPaquete e) {
     

    }
   
    

}