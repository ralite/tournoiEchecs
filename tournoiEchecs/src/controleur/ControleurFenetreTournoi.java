package controleur;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Date;

import application.Main;
import javafx.fxml.FXML;
import javafx.stage.Window;
import vue.AjouterJoueurTournoi;
import vue.CreationTournoi;

import application.Tournoi;

public class ControleurFenetreTournoi {

	/*@FXML
	private TextField nom;
	@FXML
	private TextField lieu;
	@FXML
	private DatePicker dateDeb;
	@FXML
	private DatePicker dateFin;
	@FXML
	private TextField nomArbitre;
	@FXML
	private TextField prenomArbitre;
	@FXML
	private TextField nbRondes;*/

	@FXML
    private void actionFenetreJoueurs() {
		//Tournoi tournoi = new Tournoi(nom.getText(),lieu.getText(),dateDeb.getValue(),dateFin.getValue(),nomArbitre.getText(),prenomArbitre.getText());

    	AjouterJoueurTournoi ajoutjoueur = new AjouterJoueurTournoi(Main.getPrimaryStage());
		ajoutjoueur.show();
    }

}
