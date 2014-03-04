package chambresPhytotroniques.vue.plannification;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.controleur.PlannificationAnnulerControleur;
import chambresPhytotroniques.controleur.PlannificationValiderControleur;
import chambresPhytotroniques.vue.corps.ListLine;

public class PlannificationBoutons extends JPanel {

	private static final long serialVersionUID = 5154929750748571642L;

	private static final String VALIDER_TEXT = "Valider";

	private static final String ANNULER_TEXT = "Annuler";

	private JButton valider;
	private JButton annuler;

	private PlannificationValiderControleur validerControleur;

	private PlannificationAnnulerControleur annulerControleur;

	public PlannificationBoutons(Plannification plannification,
			PlannificationLine[] plannificationLines, ListLine listLignes,
			FileDAttente fileDAttente) {
		this.setLayout(new BorderLayout());

		this.valider = new JButton(VALIDER_TEXT);
		this.annuler = new JButton(ANNULER_TEXT);

		this.validerControleur = new PlannificationValiderControleur(
				plannification, listLignes, fileDAttente);
		this.annulerControleur = new PlannificationAnnulerControleur(
				plannification);

		this.valider.addActionListener(this.validerControleur);
		this.annuler.addActionListener(this.annulerControleur);

		this.add(this.valider, BorderLayout.CENTER);
		this.add(this.annuler, BorderLayout.EAST);
	}

}
