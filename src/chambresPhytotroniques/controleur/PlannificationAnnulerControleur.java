package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chambresPhytotroniques.vue.plannification.Plannification;

public class PlannificationAnnulerControleur implements ActionListener {

	private Plannification plannification;

	public PlannificationAnnulerControleur(Plannification plannification) {
		this.plannification = plannification;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.plannification.dispose();
	}

}
