/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_J.Client;

import chat_J.Interface.InterfaceJchat;
import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *
 * @author Morales
 */
public class JchatHiloClient extends Thread implements Runnable{
    
    public ventanaJuego ventana;
    public InterfaceJchat jchat;
    public int avatar, sw = 0;
    public String conectados = null, paneles = null, avatare = null,
                  jugadores[], pans[], avats[], mensaje;  
    public boolean sesion = true;
    public JPanel panActual, panAnterior, panJugador;
    public Graphics dip, dip2, dip3;
    private Image avt, avtJ, avtM;
    
    public JchatHiloClient(InterfaceJchat jchat, ventanaJuego ventana) {
        this.jchat = jchat;
        this.ventana = ventana;  
    }
    
    public void obtenerPanel() throws RemoteException{
        dip = ventana.miPanel(jchat.obtenerPanel(ventana.jugador)).getGraphics(); 
        panActual = ventana.miPanel(jchat.obtenerPanel(ventana.jugador));
    }
    
    public void obtenerPanelAnterior() throws RemoteException{
        dip2 = ventana.miPanel(ventana.panelAnterior).getGraphics();
        panAnterior = ventana.miPanel(ventana.panelAnterior);        
    }
    
    public void obtenerPanelJugadores(int panel) throws RemoteException{
        dip3 = ventana.miPanel(panel).getGraphics();
        panJugador = ventana.miPanel(panel); 
    }
    
    public Image dibujarAvatar() throws RemoteException{
//        if(panActual.getName().equals("pan4")){ 
        switch(ventana.avatar){
            case 1:
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_1.png").getImage();
            break;  
            case 2:
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_2.png").getImage();
            break; 
            case 3:
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_3.png").getImage();
            break; 
            case 4:
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_4.png").getImage();
            break;  
            case 5:
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_8.png").getImage();
            break; 
            case 6:
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_9.png").getImage();
            break;     
        }       
        dip.drawImage(avt, 12, 8, panActual);         
        panActual.setToolTipText(ventana.jugador); 
        if(ventana.cambioPanel()){      
            avt = new ImageIcon("src\\chat_J\\Imagenes\\avatar_bg.png").getImage();
            dip2.drawImage(avt, 12, 8, panAnterior);             
            //dip2.drawString("", 15, 46);
             panAnterior.setToolTipText(""); 
        }
        return avt;
    }
    
    public void dibujarAvatarJugador(int avatarJ, JPanel panJ, String jug) throws RemoteException{
        
         switch(avatarJ){
            case 1:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_1.png").getImage();
            break;  
            case 2:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_2.png").getImage();
            break; 
            case 3:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_3.png").getImage();
            break; 
            case 4:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_4.png").getImage();
            break;  
            case 5:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_8.png").getImage();
            break; 
            case 6:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_9.png").getImage();
            break;   
            case 7:
            avtJ = new ImageIcon("src\\chat_J\\Imagenes\\avatar_bg.png").getImage();
            break;    
        }
         
         dip3.drawImage(avtJ, 12, 8, panJ);
         if(avatarJ==7){
             panJ.setToolTipText("");
         }
         else{
         panJ.setToolTipText(jug);
         }
    }
    
    @Override
    public void run(){
        try {
            
            while(sesion){
             
                jugadores = jchat.verConectados().split("\n");
                pans = jchat.verPaneles().split("\n");
                avats = jchat.verAvatar().split("\n");
                
                for (int i = 1; i < jugadores.length; i++) {
//                    System.out.println("jugad: " +jugadores[i]); 
//                    System.out.println("pans: " +pans[i]); 
//                    System.out.println("avats: " +avats[i]);
                    if(!jugadores[i].equals(ventana.jugador)){ 
                   
                    obtenerPanelJugadores(Integer.parseInt(pans[i]));
                    dibujarAvatarJugador(Integer.parseInt(avats[i]), panJugador, jugadores[i]);                     
                    }
                }
                
                obtenerPanel();
                obtenerPanelAnterior();
                avtM = dibujarAvatar();
                ventana.mostrarme(avtM); 
                ventana.confirmaVecinos();
                mensaje = jchat.leerMensajes(ventana.jugador);
                if(!mensaje.equals("")){                    
                    ventana.addMensajes(mensaje); 
                }
                sesion = ventana.estadoSesion();
                
                
                try {
                    sleep(500);
                    for (int i = 1; i < jugadores.length; i++) {
//                        System.out.println("Pan: " +pans[i]);
                    if(!jugadores[i].equals(ventana.jugador)){ 
                       
                    obtenerPanelJugadores(Integer.parseInt(pans[i]));
                    dibujarAvatarJugador(7, panJugador, jugadores[i]);
                    }
                }
                } catch (InterruptedException ex) {
                    Logger.getLogger(JchatHiloClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(JchatHiloClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
