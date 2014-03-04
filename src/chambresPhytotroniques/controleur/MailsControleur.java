/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.controleur;

import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.mails.Mails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jacques
 */
public class MailsControleur implements ActionListener {
    
    private Fenetre fenetre;
    private Mails mails;
    
    public MailsControleur(Fenetre fenetre) {
        this.fenetre = fenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.mails = new Mails(fenetre);
    }
    
   public void close() {
	if (this.mails != null)
		this.mails.dispose();
    }
   
}
