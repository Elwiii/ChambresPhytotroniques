package chambresPhytotroniques.vue.mails;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chambresPhytotroniques.outils.Configuration;
import java.awt.Dimension;

public class MailsLine extends JPanel {

	private static final String X = "Boucles : ";

	private static final int ROWS = 4;

	private static final String ESPACEMENT = " : ";

	private JLabel adresseLabel;
	private JTextField A;
	private JLabel boucle;
	private JTextField B;

	private String adresse;

	public MailsLine(String adresse, String AdresseLabel) {
		this.adresse = adresse;
		this.adresseLabel = new JLabel(AdresseLabel + ESPACEMENT);
                System.out.println("booleen " + Configuration.getConfiguration().contains(adresse) );
                if(Configuration.getConfiguration().contains(adresse)) {
                    		this.A = new JTextField(String.valueOf(Configuration.getConfiguration()
				.getAdresse(this.adresse)));
                } else {
                    this.A = new JTextField();
                }

                A.setPreferredSize(new Dimension(150, 10));
                
                /*
		this.boucle = new JLabel(X);
		//this.B = new JTextField(String.valueOf(Configuration.getConfiguration()
		//		.getB(this.sonde)));
                this.B = new JTextField();
                */
		this.setLayout(new GridLayout(1, 2));

		this.add(this.adresseLabel);
		this.add(this.A);
//		this.add(this.boucle);
//		this.add(this.B);
	}

	public String getA() {
		return this.A.getText();
	}

	public String getB() {
		return this.B.getText();
	}

        public String getAdresse() {
            return adresse;
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
