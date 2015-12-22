package controleur;

import vue.AppariementJoueur;
import vue.CreationTournoi;
import vue.CreerJoueur;
import vue.FenetreAccueil;
import vue.FenetreFileChooser;
import vue.ModifierJoueur;
import vue.RecapTournoi;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import metier.Joueur;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.xml.StockageXML;
import application.Main;

public class ControleurFenetreAccueil implements Initializable{

	@FXML
	private Button button_creerTournoi;

	@FXML
	private Button button_creerJoueur;

	@FXML
	private Button button_parcourirTournoi;

	private File fileTournoi;

	@FXML
	private Button button_modifierJoueurAccueil;

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
    private void actionModifierJoueur(){
    	ModifierJoueur modifJoueur = new ModifierJoueur(Main.getPrimaryStage());
    	modifJoueur.show();
    }

    @FXML
    private void actionParcourirTournoi(Event e) {
    	fileTournoi = FenetreFileChooser.choisirTournoi(Main.getPrimaryStage());
		if (fileTournoi != null) {
			ModeleTournoi.ajouterTournoi(StockageXML.readXMLTournoi(fileTournoi.getAbsolutePath()));
			ModeleTournoi.setFichierTournoi(fileTournoi);
			RecapTournoi recapT = new RecapTournoi(Main.getPrimaryStage());
			recapT.show();
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
    	button_modifierJoueurAccueil.setVisible(false);
    }

    @FXML
    private void onClickJoueur(Event e){
    	button_creerJoueur.setVisible(true);
    	button_parcourirTournoi.setVisible(false);
    	button_creerTournoi.setVisible(false);
    	button_modifierJoueurAccueil.setVisible(true);
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	ArrayList<Joueur> listJoueur = StockageXML.readXMLJoueur(StockageXML.joueurFilePath);
    	if(listJoueur != null){
	    	for (Joueur joueur : listJoueur) {
				ModeleJoueur.ajouterJoueur(joueur);
			}
    	}

    }
}
