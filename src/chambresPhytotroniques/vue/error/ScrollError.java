package chambresPhytotroniques.vue.error;

import java.awt.Point;

import javax.swing.JScrollPane;

public class ScrollError extends JScrollPane {

	private static final long serialVersionUID = -6793373330413299798L;

	private TextError textError;

	public ScrollError() {
		super(new TextError());

		this.textError = (TextError) this.getViewport().getView();
	}

	/**
	 * Ajout le texte au TextError et met au bon endroit le ScrollBar
	 * 
	 * @param t
	 *            String à ajouter
	 */
	public void setText(String t) {
		this.textError.setText(t);

		// Positionne le ScrollBar en bas après l'ajout
		this.getViewport().setViewPosition(
				new Point(0, (int) this.textError.getPreferredSize()
						.getHeight()));
	}
}
