package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chambresPhytotroniques.controleur.ScrutationControleur;
import chambresPhytotroniques.vue.Fenetre;

public class MenuScrutation extends JMenuItem {

	private static final long serialVersionUID = -3171575815600803718L;

	private static final int RACCOURCIS = KeyEvent.VK_T;
	private static final KeyStroke ACCELERATOR = KeyStroke.getKeyStroke(
			KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);

	private ScrutationControleur scrutationControleur;

	public MenuScrutation(Fenetre fenetre) {
		super("Temps de scrutation");

		this.scrutationControleur = new ScrutationControleur(fenetre);

		this.addActionListener(this.scrutationControleur);

		this.setMnemonic(RACCOURCIS);
		this.setAccelerator(ACCELERATOR);
	}

	public ScrutationControleur getScrutationControleur() {
		return scrutationControleur;
	}

}
