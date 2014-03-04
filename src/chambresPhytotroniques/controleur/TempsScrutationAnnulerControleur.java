package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.tempsScrutation.TempsScrutation;

public class TempsScrutationAnnulerControleur implements ActionListener {

	private TempsScrutation tempsScrutation;

	public TempsScrutationAnnulerControleur(TempsScrutation tempsScrutation) {
		this.tempsScrutation = tempsScrutation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Fermeture de la fenêtre
		this.tempsScrutation.dispose();
	}

}
