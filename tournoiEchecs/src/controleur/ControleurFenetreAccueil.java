package controleur;

import vue.CreationTournoi;
import vue.CreerJoueur;
import vue.FenetreAccueil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;

import application.Main;

public class ControleurFenetreAccueil {
	
	private File fileTournoi;
	
	@FXML
	private Label label_cheminTournoi;
	
    @FXML
    private void actionCreerTournoi() {
    	CreationTournoi creationTournoi = new CreationTournoi(Main.getPrimaryStage());
		creationTournoi.show();
    }
    
    @FXML
    private void actionCreerJoueur() {
    	CreerJoueur creerJoueur = new CreerJoueur(Main.getPrimaryStage());
    	creerJoueur.show();
    }
    
    @FXML
    private void actionParcourirTournoi(Event e) {
    	fileTournoi = FenetreAccueil.choisirTournoi(Main.getPrimaryStage());
		if (fileTournoi != null) {
			label_cheminTournoi.setText(fileTournoi.getName());
		}
    }

}
