package test;

import javax.swing.UIManager;
import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.Fenetre;

public class TestGraphiqueUIManager {

	public static void main(String[] args) {
		try {

			int i = (int) ((Math.random() * 1000) % UIManager
					.getInstalledLookAndFeels().length);

			System.out.println("Nombre de LookAndFeels="
					+ UIManager.getInstalledLookAndFeels().length);
			System.out.println("LookAndFeels choisi=" + i);
			System.out.println(UIManager.getInstalledLookAndFeels()[i]
					.getName());

			UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[i]
					.getClassName());

		} catch (Exception e) {
		}

		FileDAttente fileDAttente = new FileDAttente();
		new Fenetre(11, fileDAttente);
	}
}
