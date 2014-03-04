package test;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.controleur.evenement.SondeEvenement;
import chambresPhytotroniques.vue.corps.Line;

public class TestFileDAttente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileDAttente fileDAttente = new FileDAttente();

		fileDAttente.setEvenement(1, new SondeEvenement(0, new Line("", 0,
				fileDAttente, null, null, null), null));
		fileDAttente.executeEvenement(1);
		fileDAttente.doAction();

		System.out.println(fileDAttente);
	}

}
