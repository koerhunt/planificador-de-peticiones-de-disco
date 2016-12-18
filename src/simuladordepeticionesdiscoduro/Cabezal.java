/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladordepeticionesdiscoduro;

import java.awt.Color;
import java.awt.Graphics;
import static simuladordepeticionesdiscoduro.SimuladorInterface.graphics;

/**
 *
 * @author elias
 */
public class Cabezal {
    
    private static int x;
    private static int y;
    
    private static int pista_actual;

    public Cabezal(int x, int y, int pista) {
        this.x = x;
        this.y = y;
        this.pista_actual = pista;
        moverAPista(pista);
    }
    
    public static void moverAPista(int p){
        pista_actual = p;
        y = 160+(10*p);
    }
    
    public static void pintarCabezal(Graphics g){
        graphics.setColor(Color.red);
        graphics.fillRect(x, y, 35, 10);
    }

    public static int getPista_actual() {
        return pista_actual;
    }


    
    
}
