/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.controleur;

import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.vue.mails.Mails;
import chambresPhytotroniques.vue.mails.MailsLine;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jacques
 */
public class MailValiderControleur implements ActionListener {
    
    private boolean error;
    private Mails mails;
    private MailsLine[] mailsLines;

    public MailValiderControleur(Mails mails, MailsLine[] mailsLines) {
        super();
        this.mails = mails;
        this.mailsLines = mailsLines;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.error = false;
        
        for (int i = 0; i < this.mailsLines.length; i++) {
			try {
				Configuration.getConfiguration().setAdresse(i+"", this.mailsLines[i].getA());
			} catch (Exception ex) {
				// TODO: handle exception
				this.error = true;
				ex.printStackTrace();
			}
/* BOUCLES !
			try {
				Configuration.getConfiguration().setB(
						this.mailsLines[i].getsonde(),
						Double.valueOf(this.mailsLines[i].getB()));
			} catch (Exception ex) {
				// TODO: handle exception
				this.error = true;
				ex.printStackTrace();
			}  */
		}

		if (!error) {
			// Si y'a pas eu d'erreur, on ferme la fenêtre
			this.mails.dispose();
		} else {
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						Color col = mails.getMailsBoutons()
								.getValider().getBackground();
						mails.getMailsBoutons().getValider()
								.setBackground(Color.RED);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						mails.getMailsBoutons().getValider()
								.setBackground(col);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
					}
				}
			}.start();
		}
        
    }
    
}
