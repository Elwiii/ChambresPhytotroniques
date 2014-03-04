/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.vue.menu;

import chambresPhytotroniques.controleur.MailsControleur;
import chambresPhytotroniques.vue.Fenetre;
import javax.swing.JMenuItem;

/**
 *
 * @author Jacques
 */
public class MenuParametresMails extends JMenuItem {
    
    private Fenetre fenetre;
    private MailsControleur mailsControleur;
    
    public MenuParametresMails(Fenetre fenetre) {
        
        super("Paramètres");
        
        mailsControleur = new MailsControleur(fenetre);
        
        this.addActionListener(this.mailsControleur);
        
        this.fenetre = fenetre;
        
    } 
    
}
