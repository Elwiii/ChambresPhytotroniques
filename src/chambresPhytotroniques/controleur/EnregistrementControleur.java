package chambresPhytotroniques.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import chambresPhytotroniques.outils.CSV;
import chambresPhytotroniques.vue.Fenetre;

public class EnregistrementControleur implements ActionListener {

	private static final String EXTENSION = ".csv";

	private JFileChooser jFileChooser;

	private Fenetre fenetre;

	private File fileSelected;

	private FileDAttente fileDAttente;

	public EnregistrementControleur(Fenetre fenetre, FileDAttente fileDAttente) {
		super();
		this.fenetre = fenetre;
		this.fileDAttente = fileDAttente;

		this.jFileChooser = new JFileChooser(FileSystemView.getFileSystemView()
				.getHomeDirectory());
		this.jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// JFileChooser
		if (this.jFileChooser.showSaveDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
			this.fileSelected = this.jFileChooser.getSelectedFile();

			// Si ça se fini pas par la bonne extension
			if (!this.fileSelected.getName().toLowerCase().endsWith(EXTENSION)) {
				// On ajoute l'extension
				this.fileSelected = new File(this.fileSelected.getPath()
						+ EXTENSION);
			}

			// Enregistrer les valeurs au format Excel
			new Thread() {
				public void run() {
					new CSV(fileSelected).ecrire(fileDAttente
							.getListEvenement());
				};
			}.start();
		}
	}
}
