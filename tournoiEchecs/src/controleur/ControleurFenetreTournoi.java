package controleur;

import application.Main;
import javafx.fxml.FXML;
import javafx.stage.Window;
import vue.AjouterJoueurTournoi;
import vue.CreationTournoi;

public class ControleurFenetreTournoi {

	@FXML
    private void actionFenetreJoueurs() {
    	AjouterJoueurTournoi ajoutjoueur = new AjouterJoueurTournoi(Main.getPrimaryStage());
		ajoutjoueur.show();
    }
	
}
