package chambresPhytotroniques.outils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import chambresPhytotroniques.vue.error.JFrameError;

/**
 * Gestion des erreurs et exceptions
 * 
 * @author Mathieu Colin
 * @author guillaume Lolivier
 * 
 */
public class Error {

	/**
	 * Nom du fichier de log
	 */
	private static final String ERROR_LOG_FILE_NAME = "ChambrePhytotronique.log";

	/**
	 * Nom du logger
	 */
	private static final String LOGER_NAME = "ChambrePytotroniquesLogger";

	/**
	 * Singleton Error
	 */
	private static Error error;

	/**
	 * Logger permettant d'écrire sous forme log
	 */
	private Logger logger;

	/**
	 * FileHandler spécifiant le fichier log
	 */
	private FileHandler fileHandler;

	/**
	 * JFrame dans laquelle afficher les erreurs
	 */
	private JFrameError jFrameError;

	/**
	 * Constructeur d'Error Avec initialisation du logger et fileHandler
	 */
	private Error() {
		this.jFrameError = new JFrameError();

		this.logger = Logger.getLogger(LOGER_NAME);

		try {
			this.fileHandler = new FileHandler(ERROR_LOG_FILE_NAME, true);
			logger.addHandler(this.fileHandler);
			this.fileHandler.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			e.printStackTrace();
			dispError("Error", "Error",
					"Impossible d'initialier le FileHandler", e);
		} catch (IOException e) {
			e.printStackTrace();
			dispError("Error", "Error",
					"Impossible d'initialier le FileHandler", e);
		}

		logger.setLevel(Level.ALL);

	}

	/**
	 * Singleton Error
	 * 
	 * @return Singleton Error
	 */
	public static Error getError() {
		if (error == null)
			error = new Error();
		return error;
	}

	/**
	 * Gestion d'erreur
	 * 
	 * @param classe
	 *            classe d'ou provient l'erreur
	 * @param methode
	 *            méthode d'ou provient l'erreur
	 * @param message
	 *            message de l'erreur
	 */
	public void error(String classe, String methode, String message) {
		// Écrit le log
		writeLogError(classe, methode, message);

		// Affiche un message d'erreur
		dispError(classe, methode, message);

		new Mail().sendAlertMail("", "Erreur", message);
	}

	/**
	 * Gestion d'erreur
	 * 
	 * @param classe
	 *            classe d'ou provient l'erreur
	 * @param methode
	 *            méthode d'ou provient l'erreur
	 * @param message
	 *            message de l'erreur
	 * @param thrown
	 *            exception de l'erreur
	 */
	public void error(String classe, String methode, String message,
			Throwable thrown) {
		// Écrit le log
		writeLogError(classe, methode, message, thrown);

		// Affiche un message d'erreur
		dispError(classe, methode, message, thrown);

		new Mail().sendAlertMail("", "Erreur",
				message + "\n\n" + thrown.getMessage());
	}

	/**
	 * Affiche une fenêtre d'erreur à l'utilisateur
	 * 
	 * @param classe
	 *            classe d'ou provient l'erreur
	 * @param methode
	 *            méthode d'ou provient l'erreur
	 * @param message
	 *            message de l'erreur
	 */
	private void dispError(String classe, String methode, String message) {
		this.jFrameError.getScrollError()
				.setText(
						DateFormat.getDateTimeInstance().format(new Date())
								+ " " + classe + " " + methode + "\nGRAVE: "
								+ message + "\n");

		this.jFrameError.setVisible(true);
	}

	/**
	 * Affiche une fenêtre d'erreur à l'utilisateur
	 * 
	 * @param classe
	 *            classe d'ou provient l'erreur
	 * @param methode
	 *            méthode d'ou provient l'erreur
	 * @param message
	 *            message de l'erreur
	 * @param thrown
	 *            exception de l'erreur
	 */
	private void dispError(String classe, String methode, String message,
			Throwable thrown) {
		StringWriter string = new StringWriter();
		PrintWriter printer = new PrintWriter(string);

		thrown.printStackTrace(printer);

		this.jFrameError.getScrollError().setText(
				DateFormat.getDateTimeInstance().format(new Date()) + " "
						+ classe + " " + methode + "\nGRAVE: " + message + "\n"
						+ string);
	}

	/**
	 * Écrit dans le log et affiche une erreur
	 * 
	 * @param classe
	 *            classe d'ou provient l'erreur
	 * @param methode
	 *            méthode d'ou provient l'erreur
	 * @param message
	 *            message de l'erreur
	 */
	private void writeLogError(String classe, String methode, String message) {
		try {
			logger.logp(Level.SEVERE, classe, methode, message);
		} catch (SecurityException e) {
			e.printStackTrace();
			dispError("Error", "writeLogError",
					"Impossible d'écrire le fichier log", e);
		}

	}

	/**
	 * Écrit dans le log et affiche une erreur
	 * 
	 * @param classe
	 *            classe d'ou provient l'erreur
	 * @param methode
	 *            méthode d'ou provient l'erreur
	 * @param message
	 *            message de l'erreur
	 * @param thrown
	 *            exception de l'erreur
	 */
	private void writeLogError(String classe, String methode, String message,
			Throwable thrown) {
		try {
			logger.logp(Level.SEVERE, classe, methode, message, thrown);
		} catch (SecurityException e) {
			e.printStackTrace();
			dispError("Error", "writeLogError",
					"Impossible d'écrire le fichier log", e);
		}

	}

}
