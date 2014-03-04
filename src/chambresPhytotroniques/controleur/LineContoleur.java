package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.corps.Line;

public class LineContoleur implements ActionListener {

	private int sonde;
	private Line line;
	private FileDAttente filesDAttente;
	private ListLinesControleur listLinesControleur;

	public LineContoleur(int sonde, Line line, FileDAttente filesDAttente,
			ListLinesControleur listLinesControleur) {
		this.sonde = sonde;
		this.line = line;
		this.filesDAttente = filesDAttente;
		this.listLinesControleur = listLinesControleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!this.line.getDemarrerArreterEtat().isEtat()) {
			// Si on l'active
			this.filesDAttente.executeEvenement(sonde);
			this.line.getDemarrerArreterEtat().setEtat(true);
		} else {
			// Si on désactive
			this.filesDAttente.removeExecuteEvenement(sonde);
			this.line.getDemarrerArreterEtat().setEtat(false);
		}

		this.listLinesControleur.actionPerformed(e);
	}
}
