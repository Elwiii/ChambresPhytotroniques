package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chambresPhytotroniques.vue.corps.Line;
import chambresPhytotroniques.vue.corps.ListLine;
import chambresPhytotroniques.vue.menu.MenuStartPlannification;
import chambresPhytotroniques.vue.menu.MenuStopPlannification;

public class PlannificationStopControleur implements ActionListener {

	private ListLine listLines;
	private MenuStartPlannification menuStartPlannification;
	private MenuStopPlannification menuStopPlannification;

	public PlannificationStopControleur(ListLine listLines,
			MenuStartPlannification menuStartPlannification,
			MenuStopPlannification menuStopPlannification) {
		this.listLines = listLines;
		this.menuStartPlannification = menuStartPlannification;
		this.menuStopPlannification = menuStopPlannification;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Line line : this.listLines.getListLines()) {
			line.getDemarrerArreterEtat().setPlannification(false);
		}

		if (this.listLines.getFileDAttente().isTheLastOpen()) {
			for (Line line : this.listLines.getListLines()) {
				if (line.getDemarrerArreterEtat().isEtat()) {
					line.getDemarrerArreterEtat().setEnabled(false);
				}
			}
		}

		this.menuStartPlannification.setEnabled(true);
		this.menuStopPlannification.setEnabled(false);
	}

}
