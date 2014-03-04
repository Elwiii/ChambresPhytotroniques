package chambresPhytotroniques.controleur.evenement;

import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.outils.Communication;
import chambresPhytotroniques.outils.Error;
import chambresPhytotroniques.vue.corps.Line;

public class SondeSasEvenement extends SondeHumiditeEvenement {

	private static final int NUMERO_SAS = 8;

	public SondeSasEvenement(boolean checkCO2, boolean checkO3,
			boolean checkTemperature, Line line, CSV excel) {
		super(NUMERO_SAS, checkCO2, checkO3, checkTemperature, line, excel);
	}

	public SondeSasEvenement(Line line, CSV excel) {
		this(true, true, false, line, excel);
	}

	@Override
	public Evenement doAction() {
		super.doAction();

		this.doHumiditeAction();

		return this;
	}

	@Override
	public void doHumiditeAction() {
		// Début scrutation
		this.line.getDemarrerArreterEtat().getEtat().setScrutation(true);

		Communication.getCommunication().fermetureHumiditeSas();

		// Attente 1 seconde
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			Error.getError().error("Humidite1234Evnement", "doAction",
					"Le temps d'attente n'as pas été respecté", e);
		}

		Valeur humidite = new Valeur(Communication.getCommunication()
				.getHumiditeSas());

		this.listHumidite.add(humidite);

		this.excel.ajouterUneLigneHumiditeSas(humidite);

		this.line.setListHumidite(this.listHumidite);

		// Attente 1 seconde
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			Error.getError().error("Humidite1234Evnement", "doAction",
					"Le temps d'attente n'as pas été respecté", e);
		}

		Communication.getCommunication().ouvrirHumiditeSas();

		// Fin scrutation
		this.line.getDemarrerArreterEtat().getEtat().setScrutation(false);
	}

}
