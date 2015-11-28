package controleur;

import vue.CreationTournoi;
import vue.CreerJoueur;
import vue.FenetreAccueil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;

import modele.ModeleTournoi;
import application.Main;

public class ControleurFenetreAccueil {
	
	@FXML
	private Button button_creerTournoi;
	
	@FXML
	private Button button_creerJoueur;
	
	@FXML
	private Button button_parcourirTournoi;
	
	private File fileTournoi;
	
	@FXML
	private Label label_cheminTournoi;
	
    @FXML
    private void actionCreerTournoi() {
    	ModeleTournoi.nouveauTournoi();
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
    
    @FXML
    private void actionFermer(Event e) {
    	((Node)e.getSource()).getScene().getWindow().hide();
    }
    
    @FXML
    private void onClickTournoi(Event e){
    	button_parcourirTournoi.setVisible(true);
    	button_creerTournoi.setVisible(true);
    	button_creerJoueur.setVisible(false);
    }
    
    @FXML
    private void onClickJoueur(Event e){
    	button_creerJoueur.setVisible(true);
    	button_parcourirTournoi.setVisible(false);
    	button_creerTournoi.setVisible(false);
    }

}
