package chambresPhytotroniques.vue.plannification;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlannificationLine extends JPanel {

	private static final long serialVersionUID = -3234249671120639121L;

	protected static final int LIGNES = 1;
	protected static final int COLONNES = 2;

	protected JLabel sondeLabel;
	protected JCheckBox jCheckBox;

	/**
	 * La JCheckBox est-elle sélectionnée?
	 * 
	 * @return
	 */
	public boolean isSelected() {
		return jCheckBox.isSelected();
	}

}
