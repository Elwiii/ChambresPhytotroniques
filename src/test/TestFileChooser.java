package test;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class TestFileChooser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser(FileSystemView
				.getFileSystemView().getHomeDirectory());
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "test";
			}

			@Override
			public boolean accept(File f) {
				return (f.canWrite() && f.getName().endsWith(".TxT") || f
						.isDirectory());
			}
		});
		int status = chooser.showSaveDialog(null);

		if (status == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			System.out.println(selectedFile.getPath());
		}
	}
}
