package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import chambresPhytotroniques.controleur.FenetreControleur;
import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.Fenetre;

public class MenuFichier extends JMenu {

	private static final long serialVersionUID = -6231165894246845124L;
	private static final int RACCOURCIS = KeyEvent.VK_F;
	private MenuEnregistrement enregistrement;
	private MenuQuitter quitter;

	public MenuFichier(Fenetre fenetre, FileDAttente fileDAttente) {
		super("Fichier");

		this.enregistrement = new MenuEnregistrement(fenetre, fileDAttente);
		this.quitter = new MenuQuitter();

		this.add(this.enregistrement);
		this.add(this.quitter);

		this.setMnemonic(RACCOURCIS);
	}

	public void setFenetreControleur(FenetreControleur fenetreControleur) {
		this.quitter.setFenetreControleur(fenetreControleur);
	}

}
