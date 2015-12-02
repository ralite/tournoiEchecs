package controleur;

import vue.CreationTournoi;
import vue.CreerJoueur;
import vue.FenetreAccueil;
import vue.FenetreFileChooser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import modele.ModeleTournoi;
import application.Main;

public class ControleurFenetreAccueil implements Initializable {
	
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
    private void actionParcourirTournoi(Event e) {
    	fileTournoi = FenetreFileChooser.choisirTournoi(Main.getPrimaryStage());
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
    	button_modifierJoueurAccueil.setVisible(false);
    }
    
    @FXML
    private void onClickJoueur(Event e){
    	button_creerJoueur.setVisible(true);
    	button_parcourirTournoi.setVisible(false);
    	button_creerTournoi.setVisible(false);
    	button_modifierJoueurAccueil.setVisible(true);
    }
    
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/echecs.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

}
