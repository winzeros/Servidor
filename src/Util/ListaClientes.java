/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ERWIN
 */
public class ListaClientes {
 private static final HashMap<String, Cliente> clientes;
    
    static {
        clientes = new HashMap<String, Cliente>();
    }

    public static void MostrarClientes() {
        Collection<Cliente> values = clientes.values();

        Iterator<Cliente> iterator = values.iterator();

        while (iterator.hasNext()) {

            Cliente socket = iterator.next();
            System.out.println("key :" + String.valueOf(socket.getConexion().getPort()));
            System.out.println("nick :" + socket.getUsuario());
        }

    }

    public static void addClientes(String codigo, Cliente cliente) {
        clientes.put(codigo, cliente);
    }

    public static void RegistrarUsuario(String key, String Nick) throws IOException {

        //   System.out.println("existe: "+ Nick);
        // String ksc = usuarios.get(Nick);
        Cliente socket = clientes.get(key);
        socket.setUsuario(Nick);

    }
   

    public static Cliente getUsuario(String nick) {
        Collection<Cliente> values = clientes.values();

        Iterator<Cliente> iterator = values.iterator();

        while (iterator.hasNext()) {

            Cliente socket = iterator.next();
            if (socket.getUsuario()!= null && socket.getUsuario().equals(nick)) {
                return socket;
            }
        }
        return null;
    }

    public static void delCliente(String ID) {
        if (clientes.containsKey(ID)) {
            clientes.remove(ID);
        }
    }

    public static boolean existeUsuraio(String key) {
        return clientes.containsKey(key);
    }

    public static Cliente getCliente(String key) {
        return clientes.get(key);
    }

    public static Collection<Cliente> getClientes() {
        return clientes.values();
    }

}
