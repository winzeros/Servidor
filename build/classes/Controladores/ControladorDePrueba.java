/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Eventos.EventoBD;
import Interfaces.EscuchadorEventoMensajeBD;
import Protocolo.MensajeBD;
import Protocolo.Respuesta;
import Util.ListaEscuchadoresMsjBD;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ERWIN
 */
public class ControladorDePrueba implements EscuchadorEventoMensajeBD{
        private Gson gson=new Gson();

    @Override
    public void alRecibirPaqueteGuardar(EventoBD e) {
         System.out.println("-------EVENTO de Base de datos---------");
        String keyPort=e.mensajeBD.getKeyPort();
         
        MensajeBD msj=e.mensajeBD;
                
        String idUs=msj.getIdUsuario();
        String nomTab=msj.getNombreTabla();
        String consulta=msj.getConsulta();
        String tipo=msj.getTipo();
        
        if (nomTab.equals("Propietario")){
        List<Propietario> lista_prop=new ArrayList<Propietario>();
        
        //lista_prop=(ArrayList<Propietario>)msj.getListaObjetos();
        List<Object> ls = msj.getListaObjetos();
        //Propietario p1=new Propietario();
        //Propietario p2=new Propietario();
        //Propietario p1=gson.fromJson(ls.get(0).toString(), Propietario.class);
        //Propietario p2=gson.fromJson(ls.get(1).toString(), Propietario.class);
        
        for (int i = 0; i < ls.size(); i++) {
            lista_prop.add(gson.fromJson(ls.get(i).toString(), Propietario.class));
        }
        
        
        System.out.println(idUs);
        System.out.println(nomTab);        
        System.out.println("Informacion de la lista que acaba de llegar");
        
        for (int j = 0; j < lista_prop.size(); j++) {
            System.out.println(lista_prop.get(j).getCorreo());
            System.out.println(lista_prop.get(j).getNombreCompleto());
            System.out.println(lista_prop.get(j).getNombreUsuario());
            System.out.println(lista_prop.get(j).getContrasena());
            System.out.println(lista_prop.get(j).getFechaCreacion());
            System.out.println(lista_prop.get(j).getFechaActualizacion());            
            
        }
        System.out.println(idUs);

        //Envio de Respuesta
        MensajeBD msjRBD=new MensajeBD(keyPort, idUs, nomTab, null, ""+Respuesta.MESAJE_CORRECTO);
        EventoBD msjrespuestaBD=new EventoBD(this, msjRBD);                        
        
        ListaEscuchadoresMsjBD.DispararOnRecibirPaqueteBD(msjrespuestaBD);
        //FIN Envio de Respuesta
        
        System.out.println("SE GUARDO CORRECTAMENTE LAS TABLAS..");
        
        }
        
        
    }

    @Override
    public void alRecibirPaqueteModificar(EventoBD e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alRecibirPaqueteEliminar(EventoBD e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alRecibirPaqueteConsulta(EventoBD e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
