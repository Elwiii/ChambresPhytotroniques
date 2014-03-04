package chambresPhytotroniques.vue.plannification;

import java.awt.GridLayout;

import javax.swing.JDialog;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.vue.Fenetre;
import chambresPhytotroniques.vue.corps.ListLine;
import chambresPhytotroniques.vue.tempsScrutation.TempsScrutation;

public class Plannification extends JDialog {

	private static final long serialVersionUID = -7771864978396108260L;

	private static final String TITLE = "Plannification";

	private PlannificationLine[] plannificationLines;

	private PlannificationBoutons boutons;

	public Plannification(Fenetre fenetre, ListLine listLignes,
			FileDAttente fileDAttente) {
		super(fenetre);

		this.plannificationLines = new PlannificationLine[TempsScrutation.LIGNES_SONDES];

		this.setLayout(new GridLayout(TempsScrutation.LIGNES_SONDES + 1, 1));

		for (int i = 0; i < TempsScrutation.LIGNES_SONDES; i++) {
			this.plannificationLines[i] = new PlannificationLineSonde(i);
			this.add(this.plannificationLines[i]);
		}

		this.boutons = new PlannificationBoutons(this, plannificationLines,
				listLignes, fileDAttente);
		this.add(this.boutons);

		this.setTitle(TITLE);
		this.setLocationRelativeTo(fenetre);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();

		this.setVisible(true);
	}

	public PlannificationLine[] getPlannificationLines() {
		return plannificationLines;
	}
}
