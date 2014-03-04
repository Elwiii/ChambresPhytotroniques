package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chambresPhytotroniques.controleur.EnregistrementControleur;
import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.Fenetre;

public class MenuEnregistrement extends JMenuItem {

	private static final long serialVersionUID = 6080786519939628780L;

	private static final int RACCOURCIS = KeyEvent.VK_E;
	private static final KeyStroke ACCELERATOR = KeyStroke.getKeyStroke(
			KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);

	public MenuEnregistrement(Fenetre fenetre, FileDAttente fileDAttente) {
		super("Enregistrer au format Excel");

		this.addActionListener(new EnregistrementControleur(fenetre,
				fileDAttente));

		this.setMnemonic(RACCOURCIS);
		this.setAccelerator(ACCELERATOR);
	}

}
