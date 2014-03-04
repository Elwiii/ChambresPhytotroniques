package chambresPhytotroniques.vue.corps;

import java.awt.GridLayout;

import javax.swing.JPanel;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.outils.CSV;
import java.awt.BorderLayout;

public class Corps extends JPanel {

	private static final long serialVersionUID = 923908340565208407L;
        private Compteur compteur;

	private ScrollLine scrollLine;

	public Corps(int nbLines, FileDAttente fileDAttente, CSV excel) {
		this.scrollLine = new ScrollLine(nbLines, fileDAttente, excel);
                this.scrollLine.setCorps(this);
                this.compteur = new Compteur();
                
                this.setLayout(new BorderLayout());
		this.add(scrollLine, BorderLayout.CENTER);
                this.add(compteur, BorderLayout.SOUTH);
	}

	public ScrollLine getScrollLine() {
		return scrollLine;
	}

        public Compteur getCompteur() {
            return compteur;
        }

        
}
