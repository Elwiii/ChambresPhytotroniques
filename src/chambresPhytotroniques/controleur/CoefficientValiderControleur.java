package chambresPhytotroniques.controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.vue.coefficient.Coefficient;
import chambresPhytotroniques.vue.coefficient.CoefficientLine;

public class CoefficientValiderControleur implements ActionListener {

	private boolean error;
	private Coefficient coefficient;
	private CoefficientLine[] coefficientLines;

	public CoefficientValiderControleur(Coefficient coefficient,
			CoefficientLine[] coefficientLines) {
		super();
		this.coefficient = coefficient;
		this.coefficientLines = coefficientLines;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.error = false;

		for (int i = 0; i < this.coefficientLines.length; i++) {
			try {
				Configuration.getConfiguration().setA(
						this.coefficientLines[i].getsonde(),
						Double.valueOf(this.coefficientLines[i].getA()));
			} catch (Exception ex) {
				// TODO: handle exception
				this.error = true;
				ex.printStackTrace();
			}

			try {
				Configuration.getConfiguration().setB(
						this.coefficientLines[i].getsonde(),
						Double.valueOf(this.coefficientLines[i].getB()));
			} catch (Exception ex) {
				// TODO: handle exception
				this.error = true;
				ex.printStackTrace();
			}
		}

		if (!error) {
			// Si y'a pas eu d'erreur, on ferme la fenêtre
			this.coefficient.dispose();
		} else {
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						Color col = coefficient.getCoefficientBoutons()
								.getValider().getBackground();
						coefficient.getCoefficientBoutons().getValider()
								.setBackground(Color.RED);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						coefficient.getCoefficientBoutons().getValider()
								.setBackground(col);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
					}
				}
			}.start();
		}
	}

}
