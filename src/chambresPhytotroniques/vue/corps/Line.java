package chambresPhytotroniques.vue.corps;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JPanel;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.controleur.LineContoleur;
import chambresPhytotroniques.controleur.ListLinesControleur;
import chambresPhytotroniques.controleur.evenement.Evenement;
import chambresPhytotroniques.controleur.evenement.SondeEvenement;
import chambresPhytotroniques.controleur.evenement.SondeRejet1Evenement;
import chambresPhytotroniques.controleur.evenement.SondeRejet2Evenement;
import chambresPhytotroniques.controleur.evenement.SondeSasEvenement;
import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.outils.CSV;

public class Line extends JPanel {
	public static final long TEMPS_ATTENTE = 0;
	public static final int NOMBRE_PRISE = 1;

	private static final long serialVersionUID = 2441972563441427473L;

	/**
	 * Hauteur de ligne minimum
	 */
	public static final int MINIMUM_HEIGHT = 70;

	/**
	 * Largeur de ligne minimum
	 */
	public static final int MINIMUM_WIDTH = 600;

	private SondeTempsReel sondeTempsReel;
	private Graphique graphique;
	private DemarrerArreterEtat demarrerArreterEtat;

	private LineContoleur lineControleur;
	private Evenement evenement;
        private ListLine listLine;

	public Line(String sondeName, int sonde, FileDAttente fileDAttente,
			ListLinesControleur listLinesControleur, CSV excel,
			ListLine listLine) {
		this.sondeTempsReel = new SondeTempsReel(sonde, sondeName,
				(sonde < 8) ? true : false);
		this.graphique = new Graphique(sonde);
		this.demarrerArreterEtat = new DemarrerArreterEtat(sonde, fileDAttente);

		this.setLayout(new BorderLayout());

		this.add(this.sondeTempsReel, BorderLayout.WEST);
		this.add(this.graphique, BorderLayout.CENTER);
		this.add(this.demarrerArreterEtat, BorderLayout.EAST);

		// Taille minimum
		this.setPreferredSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));

		// Création de l'évenement
		if (sonde < 8) {
			// Chambre
			this.evenement = new SondeEvenement(sonde, this, excel);
		} else if (sonde < 9) {
			// Sas
			this.evenement = new SondeSasEvenement(this, excel);
		} else if (sonde < 10) {
			// Rejet1
			this.evenement = new SondeRejet1Evenement(listLine, this, excel);
		} else {
			// Rejet2
			this.evenement = new SondeRejet2Evenement(listLine, this, excel);
		}

		fileDAttente.setEvenement(sonde, evenement);
		fileDAttente.executeEvenement(sonde);

		// Ajout du contrôleur
		this.lineControleur = new LineContoleur(sonde, this, fileDAttente,
				listLinesControleur);
		this.getDemarrerArreterEtat().getDemarrerArreter()
				.addActionListener(this.lineControleur);
	}

	public SondeTempsReel getSondeTempsReel() {
		return sondeTempsReel;
	}

	public Graphique getGraphique() {
		return graphique;
	}

	public DemarrerArreterEtat getDemarrerArreterEtat() {
		return demarrerArreterEtat;
	}

	public LineContoleur getLineControleur() {
		return lineControleur;
	}

	public void setListCo2(LinkedList<Valeur> co2) {
		this.sondeTempsReel.getTempsReel().setCo2(co2.getLast());
		this.graphique.setListCo2(co2);
	}

	public void setListO3(LinkedList<Valeur> o3) {
		this.sondeTempsReel.getTempsReel().setO3(o3.getLast());
		this.graphique.setListO3(o3);
	}

	public void setListTempearture(LinkedList<Valeur> tempearture) {
		this.sondeTempsReel.getTempsReel()
				.setTempearture(tempearture.getLast());
		this.graphique.setListTemperature(tempearture);
	}

	public void setListHumidite(LinkedList<Valeur> humidite) {
		this.sondeTempsReel.getTempsReel().setHumidite(humidite.getLast());
		this.graphique.setListHumidite(humidite);
	}

        void setListLines(ListLine listLine) {
            this.listLine = listLine;
        }

        public ListLine getListLine() {
            return listLine;
        }

        
}
