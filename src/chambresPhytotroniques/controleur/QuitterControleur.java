package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitterControleur implements ActionListener {

	private FenetreControleur fenetreControleur;

	public QuitterControleur(FenetreControleur fenetreControleur) {
		super();
		this.fenetreControleur = fenetreControleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.fenetreControleur.windowClosed(null);
	}

}
