/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;


import Protocolo.Cabesera;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Rafael
 */
public class RecibirDatos implements Runnable{
    Cabesera mensaje;
    ObjectInputStream ois;

    public RecibirDatos(Cabesera mensaje, ObjectInputStream ois ) {
        this.mensaje = mensaje;
        this.ois = ois;
    }
    

    @Override
    public void run() {
    
        
    }
    
}
