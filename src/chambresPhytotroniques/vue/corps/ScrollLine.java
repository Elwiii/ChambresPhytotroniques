package chambresPhytotroniques.vue.corps;

import javax.swing.JScrollPane;

import chambresPhytotroniques.controleur.FileDAttente;
import chambresPhytotroniques.outils.CSV;

public class ScrollLine extends JScrollPane {

	private static final long serialVersionUID = 1731244786880084510L;

	private ListLine listLine;
        private Corps corps;

	public ScrollLine(int nbLines, FileDAttente fileDAttente, CSV excel) {
		super(new ListLine(nbLines, fileDAttente, excel));

		this.listLine = (ListLine) this.getViewport().getView();
                this.listLine.setScrollLine(this);
	}

	public ListLine getListLine() {
		return listLine;
	}

        void setCorps(Corps corps) {
            this.corps = corps;
        }

        public Corps getCorps() {
            return corps;
        }
        
        
}
