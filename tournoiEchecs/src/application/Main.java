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
		/*LocalDate d1 = LocalDate.parse("1965-02-28");
		LocalDate d2 = LocalDate.parse("1980-09-20");
		Joueur j1 = new Joueur("A11112", "damier", "jacques","M",d1,"maitre","francaise",1200,"FIDE","LR","senior","mtp");
		Joueur j2 = new Joueur("A11113", "ideo", "paul","M",d2,"aucun","francaise",1800,"FIDE","AUV","senior","cmt");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);
		StockageXML.WriteXMLJoueur(StockageXML.joueurFilePath, ModeleJoueur.getArrayJoueurs());*/

		launch(args);
	}

	public static Window getPrimaryStage() {
		return fenetrePrincipale;
	}
}
