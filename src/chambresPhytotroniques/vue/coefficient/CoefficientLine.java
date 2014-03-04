package chambresPhytotroniques.vue.coefficient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chambresPhytotroniques.outils.Configuration;

public class CoefficientLine extends JPanel {

	private static final long serialVersionUID = 8731317327191614881L;

	private static final String X = " * x + ";

	private static final int ROWS = 4;

	private static final String ESPACEMENT = " : ";

	private JLabel sondeLabel;
	private JTextField A;
	private JLabel x;
	private JTextField B;

	private String sonde;

	public CoefficientLine(String sonde, String SondeLabel) {
		this.sonde = sonde;
		this.sondeLabel = new JLabel(SondeLabel + ESPACEMENT);
		this.A = new JTextField(String.valueOf(Configuration.getConfiguration()
				.getA(this.sonde)));
		this.x = new JLabel(X);
		this.B = new JTextField(String.valueOf(Configuration.getConfiguration()
				.getB(this.sonde)));

		this.setLayout(new GridLayout(1, ROWS));

		this.add(this.sondeLabel);
		this.add(this.A);
		this.add(this.x);
		this.add(this.B);
	}

	public String getA() {
		return this.A.getText();
	}

	public String getB() {
		return this.B.getText();
	}

	public String getsonde() {
		return this.sonde;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);

		// Bas
		g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
		// Haut
		g.drawLine(0, 0, this.getWidth() - 1, 0);
	}

}
