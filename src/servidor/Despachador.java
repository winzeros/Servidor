/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Protocolo.Paquete;
import Util.*;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ERWIN
 */
public class Despachador {

    private Gson mGson;
  //  private ObjectOutputStream salida;
  

    public Despachador() {

        mGson = new Gson();
    }

   

   /* public static void Enviar() {
        try {

            String mensaje = "BIENBENIDO AL SERVIDOR...";
            salida.writeObject(mensaje);

            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(ssdespachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public static void enviarTodos(String dato) throws IOException {
        Gson mGson = new Gson();
        Collection<Cliente> values = ListaClientes.getClientes();

        Iterator<Cliente> iterator = values.iterator();

        while (iterator.hasNext()) {

            Cliente socket = iterator.next();
            ObjectOutputStream out = new ObjectOutputStream(socket.getConexion().getOutputStream());
            //  out.flush();
            try {
                Paquete pk = new Paquete("", String.valueOf(socket.getConexion().getPort()), 3, dato);
                String jsonpk = mGson.toJson(pk);
                out.writeObject(jsonpk);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public  static void enviarN(String Nick, String dato) {
         Gson mGson = new Gson();
        System.out.println("existe: " + Nick);
        Cliente cliente = ListaClientes.getUsuario(Nick);
        if (cliente != null) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(cliente.getConexion().getOutputStream());
                Paquete pk = new Paquete("", String.valueOf(cliente.getConexion().getPort()), 3, dato);
                String jsonpk = mGson.toJson(pk);
                out.writeObject(jsonpk);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void enviaFichero(String fichero, String nick) {
        /*try {
             Gson mGson = new Gson();
            Cliente cliente = ListaClientes.getUsuario(nick);
            if (cliente != null) {
                try {
                    ObjectOutputStream out = new ObjectOutputStream(cliente.getConexion().getOutputStream());
                  

                    boolean enviadoUltimo = false;
                    // Se abre el fichero.
                    File file= new  File(fichero);
                    FileInputStream fis = new FileInputStream(file);
                    

                    // Se instancia y rellena un mensaje de envio de fichero
                    Cabesera mensaje = new Cabesera();
                    mensaje.nombreFichero = fichero;
                    
                    // Se leen los primeros bytes del fichero en un campo del mensaje
                    int leidos = fis.read(mensaje.contenidoFichero);
                     byte fileContent[] = new byte[(int) file.length()];
                    System.out.println("TAMANO "+ fileContent.length);
                    // Bucle mientras se vayan leyendo datos del fichero
                    while (leidos > -1) {

                        // Se rellena el número de bytes leidos
                        mensaje.bytesValidos = leidos;

                // Si no se han leido el máximo de bytes, es porque el fichero
                        // se ha acabado y este es el último mensaje
                        if (leidos < Cabesera.LONGITUD_MAXIMA) {
                            mensaje.ultimoMensaje = true;
                            enviadoUltimo = true;
                        } else {
                            mensaje.ultimoMensaje = false;
                        }

                        // Se envía por el socket
                        String dato = mGson.toJson(mensaje);
                        Paquete pk = new Paquete("", String.valueOf(cliente.getConexion().getPort()), Protocolo.ENVIAR_ARCHIVO, dato);
                        String jsonpk = mGson.toJson(pk);
                        out.writeObject(jsonpk);

                        // Si es el último mensaje, salimos del bucle.
                        if (mensaje.ultimoMensaje) {
                            break;
                        }

                        // Se crea un nuevo mensaje
                        mensaje = new Cabesera();
                        mensaje.nombreFichero = fichero;
                        // y se leen sus bytes.
                        leidos = fis.read(mensaje.contenidoFichero);
                    }

                    if (enviadoUltimo == false) {
                        mensaje.ultimoMensaje = true;
                        mensaje.bytesValidos = 0;
                         String dato = mGson.toJson(mensaje);
                       Paquete pk = new Paquete("", String.valueOf(cliente.getConexion().getPort()), 3, dato);
                        String jsonpk = mGson.toJson(pk);
                        out.writeObject(mensaje);
                    }
               
                 
                } catch (IOException ex) {
                    Logger.getLogger(SServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                 System.out.println("ENVIO DE ARCHVIO TERMINADO");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
