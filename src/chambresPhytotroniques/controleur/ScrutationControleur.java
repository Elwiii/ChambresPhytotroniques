package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.tempsScrutation.TempsScrutation;

public class ScrutationControleur implements ActionListener {

	private Fenetre fenetre;

	private TempsScrutation tempsScrutation;

	public ScrutationControleur(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.tempsScrutation = new TempsScrutation(fenetre);
	}

	public void close() {
		if (this.tempsScrutation != null)
			this.tempsScrutation.dispose();
	}

}
