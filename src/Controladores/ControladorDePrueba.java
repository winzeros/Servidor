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
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ERWIN
 */
public class ControladorDePrueba implements EscuchadorEventoMensajeBD{

    @Override
    public void alRecibirPaqueteGuardar(EventoBD e) {
         System.out.println("-------EVENTO de Base de datos---------");
        String keyPort=e.mensajeBD.getKeyPort();
         
        MensajeBD msj=e.mensajeBD;
                
        String idUs=msj.getIdUsuario();
        String nomTab=msj.getNombreTabla();
        String consulta=msj.getConsulta();
        

        List<Propietario> lista_prop=new ArrayList<Propietario>();
        
        //lista_prop=(ArrayList<Propietario>)msj.getListaObjetos();
        List<Object> ls = msj.getListaObjetos();
        
        Propietario p1=(Propietario)ls.get(0);
        Propietario p2=(Propietario)ls.get(1);
        
        String tipo=msj.getTipo();
        
        System.out.println(idUs);
        System.out.println(nomTab);
        
        System.out.println("Informacion de la lista de clientes");
        
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
