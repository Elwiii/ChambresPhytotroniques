package test;

import java.text.DecimalFormat;

public class TestDecimalFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new DecimalFormat("#.###").format(123456789.123456789));
	}

}
