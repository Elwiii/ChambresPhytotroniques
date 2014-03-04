package chambresPhytotroniques.vue.coefficient;

import java.awt.GridLayout;

import javax.swing.JDialog;

import chambresPhytotroniques.vue.Fenetre;

public class Coefficient extends JDialog {

	private static final long serialVersionUID = -3799936137563296077L;

	public static final String HUMIDITE_NAME = "Humidite";

	public static final String TEMPERATURE_NAME = "Temperature";

	public static final String CO2_NAME = "CO2";

	public static final String O3_NAME = "O3";

	public static final String[] LINES_NAME = { CO2_NAME, O3_NAME };

	private static final String TITLE = "Coefficients";

	private static final int NB_TEMPERATURE = 8;

	public static final int NB_HUMIDITE = 3;

	private CoefficientLine[] coefficientLines;

	private CoefficientBoutons coefficientBoutons;

	public Coefficient(Fenetre fenetre) {
		super(fenetre);
		this.coefficientLines = new CoefficientLine[LINES_NAME.length
				+ NB_TEMPERATURE + NB_HUMIDITE];

		this.setLayout(new GridLayout(LINES_NAME.length + NB_TEMPERATURE
				+ NB_HUMIDITE + 1, 1));

		for (int i = 0; i < LINES_NAME.length; i++) {
			this.coefficientLines[i] = new CoefficientLine(LINES_NAME[i],
					LINES_NAME[i]);

			this.add(this.coefficientLines[i]);
		}

		for (int i = 0; i < NB_TEMPERATURE; i++) {
			this.coefficientLines[LINES_NAME.length + i] = new CoefficientLine(
					TEMPERATURE_NAME + i, "Temp Cab " + (i + 1));

			this.add(this.coefficientLines[LINES_NAME.length + i]);
		}

		for (int i = 0; i < NB_HUMIDITE; i++) {
			this.coefficientLines[LINES_NAME.length + NB_TEMPERATURE + i] = new CoefficientLine(
					HUMIDITE_NAME + i, HUMIDITE_NAME
							+ ((i == 0) ? " Sas" : " " + i));

			this.add(this.coefficientLines[LINES_NAME.length + NB_TEMPERATURE
					+ i]);
		}

		this.coefficientBoutons = new CoefficientBoutons(this, coefficientLines);
		this.add(this.coefficientBoutons);

		this.setTitle(TITLE);
		this.setLocationRelativeTo(fenetre);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();

		this.setVisible(true);
	}

	public CoefficientBoutons getCoefficientBoutons() {
		return coefficientBoutons;
	}
}
