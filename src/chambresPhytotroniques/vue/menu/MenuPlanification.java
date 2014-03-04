package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.corps.ListLine;

public class MenuPlanification extends JMenu {

	private static final long serialVersionUID = -7065641795545923837L;

	private static final int RACCOURCIS = KeyEvent.VK_N;

	private MenuStartPlannification menuStartPlannification;
	private MenuStopPlannification menuStopPlannification;

	public MenuPlanification(Fenetre fenetre, ListLine listLines,
			FileDAttente fileDAttente) {
		super("Planification");

		this.setMnemonic(RACCOURCIS);

		this.menuStopPlannification = new MenuStopPlannification(listLines);
		this.menuStartPlannification = new MenuStartPlannification(fenetre,
				listLines, menuStopPlannification, fileDAttente);

		this.menuStopPlannification
				.setMenuStartPlannification(this.menuStartPlannification);

		this.menuStopPlannification.setEnabled(false);

		this.add(this.menuStartPlannification);
		this.add(this.menuStopPlannification);
	}

	public MenuStartPlannification getMenuStartPlannification() {
		return menuStartPlannification;
	}

}
