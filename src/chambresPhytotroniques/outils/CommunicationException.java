package chambresPhytotroniques.outils;

/**
 * Excetion lors d'un retour d'une commande envoyé à la central d'aquisition par
 * le port série
 * 
 * @author Mathieu Colin
 * @author Guillaume Lolivier
 * 
 */
public class CommunicationException extends Exception {

	private static final long serialVersionUID = -4447859815487079551L;

	public CommunicationException(String message) {
		super(message);
	}

}
