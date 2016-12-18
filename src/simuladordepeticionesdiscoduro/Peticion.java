/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladordepeticionesdiscoduro;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author elias
 */
public class Peticion {
    
    public static final int CREADA = 0;
    public static final int ACTIVA = 1;
    public static final int TERMINADA = 2;
    
    private int x;
    private int y;
    
    private int pista;
    
    private Color color;
    
    private int status;
    
    public Peticion() {
        
    }

    public Peticion(int x, int y, int pista) {
        this.x = x;
        this.y = y;
        this.pista = pista;
        this.color = generarColorAleatorio();
        this.status = Peticion.CREADA;
    }
    
    private Color generarColorAleatorio(){
        int numero = ThreadLocalRandom.current().nextInt(1, 8);
        switch(numero){
            case 1:
                return Color.BLUE;
            case 2:
                return Color.DARK_GRAY;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.RED;
            case 6:
                return Color.WHITE;
            default:
                return Color.DARK_GRAY;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPista() {
        return pista;
    }

    public void setPista(int pista) {
        this.pista = pista;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void pintarPeticion(Graphics g){
       g.setColor(this.color);
       g.fillRect(this.x,this.y,20,10);
       //System.out.println("Pintando peticion "+this.pista);
   }
    
    
}
