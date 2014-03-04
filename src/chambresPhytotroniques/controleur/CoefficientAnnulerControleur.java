package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.coefficient.Coefficient;

public class CoefficientAnnulerControleur implements ActionListener {

	private Coefficient coefficient;

	public CoefficientAnnulerControleur(Coefficient coefficient) {
		super();
		this.coefficient = coefficient;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Fermeture de la fenêtre
		this.coefficient.dispose();
	}

}
