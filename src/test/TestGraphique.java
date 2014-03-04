package test;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.Fenetre;

public class TestGraphique {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileDAttente fileDAttente = new FileDAttente();
		new Fenetre(11, fileDAttente);
	}

}
