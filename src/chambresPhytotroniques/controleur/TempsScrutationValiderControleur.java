package chambresPhytotroniques.controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.vue.tempsScrutation.TempsScrutation;
import chambresPhytotroniques.vue.tempsScrutation.TempsScrutationLine;

public class TempsScrutationValiderControleur implements ActionListener {

	private TempsScrutationLine[] tempsScrutationLines;
	private boolean error;
	private TempsScrutation tempsScrutation;

	public TempsScrutationValiderControleur(TempsScrutation tempsScrutation,
			TempsScrutationLine[] tempsScrutationLines) {
		super();
		this.tempsScrutation = tempsScrutation;
		this.tempsScrutationLines = tempsScrutationLines;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Ajout des valeurs dans le .properties
		this.error = false;

		// Pour chaque sonde "Normale"s
		for (int i = 0; i < TempsScrutation.LIGNES_SONDES; i++) {

			try {
				Configuration.getConfiguration().setTempsAnalyse(
						i,
						Integer.valueOf(this.tempsScrutationLines[i]
								.getTempsAnalyse()));
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
				this.error = true;
			}

			try {
				Configuration.getConfiguration().setTempsPurge(
						i,
						Integer.valueOf(this.tempsScrutationLines[i]
								.getTempsPurge()));
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
				this.error = true;
			}

		}

		if (!error) {
			// Si y'a pas eu d'erreur, on ferme la fenêtre
			this.tempsScrutation.dispose();
		} else {
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						Color col = tempsScrutation.getTempsScrutationBoutons()
								.getValider().getBackground();
						tempsScrutation.getTempsScrutationBoutons()
								.getValider().setBackground(Color.RED);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						tempsScrutation.getTempsScrutationBoutons()
								.getValider().setBackground(col);
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
