package chambresPhytotroniques.vue.corps;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class SondeTempsReel extends JPanel {

	private static final long serialVersionUID = 3585471553791797998L;

	private Sonde sonde;

	private TempsReel tempsReel;

	public SondeTempsReel(int sonde, String sondeName, boolean checkTemperature) {
		super();
		this.sonde = new Sonde(sondeName);
		this.tempsReel = new TempsReel(sonde, checkTemperature);

		this.setLayout(new BorderLayout());

		this.add(this.sonde, BorderLayout.WEST);
		this.add(this.tempsReel, BorderLayout.CENTER);
	}

	public Sonde getSonde() {
		return sonde;
	}

	public TempsReel getTempsReel() {
		return tempsReel;
	}

}
