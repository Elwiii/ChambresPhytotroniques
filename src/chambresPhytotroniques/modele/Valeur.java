package chambresPhytotroniques.modele;

import java.util.Date;

/**
 * Valeur enregistré lors d'une capture
 * 
 * @author Mathieu Colin
 * @author Guillaume Lolivier
 * 
 */
public class Valeur implements Comparable<Valeur> {

	private double valeur;
	private Date date;

	/**
	 * Créé une valeur
	 * 
	 * @param double valeur valeur en Volt de cette valeur
	 * @param Date
	 *            date et heure de l'aquisition de cette valeur
	 */
	public Valeur(double valeur, Date date) {
		super();
		this.valeur = valeur;
		this.date = date;
	}

	/**
	 * Créé une valeur à la date et heure actuel
	 * 
	 * @param double valeur valeur en Volt de cette valeur
	 */
	public Valeur(double valeur) {
		super();
		this.valeur = valeur;
		this.date = new Date();
	}

	public Valeur() {
		this(-1);
	}

	/**
	 * Retourne la valeur en Volt
	 * 
	 * @return la valeur en Volt
	 */
	public double getValeur() {
		return valeur;
	}

	/**
	 * Retourne la date et heure de l'aquisition de cette valeur
	 * 
	 * @return la date et heure de l'aquisition de cette valeur
	 */
	public Date getDate() {
		return date;
	}

	@Override
	public int compareTo(Valeur o) {
		return this.getDate().compareTo(o.getDate());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valeur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valeur other = (Valeur) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(valeur) != Double
				.doubleToLongBits(other.valeur))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Valeur [valeur=" + valeur + ", date=" + date + "]";
	}

}
