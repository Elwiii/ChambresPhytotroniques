package chambresPhytotroniques.controleur.evenement;

import java.util.LinkedList;

import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.outils.Communication;
import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.outils.Error;
import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.vue.corps.CompteurTempsReel;
import chambresPhytotroniques.vue.corps.Line;
import chambresPhytotroniques.vue.corps.TempsReel;
import javax.swing.JLabel;

public class SondeEvenement implements Evenement {

	/**
	 * {@link Line} à updater (TempsReel + Graphique)
	 */
	protected Line line;

	/**
	 * Numéro de la sonde
	 */
	private int numeroSonde;

	/**
	 * Liste des valeurs de CO2
	 */
	private LinkedList<Valeur> listCO2;

	/**
	 * Liste des valeurs d'O3
	 */
	private LinkedList<Valeur> listO3;

	/**
	 * Liste des valeurs de température
	 */
	private LinkedList<Valeur> listTemperature;

	/**
	 * Cherche la valeur de CO2?
	 */
	private boolean checkCO2;

	/**
	 * Cherche la valeur d'O3?
	 */
	private boolean checkO3;

	/**
	 * Cherche la valeur de température?
	 */
	private boolean checkTemperature;
        
        private CompteurTempsReel compteur;

	protected CSV excel;

	/**
	 * Ajoute un évènement de sonde
	 * 
	 * @param numeroSonde
	 *            numéro de la sonde
	 * @param tempsAttente
	 *            temps d'attente avant le relevé de température
	 * @param nombreDePrise
	 *            nombre de mesures
	 * @param tempsReel
	 *            {@link TempsReel} correspondant à updater
	 */
	public SondeEvenement(int numeroSonde, Line line, CSV excel) {
		this.numeroSonde = numeroSonde;

		this.line = line;
		this.excel = excel;

		this.checkCO2 = true;
		this.checkO3 = true;
		this.checkTemperature = true;
                
		if (this.checkCO2)
			this.listCO2 = new LinkedList<Valeur>();

		if (this.checkO3)
			this.listO3 = new LinkedList<Valeur>();

		if (this.checkTemperature)
			this.listTemperature = new LinkedList<Valeur>();

	}

	/**
	 * Ajoute un évènement de sonde
	 * 
	 * @param numeroSonde
	 *            numéro de la sonde
	 * @param tempsAttente
	 *            temps d'attente avant le relevé de température
	 * @param nombreDePrise
	 *            nombre de mesures
	 * @param checkCO2
	 *            regarde le CO2
	 * @param checkHumidite
	 *            regarde l'humidité
	 * @param checkO3
	 *            regarde l'O3
	 * @param checkTemperature
	 *            regarde la température
	 * @param tempsReel
	 *            {@link TempsReel} correspondant à updater
	 */
	public SondeEvenement(int numeroSonde, boolean checkCO2, boolean checkO3,
			boolean checkTemperature, Line line, CSV excel) {

		this(numeroSonde, line, excel);

		this.checkCO2 = checkCO2;
		this.checkO3 = checkO3;
		this.checkTemperature = checkTemperature;
	}

	@Override
	public Evenement doAction() {
		// Début scrutation
		this.line.getDemarrerArreterEtat().getEtat().setScrutation(true);
                
                this.compteur = this.line.getListLine().getScrollLine().getCorps().getCompteur().getCtr();
                
		// Fermeture de EV3V
		Communication.getCommunication().electrovanne3Voies(false);

		// Positionne à la valve numeroSonde
		Communication.getCommunication().positionnementValve(numeroSonde + 1);
                
                compteur.setTempsTotal(Configuration.getConfiguration().getTempsAnalyse(numeroSonde)+Configuration.getConfiguration().getTempsPurge(numeroSonde));
                compteur.start();

		// Attente purge
		try {
			Thread.sleep(Configuration.getConfiguration().getTempsPurge(
					numeroSonde) * 1000);
		} catch (InterruptedException e) {
			Error.getError().error("SondeEvenement", "doAction",
					"Le temps d'attente n'as pas été respecté", e);
		}

		// Ouverture E3V
		Communication.getCommunication().electrovanne3Voies(true);

		// Attente analyse
		try {
			Thread.sleep(Configuration.getConfiguration().getTempsAnalyse(
					numeroSonde) * 1000);
		} catch (InterruptedException e) {
			Error.getError().error("SondeEvenement", "doAction",
					"Le temps d'attente n'as pas été respecté", e);
		}

		Valeur CO2 = new Valeur(), O3 = new Valeur(), Temp = new Valeur();

		// Récupération des valeurs et ajout dans les listes correspondantes
		if (checkCO2) {
			CO2 = new Valeur(Communication.getCommunication().getCO2Value());
			this.listCO2.addLast(CO2);
			this.line.setListCo2(this.listCO2);
		}

		if (checkO3) {
			O3 = new Valeur(Communication.getCommunication().getO3Value());
			this.listO3.addLast(O3);
			this.line.setListO3(this.listO3);
		}

		if (checkTemperature) {
			Temp = new Valeur(Communication.getCommunication()
					.getTemperatureValue(numeroSonde + 1));
			this.listTemperature.addLast(Temp);
			this.line.setListTempearture(this.listTemperature);
		}

		this.excel.ajouterUneLigneCO2O3Temperature(numeroSonde, CO2, O3, Temp);

		// Fermeture de l'EV3V
		Communication.getCommunication().electrovanne3Voies(false);

		// Fermeture valve courante
		Communication.getCommunication().positionnementValve(0);

		// Fin scrutation
		this.line.getDemarrerArreterEtat().getEtat().setScrutation(false);

		return this;
	}

	@Override
	public LinkedList<Valeur> getListCO2() {
		return listCO2;
	}

	@Override
	public LinkedList<Valeur> getListO3() {
		return listO3;
	}

	@Override
	public LinkedList<Valeur> getListTemperature() {
		return listTemperature;
	}

	@Override
	public String toString() {
		return "SondeEvenement [numeroSonde=" + numeroSonde + "]";
	}

}
