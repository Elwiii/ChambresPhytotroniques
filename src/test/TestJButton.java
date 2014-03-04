package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestJButton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JButton button = new JButton("bouton");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						for (int i = 0; i < 5; i++) {
							Color col = button.getBackground();
							button.setBackground(Color.RED);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
							}
							button.setBackground(col);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
							}
						}
					}
				}.start();
			}
		});
		JFrame frame = new JFrame();
		frame.getContentPane().add(button);
		frame.pack();
		frame.setVisible(true);
	}
}
