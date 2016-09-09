/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_J.Server;

import java.util.ArrayList;

/**
 *
 * @author Morales
 */
public class usuario {
    
    String nombre;
    ArrayList <String> mensajes;
    int avatar, panel;
    

    public usuario(String nombre, int panel, int avatar) {
        this.nombre = nombre;
        this.panel = panel;      
        this.avatar = avatar;
        this.mensajes = new ArrayList<>();              
    }     

     public void setNombre(String nombre) {
        this.nombre = nombre;
     }    
    
     public void setMensaje(String mensaje) {
        mensajes.add(mensaje);
     } 
     
     public void setPanel(int panel){
         this.panel = panel;
     }
     
    public String getMensaje() {
        String mensaje = "";
        if (mensajes != null){
            if(mensajes.size() > 0){
                mensaje = mensajes.get(0);
                mensajes.remove(0);
            }
        }

        return mensaje;
    }         
    
    public String getNombre() {
        return nombre;
    }
    
    public int getAvatar(){
        return avatar;
    }
    
    public int getPanel(){
        return panel;
    }
    
    
}

