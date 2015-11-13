package application;
	
import java.io.File;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	private static Stage fenetrePrincipale = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.fenetrePrincipale = primaryStage;
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vue/fenetreAccueil.fxml"));
			Scene scene = new Scene(root,434,160);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Window getPrimaryStage() {
		return fenetrePrincipale;
	}
}
