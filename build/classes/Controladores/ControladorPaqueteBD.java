/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Eventos.EventoBD;
import Interfaces.EscuchadorEventoPaqueteBD;
import Protocolo.MensajeBD;
import Protocolo.Respuesta;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Despachador;
import sun.io.Converters;

/**
 *
 * @author ERWIN
 */
public class ControladorPaqueteBD implements EscuchadorEventoPaqueteBD{

     private Gson gson=new Gson();
    @Override
    public void alRecibirPaqueteBD(EventoBD e) {
        
        MensajeBD msjBD = e.mensajeBD;
        
        String keyPort=msjBD.getKeyPort();
        String idUsuario=msjBD.getIdUsuario();
        List<Object> lista=msjBD.getListaObjetos();
        String mensaje=msjBD.getConsulta();
        String respuesta=msjBD.getTipo();
        
        System.out.println("-------EVENTO de Paquete de BD---------");
        
        switch (Integer.parseInt(respuesta)) {
            case Respuesta.MESAJE_CORRECTO: {
                try {                    
                        String data = "correcto";
                        Despachador.enviar(keyPort, data);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorPaqueteBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.out.println("SE ENVIO UN MENSAJE DE EXITO..");
                break;
            }
            case Respuesta.MENSAJE_ERROR: {
                try {                    
                        String data = "error";
                        Despachador.enviar(keyPort, data);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorPaqueteBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.out.println("SE ENVIO UN MENSAJE DE ERROR..");
                break;
            }
            case Respuesta.ENVIO_DE_OBJETO: {
                  
                try {                    
                        String data = gson.toJson(lista.get(0));
                        Despachador.enviar(keyPort, data);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorPaqueteBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.out.println("SE ENVIO UN OBJETO..");
                break;
            }
            case Respuesta.ENVIO_LISTA_OBJETOS: {
                               
             
                    try {                    
                        String data = gson.toJson(lista);
                        Despachador.enviar(keyPort, data);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorPaqueteBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                System.out.println("SE ENVIO UNA LISTA DE OBJETOS..");
                break;
            }

        }

     
        System.out.println("---------EVENTO Mensaje de BD al Cliente-------------");
        
        
    }    
}
