package chambresPhytotroniques.outils;

import chambresPhytotroniques.outils.Fluke2625A.CommunicationFluke2625A;
import chambresPhytotroniques.outils.Sealevel470U.CommunicationSealevel470U;

/**
 * Classe de gestion de la communication avec le port série
 * 
 * @author Mathieu Colin
 * @author Guillaume Lolivier
 * 
 */
public abstract class Communication {

	

	/**
	 * Singleton de communication
	 */
	private static Communication communication;

        /**
	 * Retourne le Communication (Singleton)
	 * 
	 * @return Communication le singleton à utiliser
	 */
	public static Communication getCommunication() {
		if (communication == null){
			 //communication = new  CommunicationFluke2625A();
                         communication = new  CommunicationSealevel470U();
                }
		return communication;
	}


	public double getHumiditeSas() {
		return this.getValue(11);
	}

	/**
	 * Cherche la valeur de l'humidité pour les cabine 1 à 4 (même analyse)
	 * 
	 * @return valeur en Volt
	 */
	public double getHumidite1234() {
		return this.getValue(12);
	}

	/**
	 * Cherche la valeur de l'humidité pour les cabine 5 à 8 (même analyse)
	 * 
	 * @return valeur en Volt
	 */
	public double getHumidite5678() {
		return this.getValue(13);
	}
        
	/**
	 * DO_LEVEL 6,1
	 */
	public abstract void ouvrireHumidite1234();

	/**
	 * DO_LEVEL 6,0
	 */
	public abstract void fermerHumidite1234();

	/**
	 * DO_LEVEL 5,1
	 */
	public abstract void ouvrirHumidite5678();

	/**
	 * DO_LEVEL 5,0
	 */
	public abstract void fermetureHumidite5678();

	/**
	 * DO_LEVEL 0,0<br />
	 * DO_LEVEL 1,1<br />
	 * DO_LEVEL 2,0<br />
	 * DO_LEVEL 3,0<br />
	 * DO_LEVEL 7,0
	 */
	public abstract void fermetureHumiditeSas();

	/**
	 * DO_LEVEL 7,1
	 */
	public abstract void ouvrirHumiditeSas();

	/**
	 * Demande et retourne la valeur de l'humitité
	 * 
	 * @param numCabine
	 * 
	 * @return valeur de l'humidité
	 */
	public abstract double getValue(int numCabine);

	
        /**
	 * Demande et retourne la valeur du CO2
	 * 
	 * @return valeur du CO2
	 */
	public double getCO2Value() {
		return this.getValue(15);
	}

	/**
	 * Demande et retourne la valeur de température
	 * 
	 * @param numeroCabine
	 *            numéro de la cabine à analyser (numéro de sonde + 1)
	 * 
	 * @return valeur de température
	 */
	public double getTemperatureValue(int numeroCabine) {
		if (numeroCabine >= 9)
			return -1;

		return this.getValue(numeroCabine);
	}

	/**
	 * Demande et retourne la valeur de l'O3
	 * 
	 * @param numCabine
	 * 
	 * @return valeur de l'O3
	 */
	public double getO3Value() {
		return this.getValue(14);
	}

	/**
	 * Positionne à la valve x
	 * 
	 * @param x
	 *            numéro de la valve (de 1 à 12)<br />
	 *            ou 0 = fermeture
	 */
	public abstract void positionnementValve(int x);

	/**
	 * Positionne à la valve fermé
	 */
	public abstract void fermetureTouteElectrovanne();

	/**
	 * Positionne à la valve 1
	 */
	public abstract void positionnementValve1();

	/**
	 * Positionne à la valve 2
	 */
	public abstract void positionnementValve2();

	/**
	 * Positionne à la valve 3
	 */
	public abstract void positionnementValve3();

	/**
	 * Positionne à la valve 4
	 */
	public abstract void positionnementValve4();

	/**
	 * Positionne à la valve 5
	 */
	public abstract void positionnementValve5();

	/**
	 * Positionne à la valve 6
	 */
	public abstract void positionnementValve6();

	/**
	 * Positionne à la valve 7
	 */
	public abstract void positionnementValve7();

	/**
	 * Positionne à la valve 8
	 */
	public abstract void positionnementValve8();

	/**
	 * Positionne au sas
	 */
	public abstract void positionnementSas();

	/**
	 * Positionne au rejet 1
	 */
	public abstract void positionnementRejet1();

	/**
	 * Positionne au rejet 2
	 */
	public abstract void positionnementRejet2();

	/**
	 * Ouvre ou ferme l'electrovanne 3 voies
	 * 
	 * @param ouvert
	 *            vrai si ouverture
	 */
	public void electrovanne3Voies(boolean ouvert) {
		if (ouvert)
			ouvertureElectrovanne3Voies();
		else
			fermetureElectrovanne3Voies();
	}

	/**
	 * Ouvre l'electrovanne 3 voies
	 */
	public abstract void ouvertureElectrovanne3Voies();

	/**
	 * Ferme l'electrovanne 3 voies
	 */
	public abstract void fermetureElectrovanne3Voies();

}
