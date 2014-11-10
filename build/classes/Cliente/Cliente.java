/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import Protocolo.Cabesera;
import Protocolo.Instruccion;
import Protocolo.Paquete;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import servidor.Despachador;


/**
 *
 * @author ConstructoraSalgado
 */
public class Cliente extends Thread {

    private Socket cli;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String nick;
    private boolean conectado;
    private Gson mGson;
    private IUCliente vista;

    public Cliente(String ip, int puerto, IUCliente vista) {
        try {
            cli = new Socket(ip, puerto);
            salida = new ObjectOutputStream(cli.getOutputStream());
            conectado = true;
            mGson = new Gson();
            this.vista = vista;

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente(String ip, int puerto) {
        try {
            cli = new Socket(ip, puerto);
            salida = new ObjectOutputStream(cli.getOutputStream());
            conectado = true;
            mGson = new Gson();
            this.vista = null;

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente(Socket clie) {

        cli = clie;

    }

    @Override
    public void run() {
        try {
            
            while (conectado) {
                try {
                    entrada = new ObjectInputStream(cli.getInputStream()); 
                    if (!cli.isClosed() && cli.isConnected()) {
                        String paquete = (String) entrada.readObject();
                        Paquete pk = mGson.fromJson(paquete, Paquete.class);
                        //  String mensaje = (String) entrada.readObject();
                        System.out.println(pk.getData().toString());
                       if(pk.getInstruccion() == Instruccion.ENVIAR_ARCHIVO){
                           Cabesera c = mGson.fromJson(pk.getData(), Cabesera.class);
                       //    FileOutputStream fos = new FileOutputStream(c.nombreFichero+ "_copia");
                        //   recibir(c, entrada,fos);
                       }
                           
                        vista.obtener().append(pk.getData().toString() + "\n");
                       
                    } else {
                        conectado = false;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException ex) {
            try {
                cli.close();

                System.out.println("desconectado");
                //  Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex1) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {

            try {
                conectado = false;
                //  cli.delCliente( String.valueOf(  cli.getPort() ) );
                cli.close();
                if (entrada != null) {
                    entrada.close();
                }
                //    out.close();

                System.out.println("Terminado");
            } catch (IOException ioe) {

            }
        }
    }
    private void recibirDatos(Cabesera mensaje, ObjectInputStream ois ){
         try {
         
          
            // Se abre un fichero para empezar a copiar lo que se reciba.
            FileOutputStream fos = new FileOutputStream(mensaje.nombreFichero
                    + "_copia");

          
            Object mensajeAux;
            do {
                // Se lee el mensaje en una variabla auxiliar
                mensajeAux = ois.readObject();

                // Si es del tipo esperado, se trata
                if (mensajeAux instanceof Cabesera) {
                    Cabesera mensajeRecibido = (Cabesera) mensajeAux;
                    // Se escribe en pantalla y en el fichero
                    System.out.print(new String(
                            mensajeRecibido.contenidoFichero, 0,
                            mensajeRecibido.bytesValidos));
                    fos.write(mensajeRecibido.contenidoFichero, 0,
                            mensajeRecibido.bytesValidos);
                } else {
                    // Si no es del tipo esperado, se marca error y se termina
                    // el bucle
                    System.err.println("Mensaje no esperado "
                            + mensajeAux.getClass().getName());
                    break;
                }
            } while (!mensaje.ultimoMensaje);

            // Se cierra socket y fichero
            fos.close();
         //   ois.close();
         //   socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void enviarMensaje(Paquete men) {
        try {

            //  Paquete pk = new Paquete("222", String.valueOf(cli.getPort()),2, men);
            salida.writeObject(men);
            //  salida.writeObject(men);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensaje(String men) {
        try {

            Paquete pk = new Paquete("222", String.valueOf(cli.getInetAddress().getHostAddress()), 3, men);
            String jsonpedido = mGson.toJson(pk);
            salida.writeObject(jsonpedido);

            //  salida.writeObject(men);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensajeUsuario(String destino, String men) {
        try {

            Paquete pk = new Paquete(destino, String.valueOf(cli.getInetAddress().getHostAddress()), 4, men);
            String jsonpedido = mGson.toJson(pk);
            salida.writeObject(jsonpedido);

            //  salida.writeObject(men);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensajeArchivo(String destino, String men) {
        try {

            Paquete pk = new Paquete(destino, this.nick, Instruccion.ENVIAR_ARCHIVO, men);
            String jsonpedido = mGson.toJson(pk);
            salida.writeObject(jsonpedido);

            //  salida.writeObject(men);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarNick(String men) {
        try {

            Paquete pk = new Paquete("222", String.valueOf(cli.getInetAddress().getHostAddress()), 1, men);
            String jsonpedido = mGson.toJson(pk);
            salida.writeObject(jsonpedido);
            //    salida.writeObject(mensaje);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Registrarse(String nick){
    Date fecha= new Date(2014, 11, 9);
    Propietario p1=new Propietario("erwin_plaza_x@hotmail.com", "Erwin Plaza",nick,"123" ,"2014-11-9" , "2014-11-9");
    String data=mGson.toJson(p1);
    
    try {

            Paquete pk = new Paquete("222", String.valueOf(cli.getInetAddress().getHostAddress()), 4, data);
            String jsonpedido = mGson.toJson(pk);
            salida.writeObject(jsonpedido);
            //    salida.writeObject(mensaje);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public  void GuardarPrueba(String nick){
    Date fecha= new Date(2014, 11, 9);
    Propietario p1=new Propietario("erwin_plaza_x@hotmail.com", "Erwin Plaza",nick,"123" ,"2014-11-9" , "2014-11-9");
    Propietario p2=new Propietario("erwin.plaza.x@gmail.com", "Erwin",nick+"2","321" ,"2014" , "2014");
    
        List<Object> lista =new ArrayList<Object>();
        //lista.add("Esta es mi consulta");
        lista.add(p1);
        lista.add(p2);
    
    String data=mGson.toJson(lista);
    
    try {

            Paquete pk = new Paquete("222", String.valueOf(cli.getInetAddress().getHostAddress()), 5, data);
            pk.setUsuario("winzeros");
            String jsonpedido = mGson.toJson(pk);
            salida.writeObject(jsonpedido);
            //    salida.writeObject(mensaje);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Despachador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNick(String nick) {
        this.nick = nick;

    }

    public String getNick() {
        return nick;
    }

    //cerrar cliente
    public void cerrarCliente() {
        Paquete pk = new Paquete("222", String.valueOf(cli.getPort()), 2, "close");
        this.enviarMensaje(pk);

    }

    public void recibir(Cabesera mensaje, ObjectInputStream ois , FileOutputStream fos) {
        try {
            // Se abre un fichero para empezar a copiar lo que se reciba.
         //   FileOutputStream fos = new FileOutputStream(mensaje.nombreFichero+ "_copia");
            Object mensajeAux;
            do {
                // Se lee el mensaje en una variabla auxiliar
                mensajeAux = ois.readObject();

                // Si es del tipo esperado, se trata
                if (mensajeAux instanceof Cabesera) {
                    Cabesera mensajeRecibido = (Cabesera) mensajeAux;
                    // Se escribe en pantalla y en el fichero
                    System.out.print(new String(
                            mensajeRecibido.contenidoFichero, 0,
                            mensajeRecibido.bytesValidos));
                    fos.write(mensajeRecibido.contenidoFichero, 0,
                            mensajeRecibido.bytesValidos);
                } else {
                    // Si no es del tipo esperado, se marca error y se termina
                    // el bucle
                    System.err.println("Mensaje no esperado "
                            + mensajeAux.getClass().getName());
                    break;
                }
            } while (!mensaje.ultimoMensaje);

            fos.close();
     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cliente cc = new Cliente("127.0.0.1", 2050);
        cc.start();
    }
}
