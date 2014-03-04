/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.vue.corps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Margaux
 */
public class CompteurTempsReel extends JLabel {
    
    private Timer timer;
    private int compteur = 0;
    private int tempsTotal = 0;
    
    public CompteurTempsReel() {
        compteur = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compteur++;
                rafraichir();
            }
        });
        this.setText(compteur + "");
    }
    
    public void start() {
        compteur = 0;
        timer.start();
    }
    
    public void stop() {
        timer.stop();
    }
    
    public void reset() {
        this.compteur = 0;
    }
    
    public void rafraichir(){
        this.setText(compteur + "/" + tempsTotal);
        repaint();
    }

    /**
     * @return the tempsTotal
     */
    public int getTempsTotal() {
        return tempsTotal;
    }

    /**
     * @param tempsTotal the tempsTotal to set
     */
    public void setTempsTotal(int tempsTotal) {
        this.tempsTotal = tempsTotal;
    }
    
}
