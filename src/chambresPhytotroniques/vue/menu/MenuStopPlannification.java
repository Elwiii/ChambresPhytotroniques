package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chambresPhytotroniques.controleur.PlannificationStopControleur;
import chambresPhytotroniques.vue.corps.ListLine;

public class MenuStopPlannification extends JMenuItem {

	private static final long serialVersionUID = -2647369970368953489L;

	private static final int RACCOURCIS = KeyEvent.VK_Q;
	private static final KeyStroke ACCELERATOR = KeyStroke.getKeyStroke(
			KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK);

	private ListLine listLines;

	public MenuStopPlannification(ListLine listLines) {
		super("Arrêter la plannification");

		this.listLines = listLines;

		this.setMnemonic(RACCOURCIS);
		this.setAccelerator(ACCELERATOR);
	}

	public void setMenuStartPlannification(
			MenuStartPlannification menuStartPlannification) {
		this.addActionListener(new PlannificationStopControleur(listLines,
				menuStartPlannification, this));
	}

}
