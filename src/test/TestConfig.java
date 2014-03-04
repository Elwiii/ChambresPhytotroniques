package test;

import chambresPhytotroniques.outils.Configuration;

public class TestConfig {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 13; i++) {
			Configuration.getConfiguration().setTempsAnalyse(i, 1);
			Configuration.getConfiguration().setTempsPurge(i, 1);
		}

		Configuration.getConfiguration().setA("Humidité", 1);
		Configuration.getConfiguration().setB("Humidité", 1);

		Configuration.getConfiguration().setA("CO2", 1);
		Configuration.getConfiguration().setB("CO2", 1);

		Configuration.getConfiguration().setA("O3", 1);
		Configuration.getConfiguration().setB("O3", 1);

		for (int i = 0; i < 11; i++) {
			Configuration.getConfiguration().setA("Temperature" + i, 1);
			Configuration.getConfiguration().setB("Temperature" + i, 1);
		}
	}

}
