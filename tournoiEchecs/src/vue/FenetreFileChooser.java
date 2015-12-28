package vue;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class FenetreFileChooser {
	private static File lastdir=null;
	
	public static File choisirTournoi(Window owner){
		File fichierAouvrir;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un tournoi");
		if(lastdir!= null){
			fileChooser.setInitialDirectory(lastdir);
		}
		fileChooser.getExtensionFilters().add(new ExtensionFilter("xml Files", "*.xml"));
		fichierAouvrir=fileChooser.showOpenDialog(owner);
		lastdir=fichierAouvrir.getParentFile();
		return fichierAouvrir;
		
	}
	
	public static File EnregistrerTournoi(Window owner){
		File dossierChoisi;
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Enregistrer le tournoi");
		if(lastdir!= null){
			directoryChooser.setInitialDirectory(lastdir);
		}
		dossierChoisi=directoryChooser.showDialog(owner);
		lastdir=dossierChoisi;
		return dossierChoisi;
	}
	
	
}
