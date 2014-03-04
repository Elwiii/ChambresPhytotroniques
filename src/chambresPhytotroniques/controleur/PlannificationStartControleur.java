package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.corps.ListLine;
import chambresPhytotroniques.vue.menu.MenuStartPlannification;
import chambresPhytotroniques.vue.menu.MenuStopPlannification;
import chambresPhytotroniques.vue.plannification.Plannification;

public class PlannificationStartControleur implements ActionListener {

	private ListLine listLines;
	private MenuStartPlannification menuStartPlannification;
	private MenuStopPlannification menuStopPlannification;
	private Fenetre fenetre;
	private FileDAttente fileDAttente;

	private Plannification plannification;

	public PlannificationStartControleur(Fenetre fenetre, ListLine listLines,
			MenuStartPlannification menuStartPlannification,
			MenuStopPlannification menuStopPlannification,
			FileDAttente fileDAttente) {
		this.listLines = listLines;
		this.menuStartPlannification = menuStartPlannification;
		this.menuStopPlannification = menuStopPlannification;
		this.fenetre = fenetre;
		this.fileDAttente = fileDAttente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.plannification = new Plannification(fenetre, listLines,
				fileDAttente);

		menuStopPlannification.setEnabled(true);
		menuStartPlannification.setEnabled(false);
	}

	public void close() {
		if (this.plannification != null)
			this.plannification.dispose();
	}
}
