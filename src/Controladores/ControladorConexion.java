/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Eventos.EventoConexionDesconexion;
import Interfaces.EscuchadorEventoConexionDesconexion;
import Util.Cliente;
import Util.ListaClientes;

/**
 *
 * @author ERWIN
 */
public class ControladorConexion implements EscuchadorEventoConexionDesconexion{

    @Override
    public void alConecta(EventoConexionDesconexion e) {
        Cliente cliente = (Cliente)e.cliente;
       
        String codigo =  GenerarCodigo(cliente);
        System.out.println("----------EVENTO REGISTRO DE CLIENTES------");
        ListaClientes.addClientes(codigo, cliente);
        System.out.println("Se Conecto un nuevo usuario");
        System.out.println("----------EVENTO CONEXION------");
      
    
    }
    public String GenerarCodigo(Cliente cliente){
        
        return ""+cliente.getConexion().getPort();
    }

    @Override
    public void alDesconectar(EventoConexionDesconexion e) {
          Cliente cliente = e.cliente;
               
        System.out.println("----------EVENTO DESCONEXION DE CLIENTES------");
        ListaClientes.delCliente(""+cliente.getConexion().getPort());
        System.out.println("Se Desconecto  un  usuario nick :"+ cliente.getUsuario());
        System.out.println("----------EVENTO DESCONEXION DE CLIENTES------");
    }
    
}
