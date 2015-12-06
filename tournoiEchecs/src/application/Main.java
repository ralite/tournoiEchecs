package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import metier.Joueur;
import metier.Tournoi;
import metier.departage.Buchholz;
import metier.departage.Cumulatif;
import metier.departage.Departage;
import modele.ModeleJoueur;
import modele.xml.StockageXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

	private static Stage fenetrePrincipale = null;

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.fenetrePrincipale = primaryStage;
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vue/fenetreAccueil.fxml"));
			Scene scene = new Scene(root,600,400);
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
