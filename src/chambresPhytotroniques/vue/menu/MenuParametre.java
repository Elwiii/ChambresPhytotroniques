package chambresPhytotroniques.vue.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import chambresPhytotroniques.vue.Fenetre;

public class MenuParametre extends JMenu {

	private static final long serialVersionUID = -4335592272624134572L;

	private static final int RACCOURCIS = KeyEvent.VK_P;

	private MenuScrutation scrutation;
	private MenuCoefficient menuCoefficient;

	public MenuParametre(Fenetre fenetre) {
		super("Paramètres");

		this.scrutation = new MenuScrutation(fenetre);
		this.menuCoefficient = new MenuCoefficient(fenetre);

		this.add(this.scrutation);
		this.add(this.menuCoefficient);

		this.setMnemonic(RACCOURCIS);
	}

	public MenuCoefficient getMenuCoefficient() {
		return menuCoefficient;
	}

	public MenuScrutation getScrutation() {
		return scrutation;
	}

}
