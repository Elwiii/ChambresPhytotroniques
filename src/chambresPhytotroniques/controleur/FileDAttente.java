package chambresPhytotroniques.controleur;

import java.io.File;
import java.util.Arrays;

import chambresPhytotroniques.controleur.evenement.Evenement;
import chambresPhytotroniques.controleur.evenement.SondeHumiditeEvenement;
import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.outils.Mail;

/**
 * Gestion de la file d'attente d'evenements
 * 
 * @author Mathieu COLIN
 * 
 */
public class FileDAttente {

	private static final int NOMBRE_EVENEMENTS = 11;
	private static final int SAS = 8;
	/**
	 * Liste d'Evenement en attente<br />
	 * [sonde1, sonde2, sonde3, sonde4, sonde5, sonde6, sonde7, sonde 8, sas,
	 * rejet1, rejet2]
	 */
	private Evenement[] listEvenement;

	/**
	 * l'evenement est-il à executer<br />
	 * [sonde1, sonde2, sonde3, sonde4, sonde5, sonde6, sonde7, sonde 8, sas,
	 * rejet1, rejet2]
	 */
	private boolean[] executeEvenement;

	/**
	 * Compteur
	 */
	private int count;
	private int countMail;

	public FileDAttente() {
		this.listEvenement = new Evenement[NOMBRE_EVENEMENTS];
		for (int i = 0; i < this.listEvenement.length; i++) {
			this.listEvenement[i] = null;
		}

		this.executeEvenement = new boolean[NOMBRE_EVENEMENTS];
		for (int i = 0; i < this.executeEvenement.length; i++) {
			this.executeEvenement[i] = false;
		}

		this.count = 0;
		this.countMail = 0;
	}

	/**
	 * Effectue la prochaine action
	 */
	public void doAction() {
		while (!this.executeEvenement[this.count])
			incrementCount();

		this.listEvenement[this.count].doAction();

		incrementCount();
	}

	/**
	 * Effectue toute les actions.<br />
	 * Attention : boucle infinie!!
	 */
	public void doAllActions() {
		while (true) {
			this.doAction();
		}
	}

	private int incrementCount() {
		this.count++;
		this.count %= NOMBRE_EVENEMENTS;

		// Si on est sur Rejet1
		if (this.count == SAS + 1) {
			// S'il n'est pas scruté
			if (!this.executeEvenement[this.count]) {
				// Et qu'une des chambre 1 à 4 l'est
				if (this.executeEvenement[0] || this.executeEvenement[1]
						|| this.executeEvenement[2] || this.executeEvenement[3]) {
					((SondeHumiditeEvenement) this.listEvenement[this.count])
							.doHumiditeAction();
				}
			}
		}

		// Si on est sur Rejet2
		if (this.count == SAS + 2) {
			// S'il n'est pas scruté
			if (!this.executeEvenement[this.count]) {
				// Et qu'une des chambre 5 à 8 l'est
				if (this.executeEvenement[4] || this.executeEvenement[5]
						|| this.executeEvenement[6] || this.executeEvenement[7]) {
					((SondeHumiditeEvenement) this.listEvenement[this.count])
							.doHumiditeAction();
				}
			}
		}

		this.countMail++;
		if (this.countMail == 44) {
			this.countMail = 0;
			try {
				Mail m1 = new Mail();
				File f = File.createTempFile("releve", ".csv");
				CSV csv = new CSV(f);
				csv.ecrire(listEvenement);
				m1.sendMailWithAttachment("", f.getPath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return this.count;
	}

	/**
	 * Active l'éxecution d'un {@link Evenement}
	 * 
	 * @param numero
	 *            numéro de l'évenement
	 */
	public void executeEvenement(int numero) {
		this.executeEvenement[numero] = true;
	}

	/**
	 * Désactive l'éxécution d'un {@link Evenement}
	 * 
	 * @param numero
	 *            numéro de l'évenement
	 */
	public void removeExecuteEvenement(int numero) {
		this.executeEvenement[numero] = false;
	}

	/**
	 * Reste-t-il une seule sonde activée<br >
	 * Équivaut à :<br />
	 * Peut on encore fermer une sonde
	 * 
	 * @return nombre de sondes ouverte <= 1
	 */
	public boolean isTheLastOpen() {
		int nbOpen = 0;
		for (int i = 0; i < NOMBRE_EVENEMENTS; i++) {
			if (this.executeEvenement[i])
				nbOpen++;
		}
		return nbOpen <= 1;
	}

	/**
	 * Met l'évenement
	 * 
	 * @param numero
	 *            numéro de l'évènement
	 * @param evenement
	 *            {@link Evenement} à ajouter
	 */
	public void setEvenement(int numero, Evenement evenement) {
		this.listEvenement[numero] = evenement;
	}

	public Evenement[] getListEvenement() {
		return listEvenement;
	}

	@Override
	public String toString() {
		return "FileDAttente [listEvenement=" + Arrays.toString(listEvenement)
				+ ", executeEvenement=" + Arrays.toString(executeEvenement)
				+ ", count=" + count + "]";
	}

}
