package chambresPhytotroniques.vue.corps;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.controleur.ListLinesControleur;
import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.outils.Error;
import chambresPhytotroniques.outils.CSV;

public class ListLine extends JPanel {

	private static final long serialVersionUID = -168819197007992998L;

	public static final int SAS = 8;

	public static final int NB_SONDE = 11;

	private ArrayList<Line> listLines;

	private ListLinesControleur listLinesControleur;

	private FileDAttente fileDAttente;
        private ScrollLine scrollLine;

	public ListLine(int nbLines, FileDAttente fileDAttente, CSV excel) {
		this.listLines = new ArrayList<Line>(11);
		this.listLinesControleur = new ListLinesControleur(this, fileDAttente);
		this.fileDAttente = fileDAttente;

		this.setLayout(new GridLayout(11, 1));

		for (int i = 0; i < NB_SONDE; i++) {
			// Ajout des lignes
			this.addLine(Configuration.getConfiguration().getSondeName(i), i,
					fileDAttente, listLinesControleur, excel);
		}

		// Désactivation de tout sauf du Sas
		for (int i = 0; i < this.listLines.size(); i++) {
			if (i != SAS) {
				this.listLines.get(i).getDemarrerArreterEtat()
						.getDemarrerArreter().doClick();
			}
		}

	}

	/**
	 * Ajout d'une ligne (sonde)
	 * 
	 * @param sondeName
	 *            : String nom de la sonde
	 * @param sonde
	 * @param fileDAttente
	 * @param excel
	 */
	private void addLine(String sondeName, int sonde,
			FileDAttente fileDAttente, ListLinesControleur listLinesControleur,
			CSV excel) {
		Line line = new Line(sondeName, sonde, fileDAttente,
				listLinesControleur, excel, this);

		this.listLines.add(line);
                line.setListLines(this);

		if (listLines.size() >= ((GridLayout) getLayout()).getRows()) {
			((GridLayout) getLayout()).setRows(this.listLines.size());
		}

		this.add(line);
	}

	/**
	 * Retourne la ligne i
	 * 
	 * @param i
	 *            numéro de la ligne
	 * @return Line i
	 */
	public Line getLine(int i) {
		if (i < 0 || i >= this.listLines.size()) {
			Error.getError().error("Corps", "getLine",
					"La ligne " + i + " est inexistante");
			return null;
		}
		return this.listLines.get(i);
	}

	public int getNbLine() {
		return this.listLines.size();
	}

	public ArrayList<Line> getListLines() {
		return this.listLines;
	}

	public FileDAttente getFileDAttente() {
		return fileDAttente;
	}

        void setScrollLine(ScrollLine scrollLine) {
            this.scrollLine = scrollLine;
        }

        public ScrollLine getScrollLine() {
            return scrollLine;
        }
        
        
}
