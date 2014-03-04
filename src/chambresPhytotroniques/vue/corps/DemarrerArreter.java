package chambresPhytotroniques.vue.corps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class DemarrerArreter extends JButton {

	/**
	 * Valeur du ToolTip hors enregistrement
	 */
	private static final String BOUTON_NON_ENREGISTEMENT_TOOLTIP = "Démarrer l'enregistrement";

	/**
	 * Valeur du ToolTip pendant l'enregistrement
	 */
	private static final String BOUTON_ENREGISTREMENT_TOOLTIP = "Arrêter l'enregistrement";

	/**
	 * Valeur du bouton hors enregistrement
	 */
	private static final String BOUTON_NON_ENREGISTREMENT = "Démarrer";

	/**
	 * Valeur du bouton pendant l'enregistrement
	 */
	private static final String BOUTON_ENREGISTEMENT = "Arrêter";

	private static final long serialVersionUID = -6839427574271867148L;

	private boolean etat;

	private boolean plannification;
        
        private int sonde;

	public DemarrerArreter(int sonde) {
		super();
                this.sonde = sonde;
                
		this.setEtat(true);

		this.setPreferredSize(new Dimension(89, 70));
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

			this.setText((this.etat) ? DemarrerArreter.BOUTON_ENREGISTEMENT
					: DemarrerArreter.BOUTON_NON_ENREGISTREMENT);
			this.setToolTipText((this.etat) ? DemarrerArreter.BOUTON_ENREGISTREMENT_TOOLTIP
					: DemarrerArreter.BOUTON_NON_ENREGISTEMENT_TOOLTIP);

			repaint();
		}
	}

	public void setPlannification(boolean b) {
		this.setEnabled(!b);
		this.plannification = b;
		this.setEnabled(!b);
	}

	public boolean getplannification() {
		return plannification;
	}

	@Override
	public void setEnabled(boolean b) {
		if (!plannification)
			super.setEnabled(b);
	}
        
}
