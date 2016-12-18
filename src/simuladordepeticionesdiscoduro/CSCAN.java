/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladordepeticionesdiscoduro;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author elias
 */
public class CSCAN implements Runnable{
    
    private Peticion[] juego_de_peticiones;
    private JFrame jframe;
    
    private int siguiente = 0;
    
    private Peticion ultima_servida;
    
    private boolean abajo = true;
    
    public CSCAN(Peticion[] juego_de_peticiones) {
        this.juego_de_peticiones = juego_de_peticiones;
    }

    CSCAN(Peticion[] juego_peticiones, javax.swing.JFrame jframe) {
        this.juego_de_peticiones = juego_peticiones;
        this.jframe = jframe;
    }
    
    @Override
    public void run() {
        while(hayaPeticionesFormadas()){
            Peticion p = getSiguiente();
            if(p==null){
                break;
            }else{
                p.setStatus(Peticion.ACTIVA);
                moverPeticiones();
            }
        }
        while(hayPeticionesSiendoAtendidas()){
            moverPeticiones();
        }
    }
    
    public Peticion getSiguiente(){
        
        
        Peticion p = null;
        
        
        for(Peticion peticion : this.juego_de_peticiones){
        
            if(peticion.getStatus()==Peticion.CREADA){
                
                if(ultima_servida==null){
                    if(hayHaciaAbajo(Cabezal.getPista_actual())){
                        //sacar la siguiente hacia arriba
                        p = siguienteHaciaAbajo(Cabezal.getPista_actual());
                    }else{
                        //regresamos el cabezal a la ultima pista
                        Cabezal.moverAPista(30);
                        p = siguienteHaciaAbajo(Cabezal.getPista_actual());
                    }
                }else{
                    if(hayHaciaAbajo(ultima_servida.getPista())){
                        //sacar la siguiente hacia arriba
                        p = siguienteHaciaAbajo(ultima_servida.getPista());
                    }else{
                        //regresamos el cabezal a la ultima pista
                        Cabezal.moverAPista(30);
                        p = siguienteHaciaAbajo(Cabezal.getPista_actual());
                    }                   
                }
            }
        }
        
        ultima_servida = p;
        return p;
        
        
    }
    
    public boolean hayHaciaAbajo(int pista){
        boolean band = false;
        for(Peticion peticion : this.juego_de_peticiones){
            if(peticion.getPista() < pista){
                band = true;
                break;
            }
        }
        return band;
    }
    
    public boolean hayHaciaArribaDe(int pista){
        boolean band = false;
        for(Peticion peticion : this.juego_de_peticiones){
            if(peticion.getPista() > pista){
                band = true;
            }
        }
        return band;
    }
    
    public Peticion siguienteHaciaArriba(int pista){
        for(Peticion peticion : this.juego_de_peticiones){
            if(peticion.getStatus()==Peticion.CREADA){
                if(peticion.getPista() > pista){
                   return peticion;
               }   
            }
        }
        return null;
    }
    
    public Peticion siguienteHaciaAbajo(int pista){
        for(Peticion peticion : this.juego_de_peticiones){
            if(peticion.getStatus()==Peticion.CREADA){
                if(peticion.getPista() < pista){
                   return peticion;
               }   
            }
        }
        return null;
    }
    
    public boolean hayaPeticionesFormadas(){
        boolean band = false;
        for(Peticion peticion : this.juego_de_peticiones){
            if(peticion.getStatus()==Peticion.CREADA){
                band = true;
                break;
            }
        }
        return band;
    }
    
    
    private boolean hayPeticionesSiendoAtendidas() {
        boolean band = false;
        for(Peticion peticion : this.juego_de_peticiones){
            if(peticion.getStatus()==Peticion.ACTIVA){
                band = true;
                break;
            }
        }
        return band;
    }

    private void moverPeticiones() {
        for (Peticion peticion : juego_de_peticiones) {
            if(peticion.getStatus()==Peticion.ACTIVA){
                
                if(peticion.getX()<=80){
                    Cabezal.moverAPista(peticion.getPista());
                    peticion.setX( ( peticion.getX() - 30  ) );
                    jframe.repaint(0,0,jframe.getWidth(),jframe.getHeight());   
                    peticion.setStatus(Peticion.TERMINADA);
                }else{
                    peticion.setX( ( peticion.getX() - 30  ) );
                }
                jframe.repaint(0,0,jframe.getWidth(),jframe.getHeight());   
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(CSCAN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
