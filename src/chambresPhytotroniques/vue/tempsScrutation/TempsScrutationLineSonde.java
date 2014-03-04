package chambresPhytotroniques.vue.tempsScrutation;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import chambresPhytotroniques.outils.Configuration;

public class TempsScrutationLineSonde extends TempsScrutationLine {

	private static final long serialVersionUID = 4065466804288235614L;

	private int sonde;

	public TempsScrutationLineSonde(int sonde) {
		super();

		this.sonde = sonde;

		this.setLayout(new GridLayout(LIGNES, COLONNES));

		this.sondeLabel = new JLabel(Configuration.getConfiguration()
				.getSondeName(this.sonde) + ESPACEMENT);
		this.tempsPurgeLabel = new JLabel(TEMPS_PURGE_LABEL);
		this.tempsPurgeText = new JTextField(String.valueOf(Configuration
				.getConfiguration().getTempsPurge(this.sonde)));
		this.tempsAnalyseLabel = new JLabel(TEMPS_ANALYSE_LABEL);
		this.tempsAnalyseText = new JTextField(String.valueOf(Configuration
				.getConfiguration().getTempsAnalyse(sonde)));

		this.add(this.sondeLabel);
		this.add(this.tempsPurgeLabel);
		this.add(this.tempsPurgeText);
		this.add(this.tempsAnalyseLabel);
		this.add(this.tempsAnalyseText);
	}
}
