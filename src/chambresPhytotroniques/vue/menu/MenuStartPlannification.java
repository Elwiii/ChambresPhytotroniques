package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.controleur.PlannificationStartControleur;
import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.corps.ListLine;

public class MenuStartPlannification extends JMenuItem {

	private static final long serialVersionUID = -6220012085408003290L;

	private static final int RACCOURCIS = KeyEvent.VK_S;
	private static final KeyStroke ACCELERATOR = KeyStroke.getKeyStroke(
			KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);

	private PlannificationStartControleur plannificationStartControleur;

	public MenuStartPlannification(Fenetre fenetre, ListLine listLines,
			MenuStopPlannification menuStopPlannification,
			FileDAttente fileDAttente) {
		super("Démarrer la plannification");

		this.plannificationStartControleur = new PlannificationStartControleur(
				fenetre, listLines, this, menuStopPlannification, fileDAttente);
		this.addActionListener(this.plannificationStartControleur);

		this.setMnemonic(RACCOURCIS);
		this.setAccelerator(ACCELERATOR);
	}

	public PlannificationStartControleur getPlannificationStartControleur() {
		return plannificationStartControleur;
	}

}
