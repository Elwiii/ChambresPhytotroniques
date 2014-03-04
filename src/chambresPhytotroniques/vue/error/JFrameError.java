package chambresPhytotroniques.vue.error;

import javax.swing.JFrame;

public class JFrameError extends JFrame {

	private static final long serialVersionUID = -5418596857123282980L;

	private ScrollError scrollError;

	public JFrameError() {
		super();

		this.scrollError = new ScrollError();

		this.setLocationRelativeTo(null);
		this.setTitle("Erreurs");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(200, 200);

		this.setContentPane(this.scrollError);

		this.setVisible(true);
	}

	public ScrollError getScrollError() {
		return scrollError;
	}

}
