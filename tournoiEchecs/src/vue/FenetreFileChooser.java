package vue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import application.Lastdir;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class FenetreFileChooser {

	public static File choisirTournoi(Window owner){
			File fichierAouvrir;
			try{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choisissez un tournoi");
			if(Lastdir.lastdir!= null && Files.isDirectory(Paths.get(Lastdir.lastdir.getAbsolutePath()))){
				fileChooser.setInitialDirectory(Lastdir.lastdir);
			}
			fileChooser.getExtensionFilters().add(new ExtensionFilter("xml Files", "*.xml"));
			fichierAouvrir=fileChooser.showOpenDialog(owner);
			Lastdir.lastdir=fichierAouvrir.getParentFile();
			Lastdir.saveLastdir(Lastdir.lastDirFilePath);
			return fichierAouvrir;
		}
		catch(Exception e){
			return null;
		}

	}

	public static File EnregistrerTournoi(Window owner){
		File dossierChoisi;
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Enregistrer le tournoi");
		if(Lastdir.lastdir!= null && Files.isDirectory(Paths.get(Lastdir.lastdir.getAbsolutePath()))){
			directoryChooser.setInitialDirectory(Lastdir.lastdir);
		}
		dossierChoisi=directoryChooser.showDialog(owner);
		Lastdir.lastdir=dossierChoisi;
		Lastdir.saveLastdir(Lastdir.lastDirFilePath);
		return dossierChoisi;
	}
}
