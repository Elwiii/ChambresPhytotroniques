package chambresPhytotroniques.vue.tempsScrutation;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import chambresPhytotroniques.controleur.TempsScrutationAnnulerControleur;
import chambresPhytotroniques.controleur.TempsScrutationValiderControleur;

public class TempsScrutationBoutons extends JPanel {

	private static final long serialVersionUID = -4885625914966914565L;

	private static final String VALIDER_TEXT = "Valider";

	private static final String ANNULER_TEXT = "Annuler";

	private JButton valider;
	private JButton annuler;

	private TempsScrutationValiderControleur validerControleur;

	private TempsScrutationAnnulerControleur annulerControleur;

	public TempsScrutationBoutons(TempsScrutation tempsScrutation,
			TempsScrutationLine[] tempsScrutationLines) {
		super();

		this.setLayout(new BorderLayout());

		this.valider = new JButton(VALIDER_TEXT);
		this.annuler = new JButton(ANNULER_TEXT);

		this.validerControleur = new TempsScrutationValiderControleur(
				tempsScrutation, tempsScrutationLines);
		this.annulerControleur = new TempsScrutationAnnulerControleur(
				tempsScrutation);

		this.valider.addActionListener(this.validerControleur);
		this.annuler.addActionListener(this.annulerControleur);

		this.add(this.valider, BorderLayout.CENTER);
		this.add(this.annuler, BorderLayout.EAST);
	}

	public JButton getValider() {
		return valider;
	}

}
