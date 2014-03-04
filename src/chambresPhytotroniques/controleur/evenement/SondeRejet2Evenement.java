package chambresPhytotroniques.controleur.evenement;

import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.outils.Communication;
import chambresPhytotroniques.outils.Error;
import chambresPhytotroniques.vue.corps.Line;
import chambresPhytotroniques.vue.corps.ListLine;

public class SondeRejet2Evenement extends SondeHumiditeEvenement {

	private static final int NUMERO_REJET_2 = 10;
	private static final int SONDE_DEBUT = 4;
	private static final int SONDE_FIN = 8;
	private ListLine listLine;

	public SondeRejet2Evenement(boolean checkCO2, boolean checkO3,
			boolean checkTemperature, ListLine listLine, Line line, CSV excel) {
		super(NUMERO_REJET_2, checkCO2, checkO3, checkTemperature, line, excel);
		this.listLine = listLine;
	}

	public SondeRejet2Evenement(ListLine listLine, Line line, CSV excel) {
		super(NUMERO_REJET_2, true, true, false, line, excel);
		this.listLine = listLine;
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
		for (int i = SONDE_DEBUT; i < SONDE_FIN; i++) {
			this.listLine.getLine(i).getDemarrerArreterEtat().getEtat()
					.setScrutation(true);
		}

		Communication.getCommunication().fermetureHumidite5678();

		// Attente 1 seconde
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			Error.getError().error("SondeRejet2Evenement", "doAction",
					"Le temps d'attente n'as pas été respecté", e);
		}

		Valeur humidite = new Valeur(Communication.getCommunication()
				.getHumidite5678());

		this.listHumidite.add(humidite);

		this.excel.ajouterUneLigneHumiditeRejet(NUMERO_REJET_2, humidite);

		this.line.setListHumidite(this.listHumidite);
		for (int i = SONDE_DEBUT; i < SONDE_FIN; i++) {
			this.listLine.getLine(i).setListHumidite(this.listHumidite);
		}

		// Attente 1 seconde
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			Error.getError().error("SondeRejet2Evenement", "doAction",
					"Le temps d'attente n'as pas été respecté", e);
		}

		Communication.getCommunication().ouvrirHumidite5678();

		// Fin scrutation
		this.line.getDemarrerArreterEtat().getEtat().setScrutation(false);
		for (int i = SONDE_DEBUT; i < SONDE_FIN; i++) {
			this.listLine.getLine(i).getDemarrerArreterEtat().getEtat()
					.setScrutation(false);
		}
	}

}
