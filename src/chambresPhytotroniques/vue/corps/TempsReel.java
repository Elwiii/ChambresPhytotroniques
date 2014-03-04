package chambresPhytotroniques.vue.corps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chambresPhytotroniques.modele.Valeur;
import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.vue.coefficient.Coefficient;

public class TempsReel extends JPanel {

	/**
	 * Nombre de valeur (label)
	 */
	private static final int NB_VALEURS = 4;

	/**
	 * Valeur du début du label de l'O3
	 */
	private static final String O3_LABEL = "O3 : ";

	/**
	 * Unité de label de l'O3
	 */
	private static final String O3_UNITE = "ppm";

	/**
	 * Valeur du début du label du CO2
	 */
	public static final String CO2_LABEL = "CO2 : ";

	/**
	 * Unité du label du CO2
	 */
	public static final String CO2_UNITE = "ppm";

	/**
	 * Valeur du début du label d'humidité
	 */
	private static final String HUMIDITE_LABEL = "Humidité : ";

	/**
	 * Unité du label d'humidité
	 */
	private static final String HUMIDITE_UNITE = "%";

	/**
	 * Valeur du début du label de température
	 */
	private static final String TEMPERATURE_LABEL = "Température : ";

	/**
	 * Unité du label de température
	 */
	private static final String TEMPERATURE_UNITE = "°C";

	private static final long serialVersionUID = -8314618099203176401L;

	public static final Color CO2_COLOR = Color.BLACK;

	public static final Color O3_COLOR = Color.BLUE;

	public static final Color HUMIDITE_COLOR = Color.RED;

	public static final Color TEMPERATURE_COLOR = Color.DARK_GRAY;

	private static final int TEMPS_REEL_WIDTH = 160;

	private static final int TEMPS_REEL_HEIGHT = Line.MINIMUM_HEIGHT;

	private Valeur co2, o3, humidite, temperature;

	private boolean checkCO2, checkO3, checkHumidite, checkTemperature;

	private JLabel co2Label, o3Label, humiditeLabel, temperatureLabel;

	private int sonde;

	public TempsReel(int sonde, boolean checkTemperature) {
		this(sonde, true, true, true, checkTemperature);
	}

	public TempsReel(int sonde, boolean checkCO2, boolean checkO3,
			boolean checkHumidite, boolean checkTemperature) {
		super();

		this.setPreferredSize(new Dimension(TEMPS_REEL_WIDTH, TEMPS_REEL_HEIGHT));

		this.sonde = sonde;
		this.checkCO2 = checkCO2;
		this.checkO3 = checkO3;
		this.checkHumidite = checkHumidite;
		this.checkTemperature = checkTemperature;

		this.setLayout(new GridLayout(TempsReel.NB_VALEURS, 1));

		if (checkCO2) {
			this.co2Label = new JLabel(TempsReel.CO2_LABEL + "?"
					+ TempsReel.CO2_UNITE);
			this.co2Label.setForeground(CO2_COLOR);
			this.add(this.co2Label);
		}

		if (checkO3) {
			this.o3Label = new JLabel(TempsReel.O3_LABEL + "?"
					+ TempsReel.O3_UNITE);
			this.o3Label.setForeground(O3_COLOR);
			this.add(this.o3Label);
		}

		if (checkHumidite) {
			this.humiditeLabel = new JLabel(TempsReel.HUMIDITE_LABEL + "?"
					+ TempsReel.HUMIDITE_UNITE);
			this.humiditeLabel.setForeground(HUMIDITE_COLOR);
			this.add(this.humiditeLabel);
		}

		if (checkTemperature) {
			this.temperatureLabel = new JLabel(TempsReel.TEMPERATURE_LABEL
					+ "?" + TempsReel.TEMPERATURE_UNITE);
			this.temperatureLabel.setForeground(TEMPERATURE_COLOR);
			this.add(this.temperatureLabel);
		}

	}

	public Valeur getCo2() {
		return co2;
	}

	public Valeur getO3() {
		return o3;
	}

	public Valeur getHumidite() {
		return humidite;
	}

	public Valeur getTemperature() {
		return temperature;
	}

	/**
	 * Retourne la valeur de CO2 après application des coefficients
	 * 
	 * @return
	 */
	private String getCo2Coeff() {
		return new DecimalFormat("#.###").format(co2.getValeur()
				* Configuration.getConfiguration().getA(Coefficient.CO2_NAME)
				+ Configuration.getConfiguration().getB(Coefficient.CO2_NAME));
	}

	/**
	 * Retourne la valeur d'O3 après application des coefficients
	 * 
	 * @return
	 */
	private String getO3Coeff() {
		return new DecimalFormat("#.###").format(o3.getValeur()
				* Configuration.getConfiguration().getA(Coefficient.O3_NAME)
				+ Configuration.getConfiguration().getB(Coefficient.O3_NAME));
	}

	/**
	 * Retourne la valeur d'humidité après application des coefficients
	 * 
	 * @return
	 */
	private String getHumiditeCoeff() {
		if (this.sonde < 4 || sonde == 9) {
			return new DecimalFormat("#.###").format(humidite.getValeur()
					* Configuration.getConfiguration().getA(
							Coefficient.HUMIDITE_NAME + 1)
					+ Configuration.getConfiguration().getB(
							Coefficient.HUMIDITE_NAME + 1));
		} else if (sonde < 8 || sonde == 10) {
			return new DecimalFormat("#.###").format(humidite.getValeur()
					* Configuration.getConfiguration().getA(
							Coefficient.HUMIDITE_NAME + 2)
					+ Configuration.getConfiguration().getB(
							Coefficient.HUMIDITE_NAME + 2));
		} else {
			return new DecimalFormat("#.###").format(humidite.getValeur()
					* Configuration.getConfiguration().getA(
							Coefficient.HUMIDITE_NAME + 0)
					+ Configuration.getConfiguration().getB(
							Coefficient.HUMIDITE_NAME + 0));
		}
	}

	/**
	 * Retourne la valeurs de température après application des coefficients
	 * 
	 * @return
	 */
	private String getTemperatureCoeff() {
		return new DecimalFormat("#.###").format(temperature.getValeur()
				* Configuration.getConfiguration().getA(
						Coefficient.TEMPERATURE_NAME + this.sonde)
				+ Configuration.getConfiguration().getB(
						Coefficient.TEMPERATURE_NAME + this.sonde));
	}

	public void setCo2(Valeur co2) {
		if (checkCO2) {
			this.co2 = co2;
			this.co2Label.setText(TempsReel.CO2_LABEL + this.getCo2Coeff()
					+ TempsReel.CO2_UNITE);
			repaint();
		}
	}

	public void setO3(Valeur o3) {
		if (checkO3) {
			this.o3 = o3;
			this.o3Label.setText(TempsReel.O3_LABEL + this.getO3Coeff()
					+ TempsReel.O3_UNITE);
			repaint();
		}
	}

	public void setHumidite(Valeur humidite) {
		if (checkHumidite) {
			this.humidite = humidite;
			this.humiditeLabel.setText(TempsReel.HUMIDITE_LABEL
					+ this.getHumiditeCoeff() + TempsReel.HUMIDITE_UNITE);
			repaint();
		}
	}

	public void setTempearture(Valeur tempearture) {
		if (checkTemperature) {
			this.temperature = tempearture;
			this.temperatureLabel.setText(TempsReel.TEMPERATURE_LABEL
					+ this.getTemperatureCoeff() + TempsReel.TEMPERATURE_UNITE);
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
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
	}

}
