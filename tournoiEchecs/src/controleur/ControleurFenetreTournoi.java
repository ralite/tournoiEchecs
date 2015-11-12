package controleur;

import application.Main;
import javafx.fxml.FXML;
import javafx.stage.Window;
import vue.CreationTournoi;
import vue.FenetreJoueurs;

public class ControleurFenetreTournoi {

	@FXML
    private void actionFenetreJoueurs() {
    	FenetreJoueurs fenetreJoueurs = new FenetreJoueurs(Main.getPrimaryStage());
		fenetreJoueurs.show();
    }
	
}
