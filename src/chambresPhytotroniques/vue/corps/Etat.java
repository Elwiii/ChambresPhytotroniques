package chambresPhytotroniques.vue.corps;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Etat extends JPanel {

	/**
	 * Couleur de l'enregistrement
	 */
	private static final Color ETAT_ENREGISREMENT = Color.GREEN;

	/**
	 * Couleur hors enregistrement
	 */
	private static final Color ETAT_NON_ENREGISTREMENT = Color.RED;

	private static final long serialVersionUID = 5411861469749109951L;

	private static final Color SCRUTATION_COLOR = Color.BLUE;

	private boolean etat;
	private boolean scrutation;
        private int sonde;

	public Etat(int sonde) {
		super();
                this.sonde = sonde;
		this.setEtat(true);
		this.setScrutation(false);
	}

	/**
	 * La sonde enregistre-t-elle?
	 * 
	 * @return la sonde enregistre-t-elle?
	 */
	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		if (this.etat != etat) {
			this.etat = etat;
			repaint();
		}
	}

	public boolean isScrutation() {
		return scrutation;
	}

	public void setScrutation(boolean scrutation) {
		this.scrutation = scrutation;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
                if(sonde == 3 || sonde == 7) {
                    g.setColor(Color.RED);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.setColor(Color.BLACK);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                } else if (sonde == 4 || sonde == 8) {
                    g.setColor(Color.BLACK);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.setColor(Color.RED);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                }

		// Choix de la couleur
		if (this.scrutation)
			g.setColor(SCRUTATION_COLOR);
		else if (this.etat)
			g.setColor(Etat.ETAT_ENREGISREMENT);
		else
			g.setColor(Etat.ETAT_NON_ENREGISTREMENT);

		// Affichage du cercle
		g.fillOval(this.getWidth() / 4, this.getHeight() / 4,
				this.getWidth() / 2, this.getWidth() / 2);
	}

}
