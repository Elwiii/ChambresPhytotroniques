package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.coefficient.Coefficient;

public class CoefficientControleur implements ActionListener {

	private Fenetre fenetre;
	private Coefficient coefficient;

	public CoefficientControleur(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.coefficient = new Coefficient(fenetre);
	}

	public void close() {
		if (this.coefficient != null)
			this.coefficient.dispose();
	}

}
