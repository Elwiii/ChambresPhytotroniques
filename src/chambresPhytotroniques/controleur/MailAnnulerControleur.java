/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.controleur;

import chambresPhytotroniques.vue.mails.Mails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jacques
 */
public class MailAnnulerControleur implements ActionListener {
    
    private Mails mails;

    public MailAnnulerControleur(Mails mails) {
        super();
        this.mails = mails;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mails.dispose();
    }
    
}
