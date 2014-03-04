package chambresPhytotroniques.controleur.evenement;

import java.util.LinkedList;

import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.vue.corps.Line;

public abstract class SondeHumiditeEvenement extends SondeEvenement {

	protected LinkedList<Valeur> listHumidite;

	public SondeHumiditeEvenement(int numeroSonde, boolean checkCO2,
			boolean checkO3, boolean checkTemperature, Line line, CSV excel) {
		super(numeroSonde, checkCO2, checkO3, checkTemperature, line, excel);

		this.listHumidite = new LinkedList<Valeur>();
	}

	public LinkedList<Valeur> getListHumidite() {
		return listHumidite;
	}
	
	public abstract void doHumiditeAction();

}
