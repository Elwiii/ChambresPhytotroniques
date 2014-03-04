package chambresPhytotroniques.controleur.evenement;

import java.util.LinkedList;

import chambresPhytotroniques.modele.Valeur;

public abstract class HumiditeEvenement implements Evenement {

	@Override
	public abstract Evenement doAction();

	public abstract LinkedList<Valeur> getListHumidite();

}
