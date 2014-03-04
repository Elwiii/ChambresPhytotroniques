package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.corps.Line;
import chambresPhytotroniques.vue.corps.ListLine;

public class ListLinesControleur implements ActionListener {

	private ListLine listLine;

	private FileDAttente fileDAttente;

	public ListLinesControleur(ListLine listLine, FileDAttente fileDAttente) {
		super();
		this.listLine = listLine;
		this.fileDAttente = fileDAttente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.fileDAttente.isTheLastOpen()) {
			// Si c'est le dernier ouvert, on désactive le bouton

			for (Line line : this.listLine.getListLines()) {
				if (line.getDemarrerArreterEtat().getDemarrerArreter().isEtat()) {
					line.getDemarrerArreterEtat().setEnabled(false);
				}
			}
		} else {
			// Sinon on active tous les boutons

			for (Line line : this.listLine.getListLines()) {
				line.getDemarrerArreterEtat().setEnabled(true);
			}
		}
	}

}
