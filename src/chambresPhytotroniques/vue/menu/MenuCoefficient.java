package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chambresPhytotroniques.controleur.CoefficientControleur;
import chambresPhytotroniques.vue.Fenetre;

public class MenuCoefficient extends JMenuItem {

	private static final long serialVersionUID = -933006189463259662L;

	private static final int RACCOURCIS = KeyEvent.VK_C;
	private static final KeyStroke ACCELERATOR = KeyStroke.getKeyStroke(
			KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);

	private CoefficientControleur coefficientControleur;

	public MenuCoefficient(Fenetre fenetre) {
		super("Coefficients");

		this.coefficientControleur = new CoefficientControleur(fenetre);

		this.addActionListener(this.coefficientControleur);

		this.setMnemonic(RACCOURCIS);
		this.setAccelerator(ACCELERATOR);
	}

	public CoefficientControleur getCoefficientControleur() {
		return coefficientControleur;
	}
}
