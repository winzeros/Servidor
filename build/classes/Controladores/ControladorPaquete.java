/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;



import Eventos.EventoBD;
import Eventos.EventoConexionDesconexion;
import Eventos.EventoPaquete;
import Interfaces.EscuchadorEventoPaquete;
import Protocolo.Instruccion;
import Protocolo.MensajeBD;
import Protocolo.Paquete;
import Util.ListaClientes;
import Util.ListaEscuchadoresBD;
import Util.ListaEscuchadoresConexionDesconexion;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    
    public String getNombreClase(Object clase){
        String nomCompleto=clase.getClass().getName();
        int posPunto=nomCompleto.indexOf('.');        
        return nomCompleto.substring(posPunto+1);
    }

    @Override
    public void alRecibir(EventoPaquete e) {
         
         Paquete pk = e.Dato;
        
        System.out.println("-------EVENTO---------");
        switch (pk.getInstruccion()) {
            case Instruccion.REGISTRARSE: {
                Propietario us = gson.fromJson(pk.getData(), Propietario.class);
             try {
                 ListaClientes.RegistrarUsuario(pk.getOrigen(), us.getNombreUsuario());
                 
                 List<Object> lista = new ArrayList<Object>();
                 lista.add(us);
                 
                 MensajeBD msjbd=new MensajeBD(pk.getOrigen(),"ninguno", getNombreClase(us), lista, "guardar");
                 ListaEscuchadoresBD.DispararOnMensajeBDGuardar(new EventoBD(this, msjbd));
                 
                 System.out.println("SE REGISTRO CORRECTAMENTE..");
                 
             } catch (IOException ex) {
                 Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
             }
                
                
                break;
            }
           
            
            case Instruccion.LOGIN: {
              
                Propietario us = gson.fromJson(pk.getData(), Propietario.class);
             try {
                 ListaClientes.RegistrarUsuario(pk.getOrigen(), us.getNombreUsuario());
                 
                 List<Object> lista = new ArrayList<Object>();
                 lista.add(us);
                 
                 MensajeBD msjbd=new MensajeBD(pk.getOrigen(),"ninguno", getNombreClase(us), lista, "login");
                 ListaEscuchadoresBD.DispararOnMensajeBDConsulta(new EventoBD(this, msjbd));
                 
             } catch (IOException ex) {
                 Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
             }
                
                System.out.println("EL USUARIO SE ESTA LOGUEANDO");
                break;
            }
             
               
                 case Instruccion.CERRAR: {
                     ListaEscuchadoresConexionDesconexion.DispararOnDesconectar(new EventoConexionDesconexion(this, ListaClientes.getCliente(pk.getOrigen())));
                break;
            }
            case Instruccion.CHAT: {
                //if (server.mclientes.existeUsuraio(pk.getOrigen())) {
                server.server.obtenerArea().append("Se envio a "+pk.getDestino()+" :  "+pk.getData().toString());
                server.enviarN(pk.getDestino(), pk.getData().toString());
                break;
            }
            case Instruccion.GUARDAR: {
                List<Object> us = gson.fromJson(pk.getData(), ArrayList.class);
             try {
                 
                 //ListaClientes.RegistrarUsuario(pk.getOrigen(), us.getNombreUsuario());                 
                 
                 List<Object> lista = new ArrayList<Object>();                 
                 lista=us;
                 
                 MensajeBD msjbd=new MensajeBD(pk.getOrigen(),pk.getUsuario(), getNombreClase(us), lista, "guardar");
                 ListaEscuchadoresBD.DispararOnMensajeBDGuardar(new EventoBD(this, msjbd));
                 
                 System.out.println("SE GUARDO CORRECTAMENTE LAS TABLAS..");
                 
             } catch (Exception ex) {
                 Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
             }
             
                break;
            }
            
            case Instruccion.MODIFICAR: {
                List<Object> us = gson.fromJson(pk.getData(), ArrayList.class);
             try {
                 
                 //ListaClientes.RegistrarUsuario(pk.getOrigen(), us.getNombreUsuario());                 
                 
                 List<Object> lista = new ArrayList<Object>();                 
                 lista=us;
                 
                 MensajeBD msjbd=new MensajeBD(pk.getOrigen(),pk.getUsuario(), getNombreClase(us), lista, "modificar");
                 ListaEscuchadoresBD.DispararOnMensajeBDModificar(new EventoBD(this, msjbd));
                 
                 System.out.println("SE ESTAN MODIFICANDO LAS TABLAS..");
                 
             } catch (Exception ex) {
                 Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
             }
             
                break;
            }
                
            case Instruccion.ELIMINAR: {
                List<Object> us = gson.fromJson(pk.getData(), ArrayList.class);
             try {
                 
                 //ListaClientes.RegistrarUsuario(pk.getOrigen(), us.getNombreUsuario());                 
                 
                 List<Object> lista = new ArrayList<Object>();                 
                 lista=us;
                 
                 MensajeBD msjbd=new MensajeBD(pk.getOrigen(),pk.getUsuario(), getNombreClase(us), lista, "eliminar");
                 ListaEscuchadoresBD.DispararOnMensajeBDGuardar(new EventoBD(this, msjbd));
                 
                 System.out.println("SE ESTAN ELIMINANDO LAS TUPLAS..");
                 
             } catch (Exception ex) {
                 Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
             }
             
                break;
            }
             
            case Instruccion.CONSULTA: {
                List<Object> us = gson.fromJson(pk.getData(), ArrayList.class);
             try {
                 
                 //ListaClientes.RegistrarUsuario(pk.getOrigen(), us.getNombreUsuario());                 
                 
                 List<Object> lista = new ArrayList<Object>();                 
                 lista=us;
                 
                 MensajeBD msjbd=new MensajeBD(pk.getOrigen(),pk.getUsuario(), getNombreClase(us), lista, "consulta");
                 msjbd.setConsulta(lista.get(0).toString());
                 ListaEscuchadoresBD.DispararOnMensajeBDGuardar(new EventoBD(this, msjbd));
                 
                 System.out.println("SE ESTA PROCESANDO SU CONSULTA..");
                 
             } catch (Exception ex) {
                 Logger.getLogger(ControladorPaquete.class.getName()).log(Level.SEVERE, null, ex);
             }
             
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