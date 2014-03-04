package chambresPhytotroniques.outils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import chambresPhytotroniques.controleur.evenement.Evenement;
import chambresPhytotroniques.controleur.evenement.SondeEvenement;
import chambresPhytotroniques.controleur.evenement.SondeHumiditeEvenement;
import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.vue.coefficient.Coefficient;
import chambresPhytotroniques.vue.corps.ListLine;
import chambresPhytotroniques.vue.tempsScrutation.TempsScrutation;

/**
 * Classe générant un fichier Excel
 * 
 * @author Colin Mathieu
 * 
 */
public class CSV {

	private static final String A_CO2 = "$B$4";
	private static final String B_CO2 = "$C$4";

	private static final String A_O3 = "$B$5";
	private static final String B_O3 = "$C$5";

	private static final String A_TEMPERATURE_I = "$B$";
	private static final String B_TEMPERATURE_I = "$C$";
	private static final int TEMPERATURE_LIGNE_0 = 6;

	private static final String A_HUMIDITE_I = A_TEMPERATURE_I;
	private static final String B_HUMIDITE_I = B_TEMPERATURE_I;
	private static final int HUMIDITE_LIGNE_0 = 17;

	/**
	 * Fichier dans lequel écrire
	 */
	private FileOutputStream out;

	/**
	 * Créer un fichier Excel au chemin et de nom name
	 * 
	 * @param name
	 *            chemin et nom du fichier
	 */
	public CSV(File file) {
		try {
			this.out = new FileOutputStream(file);
			this.ajouterEnTete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Error.getError().error("CSV", "CSV", "Fichier non trouvé", e);
		}

	}

	public CSV() {
		this(
				new File(
						"ChambresPhytotroniques_"
								+ new SimpleDateFormat(
										"yyyy-MM-dd-HH'h'mm'm'ss.SSS's'")
										.format(new Date()) + ".csv"));
	}

	/**
	 * Écrit un string dans le fichier
	 * 
	 * @param line
	 *            {@link String} à écrire
	 * @return si l'écriture s'est bien passée
	 */
	private boolean write(String line) {
		try {
			this.out.write(line.getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			Error.getError().error("CSV", "write",
					"Impossible d'écrire dans le fichier", e);
		}
		return false;

	}

	/**
	 * Écrit un retour chariot pour avoir une nouvelle ligne
	 */
	private void writeLine() {
		this.write("\n");
	}

	/**
	 * Écrit la date et laisse une cellule vide
	 * 
	 * @param valeurCO2
	 *            {@link Valeur} contenant la date
	 */
	private void writeDate(Valeur valeurCO2) {
		this.write(valeurCO2.getDate().toString());
		this.write(";;");
	}

	/**
	 * Ajoute {@link Valeur} vide (2 cellules vide)
	 */
	private void writeValeur() {
		this.write(";;");
	}

	/**
	 * Écrit une valeur
	 * 
	 * @param valeurCO2
	 *            {@link Valeur} à écrire
	 */
	private void writeValeur(Valeur valeur, String A, String B) {
		this.write(String.valueOf(valeur.getValeur()).replace('.', ','));
		this.write(";");
		this.write("=" + A + "*"
				+ String.valueOf(valeur.getValeur()).replace('.', ',') + "+"
				+ B);
		this.write(";");
	}

	private void writeValeurCO2(Valeur valeur) {
		this.writeValeur(valeur, A_CO2, B_CO2);
	}

	private void writeValeurO3(Valeur valeur) {
		this.writeValeur(valeur, A_O3, B_O3);
	}

	private void writeValeurTemperature(Valeur valeur, int i) {
		this.writeValeur(valeur, A_TEMPERATURE_I + (TEMPERATURE_LIGNE_0 + i),
				B_TEMPERATURE_I + (TEMPERATURE_LIGNE_0 + i));
	}

	private void writeValeurHumidite(Valeur valeur, int i) {
		this.writeValeur(valeur, A_HUMIDITE_I + (HUMIDITE_LIGNE_0 + (i-8)),
				B_HUMIDITE_I + (HUMIDITE_LIGNE_0 + (i-8)));
	}

	/**
	 * Écrit les humidités au bon endroit (pour avec les autres valeurs vide)
	 * 
	 * @param sondeHumidite
	 *            {@link SondeHumidite} de la sonde
	 * @param valeurHumidite
	 *            {@link Valeur} de l'humidité
	 * @param i
	 */
	private void writeValeurSondeHumidite(Valeur valeurHumidite, int i) {
		this.writeValeur();
		this.writeValeur();
		this.writeValeur();
		this.writeValeurHumidite(valeurHumidite, i);
	}

	/**
	 * Écrit le décalage de la sonde pour aligner avec les entête de tableau
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 */
	private void writeDecalageSonde(int sonde) {
		for (int i = 0; i < sonde; i++) {
			this.writeValeur();
			this.writeValeur();
			this.writeValeur();
			this.writeValeur();
		}
	}

	/**
	 * Ajoute l'entête
	 */
	private void ajouterEnTete() {
		this.write("Résultat des chambre phytotroniques");
		this.writeLine();
		// Coefficients
		this.write("Tableau des Coefficients (sous forme A*x+B) :");
		this.writeLine();

		this.write(";A;B");
		this.writeLine();

		for (int i = 0; i < Coefficient.LINES_NAME.length; i++) {
			this.write(Coefficient.LINES_NAME[i]);
			this.write(";");

			this.write(String.valueOf(
					Configuration.getConfiguration().getA(
							Coefficient.LINES_NAME[i])).replace('.', ','));
			this.write(";");

			this.write(String.valueOf(
					Configuration.getConfiguration().getB(
							Coefficient.LINES_NAME[i])).replace('.', ','));

			this.writeLine();
		}

		for (int i = 0; i < ListLine.NB_SONDE; i++) {
			this.write(Coefficient.TEMPERATURE_NAME + i);
			this.write(";");

			this.write(String.valueOf(
					Configuration.getConfiguration().getA(
							Coefficient.TEMPERATURE_NAME + i))
					.replace('.', ','));
			this.write(";");

			this.write(String.valueOf(
					Configuration.getConfiguration().getB(
							Coefficient.TEMPERATURE_NAME + i))
					.replace('.', ','));

			this.writeLine();
		}

		for (int i = 0; i < Coefficient.NB_HUMIDITE; i++) {
			this.write(Coefficient.HUMIDITE_NAME + i);
			this.write(";");

			this.write(String.valueOf(
					Configuration.getConfiguration().getA(
							Coefficient.HUMIDITE_NAME + i)).replace('.', ','));
			this.write(";");

			this.write(String.valueOf(
					Configuration.getConfiguration().getB(
							Coefficient.HUMIDITE_NAME + i)).replace('.', ','));

			this.writeLine();
		}

		this.writeLine();
		this.writeLine();

		// Temps de scrutation
		this.write("Temps de scrutation :");
		this.writeLine();

		this.write(";Temps de purge;Temps d'analyse");
		this.writeLine();
		for (int i = 0; i < TempsScrutation.LIGNES_SONDES; i++) {
			this.write(Configuration.getConfiguration().getSondeName(i));
			this.write(";");

			this.write(String.valueOf(Configuration.getConfiguration()
					.getTempsPurge(i)));
			this.write(";");

			this.write(String.valueOf(Configuration.getConfiguration()
					.getTempsAnalyse(i)));

			this.writeLine();
		}

		this.writeLine();
		this.writeLine();

		// Entête de colonnes
		this.write("Date;;");
		for (int i = 0; i < ListLine.NB_SONDE; i++) {
			this.write(Configuration.getConfiguration().getSondeName(i)
					+ ";;;;;;;;");
		}
		this.writeLine();

		this.write(";;");
		for (int i = 0; i < ListLine.NB_SONDE; i++) {
			this.write(";CO2;;O3;;Température;;Humidité;");
		}
		this.writeLine();
	}

	/**
	 * Écrit et ferme le fichier Excel
	 * 
	 * @param evenements
	 *            liste des évènements
	 */
	public void ecrire(Evenement[] evenements) {
		try {
			int[] iEvenements = new int[evenements.length];
			for (int i = 0; i < iEvenements.length; i++) {
				iEvenements[i] = 0;
			}

			while (!isFin(iEvenements, evenements)) {
				int min = minumum(iEvenements, evenements);
				if (min < 0) {
					break;
				}

				ajouterUneLigne(min, evenements[min], iEvenements[min]);

				iEvenements[min]++;
			}
		} catch (IndexOutOfBoundsException e) {
			// Aucune valeur
			e.printStackTrace();
		}

		close();
	}

	/**
	 * Ajoute une ligne et ses valeurs, puis passe à la suivante<br />
	 * La {@link Date} de la <code>valeurCO2</code> sera pris en compte
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 * @param valeurCO2
	 *            {@link Valeur} de CO2
	 * @param valeurO3
	 *            {@link Valeur} d'O3
	 * @param valeurTemperature
	 *            {@link Valeur} de température
	 */
	public void ajouterUneLigneCO2O3Temperature(int sonde, Valeur valeurCO2,
			Valeur valeurO3, Valeur valeurTemperature) {
		this.writeDate(valeurCO2);

		this.writeDecalageSonde(sonde);

		this.writeValeurCO2(valeurCO2);
		this.writeValeurO3(valeurO3);
		this.writeValeurTemperature(valeurTemperature, sonde);

		this.writeLine();
	}

	/**
	 * Ajoute une ligne et ses valeurs, puis passe à la suivante<br />
	 * La {@link Date} de la <code>valeurCO2</code> sera pris en compte
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 * @param valeurCO2
	 *            {@link Valeur} de CO2
	 * @param valeurO3
	 *            {@link Valeur} d'O3
	 */
	public void ajouterUneLigneCO2O3(int sonde, Valeur valeurCO2,
			Valeur valeurO3) {
		this.writeDate(valeurCO2);

		this.writeDecalageSonde(sonde);

		this.writeValeurCO2(valeurCO2);
		this.writeValeurO3(valeurO3);

		this.writeLine();
	}

	/**
	 * Ajoute une ligne et ses valeurs pour l'humidité du Rejet1 et Rejet2, puis
	 * passe à la suivante
	 * 
	 * @param sondeHumidite
	 *            {@link SondeHumidite} de la sonde
	 * @param valeurHumidite
	 *            {@link Valeur} d'humidité
	 */
	public void ajouterUneLigneHumiditeRejet(int sondeHumidite,
			Valeur valeurHumidite) {
		this.writeDate(valeurHumidite);

		this.writeDecalageSonde(sondeHumidite);

		this.writeValeurSondeHumidite(valeurHumidite, sondeHumidite - 8);

		this.writeLine();
	}

	/**
	 * Ajoute une ligne et ses valeurs pour l'humidité du Sas, puis passe à la
	 * suivante
	 * 
	 * @param valeurHumidite
	 *            {@link Valeur} d'humidité
	 */
	public void ajouterUneLigneHumiditeSas(Valeur valeurHumidite) {
		this.writeDate(valeurHumidite);

		for (int i = 0; i < 11; i++) {
			this.writeValeurSondeHumidite(valeurHumidite, 0);
		}

		this.writeLine();
	}

	private void ajouterUneLigneCO2O3Humidite(int i, Valeur valeurCO2,
			Valeur valeurO3, Valeur valeurHumidite, int iHumidite) {
		this.writeDate(valeurCO2);

		this.writeDecalageSonde(i);

		this.writeValeurCO2(valeurCO2);
		this.writeValeurO3(valeurO3);
		this.writeValeur();
		this.writeValeurHumidite(valeurHumidite, iHumidite);

		this.writeLine();
	}

	/**
	 * Ajout d'une ligne<br />
	 * S'il n'y a pas toute les valeurs, on écrit pas
	 * 
	 * @param i
	 *            numéro de la sonde
	 * @param evenement
	 *            {@link Evenement}
	 * @param iValeur
	 *            position de la valeur à écrire
	 */
	public void ajouterUneLigne(int i, Evenement evenement, int iValeur) {
		// Si les listes n'ont pas la bonne taille
		if (evenement.getListCO2().size() <= iValeur
				|| evenement.getListO3().size() <= iValeur)
			return;

		if (i < 8) {
			// Chambre

			// Si la liste des température n'a pas la bonne taille
			if (evenement.getListTemperature().size() <= iValeur)
				return;

			ajouterUneLigneCO2O3Temperature(i, ((SondeEvenement) evenement)
					.getListCO2().get(iValeur), ((SondeEvenement) evenement)
					.getListO3().get(iValeur), ((SondeEvenement) evenement)
					.getListTemperature().get(iValeur));
		} else {
			// Sas

			// Si la liste humidité n'a pas la bonne taille
			if (((SondeHumiditeEvenement) evenement).getListHumidite().size() <= iValeur)
				return;

			ajouterUneLigneCO2O3Humidite(i,
					evenement.getListCO2().get(iValeur), evenement.getListO3()
							.get(iValeur), ((SondeHumiditeEvenement) evenement)
							.getListHumidite().get(iValeur), i);
			ajouterUneLigneHumiditeRejet(
					i,
					((SondeHumiditeEvenement) evenement).getListHumidite().get(
							iValeur));
		}
	}

	/**
	 * Ferme le fichier
	 */
	public void close() {
		try {
			this.out.flush();
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
			Error.getError().error("CSV", "close",
					"Impossible de fermer le fichier", e);
		}
	}

	/**
	 * Cherche l'évenement le plus récent
	 * 
	 * @param iEvenements
	 *            index des événements à prendre en compte
	 * @param evenements
	 *            Événements
	 * @return index des événements le plus récent
	 */
	private int minumum(int[] iEvenements, Evenement[] evenements) {
		int min = -1;
		Date minVal = new Date();

		for (int i = 0; i < iEvenements.length; i++) {
			if (minVal
					.compareTo(getDateEvenement(iEvenements[i], evenements[i])) > 0) {
				min = i;
				minVal = getDateEvenement(iEvenements[i], evenements[i]);
			}
		}
		return min;
	}

	private Date getDateEvenement(int i, Evenement evenement) {
		if (evenement.getListCO2().size() <= i) {
			return new Date();
		}
		return evenement.getListCO2().get(i).getDate();
	}

	private boolean isFin(int[] iEvenements, Evenement[] evenements) {
		boolean isFin = false;
		for (int i = 0; i < iEvenements.length; i++) {
			if (evenements[i].getListCO2().size() < iEvenements[i]) {
				isFin = true;
			}
		}

		return isFin;
	}

}
