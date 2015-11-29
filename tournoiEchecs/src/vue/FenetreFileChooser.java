package vue;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class FenetreFileChooser {
	public static File choisirTournoi(Window owner){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un tournoi");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("xml Files", "*.xml"));
		return fileChooser.showOpenDialog(owner);
	}
	
	public static File EnregistrerTournoi(Window owner){
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Enregistrer le tournoi");
		return directoryChooser.showDialog(owner);
	}
	
	
}
