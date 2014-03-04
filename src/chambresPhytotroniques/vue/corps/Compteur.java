/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.vue.corps;

import javax.swing.JPanel;

/**
 *
 * @author Margaux
 */
public class Compteur extends JPanel {
    
    private CompteurTempsReel ctr;
    
    public Compteur() {
        super();
        
        this.ctr = new CompteurTempsReel();
        
        this.add(ctr);
    }

   public CompteurTempsReel getCtr() {
        return ctr;
   }
      
}
