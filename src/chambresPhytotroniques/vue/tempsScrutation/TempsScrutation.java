package chambresPhytotroniques.vue.tempsScrutation;

import java.awt.GridLayout;

import javax.swing.JDialog;
import chambresPhytotroniques.vue.Fenetre;

public class TempsScrutation extends JDialog {

	private static final long serialVersionUID = 7007707573750323103L;

	public static final int LIGNES_SONDES = 11;

	private static final String TITLE = "Temps de scrutations";

	private TempsScrutationLine[] tempsScrutationLines;

	private TempsScrutationBoutons tempsScrutationBoutons;

	public TempsScrutation(Fenetre fenetre) {
		super(fenetre);

		this.tempsScrutationLines = new TempsScrutationLine[LIGNES_SONDES + 1];

		this.setLayout(new GridLayout(LIGNES_SONDES + 1, 1));

		for (int i = 0; i < LIGNES_SONDES; i++) {
			this.tempsScrutationLines[i] = new TempsScrutationLineSonde(i);
			this.add(this.tempsScrutationLines[i]);
		}

		this.tempsScrutationBoutons = new TempsScrutationBoutons(this,
				tempsScrutationLines);
		this.add(this.tempsScrutationBoutons);

		this.setTitle(TITLE);
		this.setLocationRelativeTo(fenetre);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();

		this.setVisible(true);
	}

	public TempsScrutationBoutons getTempsScrutationBoutons() {
		return tempsScrutationBoutons;
	}

}
