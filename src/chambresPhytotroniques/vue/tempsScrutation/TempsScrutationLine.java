package chambresPhytotroniques.vue.tempsScrutation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class TempsScrutationLine extends JPanel {

	private static final long serialVersionUID = 8732920229733227464L;

	/**
	 * Hauteur de ligne minimum
	 */
	public static final int MINIMUM_HEIGHT = 70;

	/**
	 * Largeur de ligne minimum
	 */
	public static final int MINIMUM_WIDTH = 600;

	protected static final String ESPACEMENT = " : ";

	protected static final int LIGNES = 1;
	protected static final int COLONNES = 4;
	protected static final String TEMPS_PURGE_LABEL = "Temps de purge : ";
	protected static final String TEMPS_ANALYSE_LABEL = "Temps d'analyse : ";

	protected JLabel sondeLabel;
	protected JLabel tempsPurgeLabel;
	protected JTextField tempsPurgeText;
	protected JLabel tempsAnalyseLabel;
	protected JTextField tempsAnalyseText;

	public TempsScrutationLine() {
		super();
		this.setSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
	}

	public String getTempsPurge() {
		return tempsPurgeText.getText();
	}

	public String getTempsAnalyse() {
		return tempsAnalyseText.getText();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);

		// Bas
		g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
		// Haut
		g.drawLine(0, 0, this.getWidth() - 1, 0);
	}

}
