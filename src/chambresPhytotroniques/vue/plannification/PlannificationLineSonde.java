package chambresPhytotroniques.vue.plannification;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import chambresPhytotroniques.outils.Configuration;

public class PlannificationLineSonde extends PlannificationLine {

	private static final long serialVersionUID = 672687362862220138L;

	public PlannificationLineSonde(int sonde) {
		super();

		this.setLayout(new GridLayout(LIGNES, COLONNES));

		this.sondeLabel = new JLabel(Configuration.getConfiguration()
				.getSondeName(sonde));

		this.jCheckBox = new JCheckBox();

		this.add(sondeLabel);
		this.add(jCheckBox);
	}

}
