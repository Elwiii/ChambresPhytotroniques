package chambresPhytotroniques.vue.corps;

import java.awt.GridLayout;

import javax.swing.JPanel;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.outils.Error;

public class DemarrerArreterEtat extends JPanel {

	private static final long serialVersionUID = 4752198074013869781L;

	private DemarrerArreter demarrerArreter;

	private Etat etat;

	public DemarrerArreterEtat(int sonde, FileDAttente fileDAttente) {
		super();
		this.demarrerArreter = new DemarrerArreter(sonde);
		this.etat = new Etat(sonde);

		this.setLayout(new GridLayout(1, 2));

		this.add(this.demarrerArreter);
		this.add(this.etat);
	}

	public DemarrerArreter getDemarrerArreter() {
		return demarrerArreter;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.demarrerArreter.setEtat(etat);
		this.etat.setEtat(etat);
	}

	public boolean isEtat() {
		if (this.demarrerArreter.isEtat() != this.etat.isEtat())
			Error.getError().error("DemarrerArreterEtat", "isEtat",
					"Les états ne concordent pas");

		return this.etat.isEtat();
	}

	/**
	 * Active ou non le bouton {@link DemarrerArreter}
	 * 
	 * @param b
	 */
	@Override
	public void setEnabled(boolean b) {
		this.demarrerArreter.setEnabled(b);
	}

	public void setPlannification(boolean b) {
		// Activation de la sonde si on active
		if (b)
			this.setEtat(true);

		this.demarrerArreter.setPlannification(b);
	}

	public boolean getplannification() {
		return this.demarrerArreter.getplannification();
	}

}
