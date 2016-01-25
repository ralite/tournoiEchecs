package vue;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AjouterJoueurTournoi extends Stage{
	
	public AjouterJoueurTournoi(Window window) {
		super();
		try {
			FXMLLoader leLoader = new FXMLLoader(this.getClass().getResource("/vue/ajouterJoueurTournoi.fxml"));
			AnchorPane root = leLoader.load();
			this.initModality(Modality.WINDOW_MODAL);
			this.initOwner(window);
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.setTitle("Ajouter un joueur au tournoi");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
