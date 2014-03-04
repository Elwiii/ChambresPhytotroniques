package chambresPhytotroniques.vue.mails;

import java.awt.GridLayout;

import javax.swing.JDialog;

import chambresPhytotroniques.vue.Fenetre;

public class Mails extends JDialog {

	public static final String LINES_NAME = "Adresse";

	private static final String TITLE = "Paramétrage mail";

	private static final int NB_TEMPERATURE = 8;

	public static final int NB_HUMIDITE = 3;

	private MailsLine[] mailsLines;

	private MailsBoutons mailsBoutons;

	public Mails(Fenetre fenetre) {
		super(fenetre);
		this.mailsLines = new MailsLine[4];

		this.setLayout(new GridLayout(5, 1));

		for (int i = 0; i < 4; i++) {
			this.mailsLines[i] = new MailsLine(LINES_NAME+i,
					LINES_NAME+i);

			this.add(this.mailsLines[i]);
		}

		this.mailsBoutons = new MailsBoutons(this, mailsLines);
		this.add(this.mailsBoutons);

		this.setTitle(TITLE);
		this.setLocationRelativeTo(fenetre);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();

		this.setVisible(true);
	}

	public MailsBoutons getMailsBoutons() {
		return mailsBoutons;
	}
}
