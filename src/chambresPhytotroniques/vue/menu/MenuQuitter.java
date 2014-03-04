package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chambresPhytotroniques.controleur.FenetreControleur;
import chambresPhytotroniques.controleur.QuitterControleur;

public class MenuQuitter extends JMenuItem {

	private static final long serialVersionUID = 8707524093888477013L;

	private static final int RACCOURCIS = KeyEvent.VK_Q;
	private static final KeyStroke ACCELERATOR = KeyStroke.getKeyStroke(
			KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK);

	public MenuQuitter() {
		super("Quitter");

		this.setMnemonic(RACCOURCIS);
		this.setAccelerator(ACCELERATOR);
	}

	public void setFenetreControleur(FenetreControleur fenetreControleur) {
		this.addActionListener(new QuitterControleur(fenetreControleur));
	}

}
