package chambresPhytotroniques.controleur;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.vue.Fenetre;

public class FenetreControleur implements WindowListener {

	private CSV excel;

	private CoefficientControleur coefficientControleur;

	private PlannificationStartControleur plannificationStartControleur;

	private ScrutationControleur scrutationControleur;

	private Fenetre fenetre;

	public FenetreControleur(CSV excel, Fenetre fenetre,
			CoefficientControleur coefficientControleur,
			PlannificationStartControleur plannificationStartControleur,
			ScrutationControleur scrutationControleur) {
		super();
		this.excel = excel;
		this.fenetre = fenetre;
		this.coefficientControleur = coefficientControleur;
		this.plannificationStartControleur = plannificationStartControleur;
		this.scrutationControleur = scrutationControleur;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		this.excel.close();
		this.coefficientControleur.close();
		this.plannificationStartControleur.close();
		this.scrutationControleur.close();
		this.fenetre.dispose();
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
