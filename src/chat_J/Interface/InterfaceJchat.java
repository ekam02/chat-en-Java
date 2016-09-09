/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_J.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Morales
 */
public interface InterfaceJchat extends Remote {
    
    public boolean   iniciarSesion(String nick, int pan, int avt)                   throws RemoteException;
    public String    verConectados()                                                throws RemoteException;
    public String    cerrarSesion(String nick)                                      throws RemoteException;  
    String           enviarMensajes(String msj)                                     throws RemoteException; 
    public String    leerMensajes(String nick)                                      throws RemoteException;
    public void      setAvatar(int avt, String nick)                                throws RemoteException;
    public int       asignarPanel(String nick)                                      throws RemoteException;
    public int       obtenerPanel(String nick)                                      throws RemoteException;
    public void      cambiarPanel(String nick, int nuevoPanel)                      throws RemoteException;
    public String    verPaneles()                                                   throws RemoteException;                                                
    public String    verAvatar()                                                    throws RemoteException; 
    public boolean   comprobarMovimiento(int mov)                                   throws RemoteException; 
    public void      enviarMensajes(String msj, String[] destinatarios)             throws RemoteException; 
}

