package chambresPhytotroniques.vue.corps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Sonde extends JLabel {

	private static final long serialVersionUID = 5993503707809426276L;
	private static final int SONDE_WIDTH = 100;
	private static final int SONDE_HEIGHT = Line.MINIMUM_HEIGHT;

	public Sonde(String name) {
		super(name);
		this.setPreferredSize(new Dimension(SONDE_WIDTH, SONDE_HEIGHT));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
                if(this.getText().equals("Chambre 4") || this.getText().equals("Chambre 8")) {
                    g.setColor(Color.RED);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.setColor(Color.BLACK);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                } else if (this.getText().equals("Chambre 5") || this.getText().equals("Rejet sas")) {
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
