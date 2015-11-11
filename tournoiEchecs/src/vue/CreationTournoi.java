package vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CreationTournoi extends Stage{
	
	public CreationTournoi(Window window) {
		super();
		try {
			FXMLLoader leLoader = new FXMLLoader(this.getClass().getResource("/vue/creationTournoi.fxml"));
			BorderPane root = leLoader.load();
			this.initModality(Modality.WINDOW_MODAL);
			this.initOwner(window);
			Scene scene = new Scene(root);
			this.setScene(scene);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
