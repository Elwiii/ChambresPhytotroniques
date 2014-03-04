package chambresPhytotroniques.outils;

import java.util.Properties;

import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class Mail {
	/**
	 * Méthode d'envoi de mail simple pour une alerte.
	 * 
	 * @param dest Destinataire "chambre.phyto.uhp@gmail.com" si isEmpty
	 * @param title Titre du mail
	 * @param msg Contenu du mail (seulement l'erreur à envoyer)
	 * @return true quand le mail est envoyé
	 */
	public boolean sendAlertMail(String dest, String title, String msg) {

		if (dest.isEmpty())
			dest = "chambre.phyto.uhp@gmail.com";

		final String username = "chambre.phyto.uhp@gmail.com";
		final String password = "UMR-1137";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(dest));
			message.setRecipients(Message.RecipientType.TO,
			/* UMR-1137 */
			InternetAddress.parse("chambre.phyto.uhp@gmail.com"));
			message.setSubject("[Chambres Phyto.][Alerte!] " + title);
			message.setText("Ceci est un message d'alerte du programme de gestion des chambres phytotroniques, une erreure est survenue :"
					+ "\n\n => " + msg);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Méthode d'envoi de mail avec pièce jointe
	 * 
	 * @param dest Destinataire "chambre.phyto.uhp@gmail.com" si isEmpty
	 * @param attachment Chemin de la pièce jointe
	 * @return true quand le mail est envoyé.
	 */
	public boolean sendMailWithAttachment(String dest, String attachment) {
		if (dest.isEmpty())
			dest = "chambre.phyto.uhp@gmail.com";

		final String username = "chambre.phyto.uhp@gmail.com";
		final String password = "UMR-1137";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("chambre.phyto.uhp@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			/* UMR-1137 */
			InternetAddress.parse("chambre.phyto.uhp@gmail.com"));
			message.setSubject("[Chambres Phyto][Relevé] ");

			// create the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText("Ci-joint le relevé des mesures en cours. Mail envoyé par le programme de gestion des chambres phytotroniques.");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			String filename = attachment;
			FileDataSource fds = new FileDataSource(filename);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			message.setContent(mp);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}
}
