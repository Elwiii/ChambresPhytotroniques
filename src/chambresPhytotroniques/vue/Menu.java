package chambresPhytotroniques.vue;

import javax.swing.JMenuBar;

import chambresPhytotroniques.controleur.FenetreControleur;
import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.menu.MenuFichier;
import chambresPhytotroniques.vue.menu.MenuMails;
import chambresPhytotroniques.vue.menu.MenuParametre;
import chambresPhytotroniques.vue.menu.MenuPlanification;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = -1054540707225899063L;

	private MenuFichier menuFichier;
	private MenuPlanification menuPlannification;
	private MenuParametre menuParametre;
        private MenuMails menuMails;

	public Menu(Fenetre fenetre, FileDAttente fileDAttente) {
		super();
		this.menuFichier = new MenuFichier(fenetre, fileDAttente);
		this.menuPlannification = new MenuPlanification(fenetre, fenetre
				.getCorps().getScrollLine().getListLine(), fileDAttente);
		this.menuParametre = new MenuParametre(fenetre);
                this.menuMails = new MenuMails(fenetre);

		this.add(this.menuFichier);
		this.add(this.menuPlannification);
		this.add(this.menuParametre);
                this.add(this.menuMails);
	}

	public MenuFichier getMenuFichier() {
		return menuFichier;
	}

	public MenuPlanification getMenuPlannification() {
		return menuPlannification;
	}

	public MenuParametre getMenuParametre() {
		return menuParametre;
	}

	public void setFenetreControleur(FenetreControleur fenetreControleur) {
		this.menuFichier.setFenetreControleur(fenetreControleur);
	}

}
