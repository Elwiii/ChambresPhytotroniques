package chambresPhytotroniques.outils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe de gestion de fichier de configuration
 * 
 * @author Mathieu Colin
 * @author Guillaume Lolivier
 * 
 */
public class Configuration {

	/**
	 * Nom du fichier de configuration
	 */
	private static final String CONFIG_NAME = "chambrePhytotronique.properties";

	/**
	 * Commentaire à ajouter au fichier
	 */
	private static final String CONFIG_COMMENTAIRE = "Fichier de configuration du programme ChambrePhytotronique";

	/**
	 * Singleton configuration
	 */
	private static Configuration configuration;
        
	/**
	 * Liste des propriétés
	 */
	private Properties properties;

	/**
	 * Nom de la clé du nom de la sonde <br />
	 * Exemple "sonde 1" : PROPRIETY_SONDE_NAME + "1"
	 */
	private static final String PROPRIETY_SONDE_NAME = "SondeName";

	/**
	 * Nom de la clé du temps de purge d'une sonde (à concaténer avec son
	 * numéro)
	 */
	private static final String PROPRIETY_SONDE_TEMPS_PURGE = "PurgeSonde";

	/**
	 * Nom de la clé du temps d'analyse d'une sonde (à concaténer avec son
	 * numéro)
	 */
	private static final String PROPRIETY_SONDE_TEMPS_ANALYSE = "AnalyseSonde";

	/**
	 * Nom de la clé du coefficient directeur d'un analyseur (à concaténer avec
	 * son numéro)
	 */
	private static final String PROPRIETY_SONDE_A = "ASonde";

	/**
	 * Nom de la clé de l'ordonnée à l'orgigine d'un analyseur (à concaténer
	 * avec son numéro)
	 */
	private static final String PROPRIETY_SONDE_B = "BSonde";
        
        /**
         * Nom de l'adresse (à concanténer avec son numéro)
         */
        private static String PROPRIETY_ADRESSE = "Adresse";

	/**
	 * Temps d'analyse par défaut
	 */
	private static final int DEFAULT_TEMPS_ANALYSE = 0;

	/**
	 * Nom de sonde par défaut
	 */
	private static final String DEFAULT_SONDE_NAME = "Sonde";

	/**
	 * Temps de purge par défaut
	 */
	private static final int DEFAULT_TEMPS_PURGE = 0;

	/**
	 * coefficient directeur par défaut
	 */
	private static final int DEFAULT_SONDE_A = 0;

	/**
	 * Ordonnée à l'origine par défaut
	 */
	private static final int DEFAULT_SONDE_B = 0;
        
	/**
	 * Constructeur de Configuration initialisant les propriétés à partir du
	 * fichier de configuration
	 */
	private Configuration() {
		this.properties = new Properties();

		// Chargement des propriétés depuis le fichier
		try {
			this.properties.load(new FileInputStream(CONFIG_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Error.getError()
					.error("Configuration",
							"Configuration",
							"Le fichier de configuration n'existe pas ou ne peut pas être ouvert",
							e);
		} catch (IOException e) {
			e.printStackTrace();
			Error.getError().error("Configuration", "Configuration",
					"Impossible de lire dans le fichier de configuration", e);
		}
	}

	/**
	 * Retourne le singleton Configuration
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		if (configuration == null)
			configuration = new Configuration();
		return configuration;
	}

	/**
	 * Cherche une propriété dans le fichier de configuration pour une clé key
	 * 
	 * @param key
	 *            String clé de la propriété
	 * @return String valeur de la propriété
	 */
	public String getPropertie(String key) {
		return this.properties.getProperty(key);
	}

	/**
	 * Défini une propriété pour une clé key dans le fichier de configuration
	 * 
	 * @param key
	 *            String clé de la propriété
	 * @param value
	 *            String valeur de la propriété
	 */
	public void setPropertie(String key, String value) {
		this.properties.setProperty(key, value);
		try {
			this.properties.store(new FileOutputStream(CONFIG_NAME),
					CONFIG_COMMENTAIRE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Error.getError().error("Configuration", "setProperty",
					"Fichier de configuration non trouvé", e);
		} catch (IOException e) {
			e.printStackTrace();
			Error.getError().error("Configuration", "setProperty",
					"Ecriture impossible dans le fichier de configuration", e);
		}
	}

	/**
	 * Retourne le nom de la sonde en cherchant dans le ficher de configuration
	 * 
	 * @param i
	 *            numéro de la sonde
	 * @return String nom de la sonde
	 */
	public String getSondeName(int i) {
		String name = getPropertie(Configuration.PROPRIETY_SONDE_NAME + i);
		if (name == null) {
			Error.getError()
					.error("Configuration",
							"getSondeName",
							"La sonde "
									+ i
									+ " n'as pas de nom dans le fichier de configuration");
			name = DEFAULT_SONDE_NAME;
		}

		return name;
	}

	/**
	 * Enregistrer le nom d'une sonde
	 * 
	 * @param i
	 *            numéro de la sonde
	 * @param value
	 *            {@link String} nom de la sonde
	 */
	public void setSondeName(int i, String value) {
		setPropertie(PROPRIETY_SONDE_NAME + i, value);
	}

	/**
	 * Retourne le nom de la sonde
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 * @return {@link String} nom de la sonde
	 */
	public int getTempsPurge(int sonde) {
		String stringTempsPurge = this.getPropertie(PROPRIETY_SONDE_TEMPS_PURGE
				+ sonde);
		if (stringTempsPurge == null) {
			Error.getError()
					.error("Configuration",
							"getTempsPurge",
							"La sonde "
									+ sonde
									+ " n'as pas de temps de purge dans le fichier de configuration");
			stringTempsPurge = String.valueOf(DEFAULT_TEMPS_PURGE);
		}

		int tempsPurge = DEFAULT_TEMPS_PURGE;
		try {
			tempsPurge = Integer.valueOf(stringTempsPurge);
		} catch (NumberFormatException e) {
			Error.getError().error("Configuration", "getTempsPurge",
					"La valeur n'est pas un entier", e);
		}
		return tempsPurge;
	}

	/**
	 * Enregistre le temps de purge d'une sonde
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 * @param value
	 *            temps de purge (>= 0)
	 */
	public void setTempsPurge(int sonde, int value) {
		if (value >= 0)
			this.setPropertie(PROPRIETY_SONDE_TEMPS_PURGE + sonde,
					String.valueOf(value));
	}

	/**
	 * retourne le temps d'analyse d'une sonde
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 * @return temps d'analyse de la sonde
	 */
	public int getTempsAnalyse(int sonde) {
		String stringTempsAnalyse = this
				.getPropertie(PROPRIETY_SONDE_TEMPS_ANALYSE + sonde);
		if (stringTempsAnalyse == null) {
			Error.getError()
					.error("Configuration",
							"getTempsAnalyse",
							"La sonde "
									+ sonde
									+ " n'as pas de temps d'analyse dans le fichier de configuration");
			stringTempsAnalyse = String.valueOf(DEFAULT_TEMPS_ANALYSE);
		}

		int tempsAnalyse = DEFAULT_TEMPS_ANALYSE;
		try {
			tempsAnalyse = Integer.valueOf(stringTempsAnalyse);
		} catch (NumberFormatException e) {
			Error.getError().error("Configuration", "getTempsAnalyse",
					"La valeur n'est pas un entier", e);
		}
		return tempsAnalyse;
	}

	/**
	 * Enregistre le temps d'analyse d'une sonde
	 * 
	 * @param sonde
	 *            numéro de la sonde
	 * @param value
	 *            temps d'analyse de la sonde (>= 0)
	 */
	public void setTempsAnalyse(int sonde, int value) {
		if (value >= 0)
			this.setPropertie(PROPRIETY_SONDE_TEMPS_ANALYSE + sonde,
					String.valueOf(value));
	}

	/**
	 * Retourne le coefficient directeur d'un instrument de mesure
	 * 
	 * @param instrument
	 *            {@link String} nom de l'instrument
	 * @return valeur du coefficient directeur
	 */
	public double getA(String instrument) {
		String stringA = this.getPropertie(PROPRIETY_SONDE_A + instrument);
		if (stringA == null) {
			Error.getError()
					.error("Configuration",
							"getA",
							"La sonde "
									+ instrument
									+ " n'as pas de coefficient directeur dans le fichier de configuration");
			stringA = String.valueOf(DEFAULT_SONDE_A);
		}

		double A = DEFAULT_SONDE_A;
		try {
			A = Double.valueOf(stringA);
		} catch (NumberFormatException e) {
			Error.getError().error("Configuration", "getA",
					"La valeur n'est pas un entier", e);
		}
		return A;
	}

	/**
	 * Enregistre le coefficient directeur d'un instrument de mesure
	 * 
	 * @param instrument
	 *            {@link String} nom de l'instrument
	 * @param value
	 *            valeur du coefficient directeur
	 */
	public void setA(String instrument, double value) {
		this.setPropertie(PROPRIETY_SONDE_A + instrument, String.valueOf(value));
	}
        
        public void setAdresse(String num, String adresse) {
            this.setPropertie(PROPRIETY_ADRESSE + num, adresse);
        }
        
        public boolean contains(String element) {
            return !this.properties.get(element).equals("");
        }
        
        public String getAdresse(String adresse) {
            String addr = this.getPropertie(adresse);
            return addr;
        }

	public double getB(String sonde) {
		String stringB = this.getPropertie(PROPRIETY_SONDE_B + sonde);
		if (stringB == null) {
			Error.getError()
					.error("Configuration",
							"getB",
							"La sonde "
									+ sonde
									+ " n'as pas d'ordonnée à l'origine dans le fichier de configuration");
			stringB = String.valueOf(DEFAULT_SONDE_B);
		}

		double B = DEFAULT_SONDE_B;
		try {
			B = Double.valueOf(stringB);
		} catch (NumberFormatException e) {
			Error.getError().error("Configuration", "getA",
					"La valeur n'est pas un entier", e);
		}
		return B;
	}

	/**
	 * Retourne l'ordonnée à l'origine d'un instrument de mesure
	 * 
	 * @param sonde
	 *            {@link String} nom de l'instrument
	 * @param value
	 *            valeur de l'ordonnée à l'origine
	 */
	public void setB(String sonde, double value) {
		this.setPropertie(PROPRIETY_SONDE_B + sonde, String.valueOf(value));
	}

}
