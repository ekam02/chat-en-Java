/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_J.Server;

import chat_J.Interface.InterfaceJchat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Morales
 */
public class ImplJchat extends UnicastRemoteObject implements InterfaceJchat {
    public  ArrayList <usuario> Usuarios; 
    private Random aux;
    public int numero;
    public boolean sinPanel = true;
    
    public ImplJchat() throws RemoteException{
        super();
        Usuarios = new ArrayList<>(); 
    }

    @Override
    public boolean iniciarSesion(String nick, int pan, int avt) throws RemoteException {
        boolean result=true;
         boolean encontrado=false;

        for(int i=0; i<Usuarios.size(); i++){ //Loop de comprobación de usuario existente
            if(Usuarios.get(i).getNombre().equals(nick)){
                encontrado=true;
                //result=false;
                break;
            }
        }
        
        if(encontrado){
            result=false;
        }

        if(!encontrado){
             Usuarios.add(new usuario(nick, pan, avt));  
             
//            for(int i=0; i<Usuarios.size(); i++){//Se recorre a todos los usuarios
//                Se le deja el mensaje de entrada del usuario
//                Usuarios.get(i).setMensaje("El usuario "+ nick +" inicio sesion");              
//            }           
        }
        return result; 
    }

    @Override
    public String verConectados() throws RemoteException {
         String contactos="";

        //Se recorre los usuarios y se concatena los nombres en un String
        for(int i=0; i<Usuarios.size(); i++){
            contactos=contactos+ "\n" + Usuarios.get(i).getNombre();
        }
        return contactos; 
    }

    @Override
    public String cerrarSesion(String nick) throws RemoteException {
        for (int i = 0; i < Usuarios.size(); i++) 
        {
            if(Usuarios.get(i).getNombre().equals(nick))
            {
                Usuarios.remove(i);
                //salir = nick;
                break;
            }
        }
        return /*salir*/ nick;
    }

    @Override
    public String enviarMensajes(String msj) throws RemoteException {
        
        for (int i=0; i < Usuarios.size(); i++) {
                
                Usuarios.get(i).setMensaje(msj);
            
        }
        return msj;
    }

    @Override
    public String leerMensajes(String nick) throws RemoteException {
         String msj="";
        
        for (int i=0; i < Usuarios.size(); i++) {
            
            if(Usuarios.get(i).getNombre().equals(nick)){ 
                
                msj = Usuarios.get(i).getMensaje();
                break;
            }                  
        }
        return msj;
    }

    @Override
    public void setAvatar(int avt, String nick) throws RemoteException {
        
        for(int i=0; i<Usuarios.size(); i++){ //Loop de comprobación de usuario existente
            if(Usuarios.get(i).getNombre().equals(nick)){
              
            }
        }
    }

    @Override
    public int asignarPanel(String nick) throws RemoteException {
        aux = new Random();
        if(!Usuarios.isEmpty()){//Si Usuarios no está vacío
                numero = aux.nextInt(25)+1;
                for(int i=0; i<Usuarios.size(); i++){ //Loop de comprobación de usuario existente
                    if(numero==Usuarios.get(i).getPanel()){
                        numero = aux.nextInt(25)+1;
                        i=0;
                    }
                }
        }
        else{
           numero = aux.nextInt(25)+1; 
        }
        return numero;
    }

    @Override
    public int obtenerPanel(String nick) throws RemoteException {
        int num=0;
        for(int i=0; i<Usuarios.size(); i++){
            if(Usuarios.get(i).getNombre().equals(nick)){
                num = Usuarios.get(i).getPanel();
            }
        }
        return num;
    }

    @Override
    public void cambiarPanel(String nick, int nuevoPanel) throws RemoteException {
        for(int i=0; i<Usuarios.size(); i++){
            if(Usuarios.get(i).getNombre().equals(nick)){
                Usuarios.get(i).setPanel(nuevoPanel);
            } 
        }
    }

    @Override
    public String verPaneles() throws RemoteException {
        String paneles="";

        //Se recorre los usuarios y se concatena los nombres en un String
        for(int i=0; i<Usuarios.size(); i++){
            paneles=paneles+ "\n" + Usuarios.get(i).getPanel();
        }
        return paneles;
    }

    @Override
    public String verAvatar() throws RemoteException {
        String avatar="";

        //Se recorre los usuarios y se concatena los nombres en un String
        for(int i=0; i<Usuarios.size(); i++){
            avatar=avatar+ "\n" + Usuarios.get(i).getAvatar();
        }
        return avatar;
    }

    @Override
    public boolean comprobarMovimiento(int mov) throws RemoteException {
        boolean mover = true;
        for(int i=0; i<Usuarios.size(); i++){
            if(mov==Usuarios.get(i).getPanel()){
                mover = false;
            }
        }
        return mover;
    }

    @Override
    public void enviarMensajes(String msj, String[] destinatarios) throws RemoteException {
        System.out.println("Entro enviar mensaje");
//        for (int i = 0; i < destinatarios.length; i++){
//            String destino = destinatarios[i]; 
//            System.out.println("dest: " +destino);
//            for (int j=0; j < Usuarios.size(); j++) {                
//                System.out.println("Usu: " +Usuarios.get(i).getNombre());
//                if(Usuarios.get(i).getNombre().equals(destino)){ 
//                    System.out.println("destino: "+destino);
//                    System.out.println("msj: "+msj);
//                    Usuarios.get(i).setMensaje(msj);
//                    j=0;
//                    break;
//                }
//            }
//            
//        }
        for (int i = 0; i < Usuarios.size(); i++) {
            System.out.println("Usu: " +Usuarios.get(i).getNombre());            
        }
        for (int i = 0; i < destinatarios.length; i++) {
            System.out.println("Dest: " +destinatarios[i]);
        }        
        
        for (int i = 0; i < Usuarios.size(); i++) { 
            String usuario = Usuarios.get(i).getNombre();
            for (int j = 0; j < destinatarios.length; j++) { 
                if(usuario.equals(destinatarios[j])){
                     System.out.println("Iguales");
                     Usuarios.get(i).setMensaje(msj); 
                     break;
                }
            }
        }
    }
    
}

