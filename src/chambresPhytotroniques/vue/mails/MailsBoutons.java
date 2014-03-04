package chambresPhytotroniques.vue.mails;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import chambresPhytotroniques.controleur.CoefficientAnnulerControleur;
import chambresPhytotroniques.controleur.CoefficientValiderControleur;
import chambresPhytotroniques.controleur.MailAnnulerControleur;
import chambresPhytotroniques.controleur.MailValiderControleur;

public class MailsBoutons extends JPanel {

	private static final String VALIDER_TEXT = "Valider";

	private static final String ANNULER_TEXT = "Annuler";

	private JButton valider;
	private JButton annuler;

	private MailValiderControleur validerControleur;

	private MailAnnulerControleur annulerControleur;

	public MailsBoutons(Mails mails,
			MailsLine[] mailsLines) {
		super();

		this.setLayout(new BorderLayout());

		this.valider = new JButton(VALIDER_TEXT);
		this.annuler = new JButton(ANNULER_TEXT);

		this.validerControleur = new MailValiderControleur(mails,
				mailsLines);
		this.annulerControleur = new MailAnnulerControleur(mails);

		this.valider.addActionListener(this.validerControleur);
		this.annuler.addActionListener(this.annulerControleur);

		this.add(this.valider, BorderLayout.CENTER);
		this.add(this.annuler, BorderLayout.EAST);
	}

	public JButton getValider() {
		return valider;
	}

}
