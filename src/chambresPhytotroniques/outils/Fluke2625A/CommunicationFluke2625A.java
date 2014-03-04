/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.outils.Fluke2625A;

import chambresPhytotroniques.outils.Communication;
import chambresPhytotroniques.outils.CommunicationException;
import chambresPhytotroniques.outils.Error;
import com.sun.comm.Win32Driver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

/**
 *
 * @author Mathieu Colin
 * @author Guillaume Lolivier
 */
public class CommunicationFluke2625A extends Communication{
    /**
	 * Débit en baud et port série
	 */
	private static final int BAUD_RATE = 9600;

	/**
	 * Format d'encodage de caractères
	 */
	private static final int DATA_BITS = SerialPort.DATABITS_8;

	/**
	 * Nombre de bits de stop
	 */
	private static final int STOP_BITS = SerialPort.STOPBITS_1;

	/**
	 * Nombre de bits de parité
	 */
	private static final int PARITY = SerialPort.PARITY_NONE;

	/**
	 * FlowControl du port
	 */
	private static final int FLOW_CONTROL = SerialPort.FLOWCONTROL_NONE;

	/**
	 * Nom du port série pour la communication
	 */
	private static final String NOM_PORT = "COM1";

	/**
	 * Nom de l'application pour la demande du port série
	 */
	private static final String NOM_APPLICATION = "ChambresPhytotronique";

	/**
	 * Time out sur la connection au port série
	 */
	private static final int TIMEOUT = 10000;

	/**
	 * Singleton de communication
	 */
	private static Communication communication;

	/**
	 * Driver
	 */
	private Win32Driver win32Driver;

	/**
	 * Identifieur du port
	 */
	private CommPortIdentifier portId;

	/**
	 * Port série
	 */
	private SerialPort port;

	/**
	 * Permet l'écriture vers le port série
	 */
	private PrintWriter writer;

	/**
	 * Permet la lecture dans le port série
	 */
	private BufferedReader reader;
	
    
    
        /**
	 * Créer et initialise le port série
         * @deprecated
	 */
        
	public CommunicationFluke2625A() {

		// Initialisation de driver
		this.win32Driver = new Win32Driver();
		this.win32Driver.initialize();

		// Création du port
		try {
			portId = CommPortIdentifier.getPortIdentifier(NOM_PORT);
		} catch (NoSuchPortException e) {
			Error.getError()
					.error("CommunicationFluke2625A",
							"CommunicationFluke2625A",
							"Impossible de récupérer l'identifiant de le port série",
							e);
		}

		// Prise en main sur le port
		try {
			port = (SerialPort) portId.open(NOM_APPLICATION, TIMEOUT);
		} catch (PortInUseException e) {
			Error.getError().error("CommunicationFluke2625A", "CommunicationFluke2625A",
					"Impossible d'ouvrir le port série", e);
		}

		// Initialisation des paramètres de communication
		try {
			port.setFlowControlMode(FLOW_CONTROL);
		} catch (UnsupportedCommOperationException e1) {
			Error.getError().error("CommunicationFluke2625A", "CommunicationFluke2625A",
					"Impossible de préciser le flowControl", e1);
		}
		try {
			port.setSerialPortParams(BAUD_RATE, DATA_BITS, STOP_BITS, PARITY);
		} catch (UnsupportedCommOperationException e) {
			Error.getError().error("CommunicationFluke2625A", "CommunicationFluke2625A",
					"Impossible de préciser le type de connection", e);
		}

		// Création du reader et writer
		try {
			writer = new PrintWriter(port.getOutputStream());
		} catch (IOException e) {
			Error.getError().error("CommunicationFluke2625A", "CommunicationFluke2625A",
					"Impossible de créer le writer du port série", e);
		}
		try {
			reader = new BufferedReader(new InputStreamReader(
					port.getInputStream()));
		} catch (IOException e) {
			Error.getError().error("CommunicationFluke2625A", "CommunicationFluke2625A",
					"Impossible de créer le reader du port série", e);
		}

		initialisation();
	}

        /**
         * @deprecated
         */
	private void initialisation() {
		// Désactivation de l'echo (répétition de la commande)
		this.envoi("ECHO 0");

		// Initialisation de la station
		this.envoi("*RST");

		// Initialisation des canaux
		this.envoi("FUNC 1,VDC,AUTO");
		this.envoi("FUNC 2,VDC,AUTO");
		this.envoi("FUNC 3,VDC,AUTO");
		this.envoi("FUNC 4,VDC,AUTO");
		this.envoi("FUNC 5,VDC,AUTO");
		this.envoi("FUNC 6,VDC,AUTO");
		this.envoi("FUNC 7,VDC,AUTO");
		this.envoi("FUNC 8,VDC,AUTO");

		this.envoi("FUNC 11,VDC,AUTO");
		this.envoi("SCALE_MB 11,10,0.1,7");

		this.envoi("FUNC 12,VDC,AUTO");
		this.envoi("SCALE_MB 12,10,0.1,7");

		this.envoi("FUNC 13,VDC,AUTO");
		this.envoi("SCALE_MB 13,10,0.1,7");

		this.envoi("FUNC 14,VDC,AUTO");
		this.envoi("SCALE_MB 14,10,0.1,7");

		this.envoi("FUNC 15,VDC,AUTO");
		this.envoi("SCALE_MB 15,10,0.1,7");
	}
        
        /**
	 * Envoi d'une commande puis du \r
	 * @deprecated
	 * @param commande
	 *            String à envoyer
	 * @return String réponse (ou null si aucun message)
	 */
	private String envoi(String commande) {
		return this.envoiCommande(commande + "\r");
	}

	/**
	 * Envoi d'une commande
	 * @deprecated
	 * @param commande
	 *            String à envoyer
	 * @return String réponse (ou vide si aucun message)
	 */
	private String envoiCommande(String commande) {
		this.writer.write(commande);
		this.writer.flush();

		String line = null;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);

				if (line.equals("=>"))
					break;
				if (line.equals("?>") || line.equals("!>"))
					throw new CommunicationException(
							"Erreur de communication, commande=" + commande
									+ ", reponse=" + line);
			}
		} catch (IOException e) {
			sb.append(line);
			Error.getError().error(
					"CommunicationFluke2625A",
					"envoiCommande",
					"Erreur dans la commande " + commande + ", recu "
							+ sb.toString(), e);
		} catch (CommunicationException e) {
			sb.append(line);
			Error.getError().error(
					"CommunicationFluke2625A",
					"envoiCommande",
					"La centrale n'as pas compris la commande " + commande
							+ ", et a renvoyé" + sb.toString(), e);
		}

		return sb.toString();
	}

        

	/**
	 * DO_LEVEL 6,1
         * @deprecated
	 */
        @Override
	public void ouvrireHumidite1234() {
		this.envoi("DO_LEVEL 6,1");
	}

	/**
	 * DO_LEVEL 6,0
         * @deprecated
	 */
        @Override
	public void fermerHumidite1234() {
		this.envoi("DO_LEVEL 6,0");
	}

	/**
	 * DO_LEVEL 5,1
         * @deprecated
	 */
        @Override
	public void ouvrirHumidite5678() {
		this.envoi("DO_LEVEL 5,1");
	}

	/**
	 * DO_LEVEL 5,0
         * @deprecated
	 */
        @Override
	public void fermetureHumidite5678() {
		this.envoi("DO_LEVEL 5,0");
	}

	/**
	 * DO_LEVEL 0,0<br />
	 * DO_LEVEL 1,1<br />
	 * DO_LEVEL 2,0<br />
	 * DO_LEVEL 3,0<br />
	 * DO_LEVEL 7,0
         * @deprecated
	 */
        @Override
	public void fermetureHumiditeSas() {
		this.envoi("DO_LEVEL 7,1");

		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,0");
		this.envoi("DO_LEVEL 3,0");
		this.envoi("DO_LEVEL 7,0");

	}

	/**
	 * DO_LEVEL 7,1
         * @deprecated
	 */
        @Override
	public void ouvrirHumiditeSas() {
		this.envoi("DO_LEVEL 7,1");
	}

	/**
	 * Demande et retourne la valeur de l'humitité
	 * 
	 * @param numCabine
	 * @deprecated
	 * @return valeur de l'humidité
	 */
        @Override
	public double getValue(int numCabine) {
		this.envoi("MON 1," + numCabine);
		this.envoi("*TRG");

		String retour = this.envoi("LAST? " + numCabine);
		return Double.valueOf(retour.substring(0, retour.length() - 5));
	}

	

	/**
	 * Positionne à la valve x
	 * @deprecated
	 * @param x
	 *            numéro de la valve (de 1 à 12)<br />
	 *            ou 0 = fermeture
	 */
        @Override
	public void positionnementValve(int x) {
		// Mise à zéro du bit de validation du démultiplexeur
		this.envoi("DO_LEVEL 7,1");

		switch (x) {
		case 0:
		case 1:
			positionnementValve1();
			break;

		case 2:
			positionnementValve2();
			break;

		case 3:
			positionnementValve3();
			break;

		case 4:
			positionnementValve4();
			break;

		case 5:
			positionnementValve5();
			break;

		case 6:
			positionnementValve6();
			break;

		case 7:
			positionnementValve7();
			break;

		case 8:
			positionnementValve8();
			break;

		case 9:
			positionnementSas();
			break;

		case 10:
			positionnementRejet1();
			break;

		case 11:
			positionnementRejet2();
			break;

		default:
			Error.getError().error("CommunicationFluke2625A", "positionnementValve",
					"Valve " + x + " inexistante");
			break;
		}
	}

	/**
	 * Positionne à la valve fermé
         * @deprecated
	 */
        @Override
	public void fermetureTouteElectrovanne() {
		this.envoi("DO_LEVEL 0,1");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 4,1");
		this.envoi("DO_LEVEL 5,1");
		this.envoi("DO_LEVEL 6,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 1
         * @deprecated
	 */
        @Override
	public void positionnementValve1() {
		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 2
         * @deprecated
	 */
        @Override
	public void positionnementValve2() {
		this.envoi("DO_LEVEL 0,1");
		this.envoi("DO_LEVEL 1,0");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 3
         * @deprecated
	 */
        @Override
	public void positionnementValve3() {
		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,0");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 4
         * @deprecated
	 */
        @Override
	public void positionnementValve4() {
		this.envoi("DO_LEVEL 0,1");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,0");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 5
         * @deprecated
	 */
        @Override
	public void positionnementValve5() {
		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,0");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 6
         * @deprecated
	 */
        @Override
	public void positionnementValve6() {
		this.envoi("DO_LEVEL 0,1");
		this.envoi("DO_LEVEL 1,0");
		this.envoi("DO_LEVEL 2,0");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 7
         * @deprecated
	 */
        @Override
	public void positionnementValve7() {
		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,0");
		this.envoi("DO_LEVEL 2,0");
		this.envoi("DO_LEVEL 3,1");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne à la valve 8
         * @deprecated
	 */
        @Override
	public void positionnementValve8() {
		this.envoi("DO_LEVEL 0,1");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,0");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne au sas
         * @deprecated
	 */
        @Override
	public void positionnementSas() {
		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,1");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,0");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne au rejet 1
         * @deprecated
	 */
        @Override
	public void positionnementRejet1() {
		this.envoi("DO_LEVEL 0,1");
		this.envoi("DO_LEVEL 1,0");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,0");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Positionne au rejet 2
         * @deprecated
	 */
        @Override
	public void positionnementRejet2() {
		this.envoi("DO_LEVEL 0,0");
		this.envoi("DO_LEVEL 1,0");
		this.envoi("DO_LEVEL 2,1");
		this.envoi("DO_LEVEL 3,0");
		this.envoi("DO_LEVEL 7,0");
	}

	/**
	 * Ouvre l'electrovanne 3 voies
         * @deprecated
	 */
        @Override
	public void ouvertureElectrovanne3Voies() {
		this.envoi("DO_LEVEL 4,0");
	}

	/**
	 * Ferme l'electrovanne 3 voies
         * @deprecated
	 */
        @Override
	public void fermetureElectrovanne3Voies() {
		this.envoi("DO_LEVEL 4,1");
	}
    
}
