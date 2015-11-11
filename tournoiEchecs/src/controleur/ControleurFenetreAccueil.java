package controleur;

import vue.CreationTournoi;
import javafx.fxml.FXML;
import application.Main;

public class ControleurFenetreAccueil {
	
    @FXML
    private void actionCreerTournoi() {
    	CreationTournoi creationTournoi = new CreationTournoi(Main.getPrimaryStage());
		creationTournoi.show();
    }

}
