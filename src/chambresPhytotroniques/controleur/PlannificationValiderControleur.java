package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.corps.ListLine;
import chambresPhytotroniques.vue.plannification.Plannification;

public class PlannificationValiderControleur implements ActionListener {

	private Plannification plannification;
	private ListLine listLignes;
	private FileDAttente fileDAttente;

	public PlannificationValiderControleur(Plannification plannification,
			ListLine listLignes, FileDAttente fileDAttente) {
		this.plannification = plannification;
		this.listLignes = listLignes;
		this.fileDAttente = fileDAttente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < plannification.getPlannificationLines().length; i++) {
			if (plannification.getPlannificationLines()[i].isSelected()) {
				this.listLignes.getLine(i).getDemarrerArreterEtat()
						.setPlannification(true);
				this.fileDAttente.executeEvenement(i);
			} else {
				this.listLignes.getLine(i).getDemarrerArreterEtat()
						.setPlannification(false);
			}
		}

		plannification.dispose();
	}
}
