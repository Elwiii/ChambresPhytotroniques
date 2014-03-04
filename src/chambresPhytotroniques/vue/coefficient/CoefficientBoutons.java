package chambresPhytotroniques.vue.coefficient;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import chambresPhytotroniques.controleur.CoefficientAnnulerControleur;
import chambresPhytotroniques.controleur.CoefficientValiderControleur;

public class CoefficientBoutons extends JPanel {

	private static final long serialVersionUID = -3297178084728439398L;

	private static final String VALIDER_TEXT = "Valider";

	private static final String ANNULER_TEXT = "Annuler";

	private JButton valider;
	private JButton annuler;

	private CoefficientValiderControleur validerControleur;

	private CoefficientAnnulerControleur annulerControleur;

	public CoefficientBoutons(Coefficient coefficient,
			CoefficientLine[] coefficientLines) {
		super();

		this.setLayout(new BorderLayout());

		this.valider = new JButton(VALIDER_TEXT);
		this.annuler = new JButton(ANNULER_TEXT);

		this.validerControleur = new CoefficientValiderControleur(coefficient,
				coefficientLines);
		this.annulerControleur = new CoefficientAnnulerControleur(coefficient);

		this.valider.addActionListener(this.validerControleur);
		this.annuler.addActionListener(this.annulerControleur);

		this.add(this.valider, BorderLayout.CENTER);
		this.add(this.annuler, BorderLayout.EAST);
	}

	public JButton getValider() {
		return valider;
	}

}
