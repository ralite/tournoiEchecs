package controleur;

import vue.CreerTournoi;
import vue.CreerJoueur;
import vue.FenetreFileChooser;
import vue.ModifierJoueur;
import vue.RecapInfosTournoi;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import metier.Joueur;
import metier.Tournoi;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.xml.I_DALJoueur;
import modele.xml.I_DALTournoi;
import modele.xml.JoueurXML;
import modele.xml.TournoiXML;
import application.Lastdir;
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
    	ModeleTournoi.setFichierTournoi(null);
    	CreerTournoi creationTournoi = new CreerTournoi(Main.getPrimaryStage());
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
    	ModeleTournoi.nouveauTournoi();
    	fileTournoi = FenetreFileChooser.choisirDir(Main.getPrimaryStage());
		if (fileTournoi != null) {
			I_DALTournoi tournoiXML = new TournoiXML(fileTournoi.getPath());
			Tournoi t = tournoiXML.readTournoi();
			ModeleTournoi.ajouterTournoi(t);
			ModeleTournoi.setFichierTournoi(fileTournoi.getPath());
			RecapInfosTournoi recapT = new RecapInfosTournoi(Main.getPrimaryStage());
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
    	Lastdir.readLastdir(Lastdir.lastDirFilePath);
    	I_DALJoueur joueurXML = new JoueurXML();
    	ArrayList<Joueur> listJoueur = joueurXML.readJoueur();
    	if(listJoueur != null){
	    	for (Joueur joueur : listJoueur) {
				ModeleJoueur.ajouterJoueur(joueur);
			}
    	}
    }
}
