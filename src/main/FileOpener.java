package main;

import javax.swing.JFileChooser;

public class FileOpener {
	
	JFileChooser fileOpener = new JFileChooser();
	
	public String getFilePath() {
		if(fileOpener.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return fileOpener.getSelectedFile().toString(); //TODO update to FILE instead of String
		else
			return null;
	}
	
}
