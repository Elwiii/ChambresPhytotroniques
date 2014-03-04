package chambresPhytotroniques.vue;

import javax.swing.JFrame;

import chambresPhytotroniques.controleur.FenetreControleur;
import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.vue.corps.Corps;


public class Fenetre extends JFrame {

	private static final long serialVersionUID = 5033943069276941893L;

	private static final String PROGRAMME_NAME = "Gestion Des Chambres Phytotroniques";

	private Menu menu;
	private Corps corps;
	private FileDAttente fileDAttente;
	private CSV excelLog;

	private FenetreControleur fenetreControleur;

	public Fenetre() {
		this(11, new FileDAttente());
	}

	public Fenetre(int nbLines, FileDAttente fileDAttente) {
		super();

		this.excelLog = new CSV();
		this.corps = new Corps(nbLines, fileDAttente, excelLog);
		this.menu = new Menu(this, fileDAttente);
		this.fenetreControleur = new FenetreControleur(excelLog, this,
				this.menu.getMenuParametre().getMenuCoefficient()
						.getCoefficientControleur(), this.menu
						.getMenuPlannification().getMenuStartPlannification()
						.getPlannificationStartControleur(), this.menu
						.getMenuParametre().getScrutation()
						.getScrutationControleur());
		this.fileDAttente = fileDAttente;

		this.menu.setFenetreControleur(fenetreControleur);

		this.setJMenuBar(menu);
		this.setContentPane(corps);

		this.addWindowListener(this.fenetreControleur);

		this.setTitle(PROGRAMME_NAME);
		this.setSize(800, 835);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setVisible(true);
          

	}

	public Menu getMenu() {
		return menu;
	}

	public Corps getCorps() {
		return corps;
	}

	public void start() {
		this.fileDAttente.doAllActions();
	}

}
