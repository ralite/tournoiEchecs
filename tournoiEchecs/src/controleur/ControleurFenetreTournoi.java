package controleur;

import javafx.scene.Node;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Date;

import application.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.stage.Window;
import vue.AjouterJoueurTournoi;
import vue.CreationTournoi;
import metier.Tournoi;

public class ControleurFenetreTournoi {

	@FXML
	DatePicker dp_dateDeb;
	
	@FXML
	DatePicker dp_dateFin;
	
	@FXML
	TextField tf_nomTournoi;
	
	@FXML
	TextField tf_lieuTournoi;
	
	@FXML
	TextField tf_nbRondes;
	
	@FXML
	TextField tf_arbitre;
	
	
	@FXML
    private void actionFenetreJoueurs() {
		if (check(tf_nomTournoi) && check(tf_lieuTournoi) && check(tf_arbitre) && check(tf_nbRondes) && (dp_dateDeb.getValue()!=null) && (dp_dateFin.getValue()!=null)) {
			//Tournoi tournoi = new Tournoi(tf_nomTournoi.getText(),tf_lieuTournoi.getText(),dp_dateDeb.getValue(),dp_dateFin.getValue(),tf_arbitre.getText(),Integer.valueof(tf_nbRondes.getText));

    	AjouterJoueurTournoi ajoutjoueur = new AjouterJoueurTournoi(Main.getPrimaryStage());
		ajoutjoueur.show();
		}
    }
	

	
	@FXML
	public void actionQuitter(Event e) {
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	

	private boolean check(TextField leChampDeSaisie) {
		if (leChampDeSaisie.getText().trim().isEmpty()) {
			leChampDeSaisie.setStyle("-fx-control-inner-background : red; ");
			return false;
		} else {
			leChampDeSaisie.setStyle("-fx-control-inner-background : white; ");
			return true;
		}
	}

}
