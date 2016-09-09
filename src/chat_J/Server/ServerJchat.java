/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_J.Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Morales
 */
public class ServerJchat {
    public static void main(String arg[]){
     try{
        LocateRegistry.createRegistry(1099);
        ImplJchat jchat = new ImplJchat();
        Naming.bind("jchat", jchat);
        System.out.println("Servidor listo...!");
      }
      catch (Exception e){    
          e.printStackTrace();
          System.out.println("ERROR!");
      }
}
}
