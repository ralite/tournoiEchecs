package application;

import java.time.LocalDate;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import metier.Tournoi;
import metier.departage.Buchholz;
import metier.departage.Cumulatif;
import metier.departage.Departage;
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

		/*LocalDate DateDeb = LocalDate.parse("2015-02-20");
		LocalDate DateFin = LocalDate.parse("2015-02-28");
		Tournoi tournoi = new Tournoi("nomtest", "ici", DateDeb, DateFin, "john doe", 3, 0);
		Departage dep1 = new Buchholz();
		Departage dep2 = new Cumulatif();
		tournoi.AddDepartages(dep1);
		tournoi.AddDepartages(dep2);
		StockageXML.writeXMLTournoi(tournoi, "Z:\\Documents\\Projet_Echecs");*/

		//Tournoi tournoi = StockageXML.readXMLTournoi("Z:\\Documents\\Projet_Echecs\\tournoi_nomtest_ici_2015-02-20.xml");
		//System.out.println(tournoi.toString());

	}

	public static Window getPrimaryStage() {
		return fenetrePrincipale;
	}
}
