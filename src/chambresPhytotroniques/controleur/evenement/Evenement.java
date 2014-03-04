package chambresPhytotroniques.controleur.evenement;

import java.util.LinkedList;

import chambresPhytotroniques.modele.Valeur;

public interface Evenement {

	/**
	 * Effectue l'action de cet évènement
	 * 
	 * @return le nouvel évènement à ajouter dans la liste (peut être null)
	 */
	public Evenement doAction();

	@Override
	public boolean equals(Object obj);

	public LinkedList<Valeur> getListCO2();

	public LinkedList<Valeur> getListO3();

	public LinkedList<Valeur> getListTemperature();

}
