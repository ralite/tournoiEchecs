package controleur;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import metier.Joueur;
import metier.departage.Departage;
import modele.ModeleTournoi;
import vue.AjouterJoueurTournoi;
import vue.AppariementJoueur;
import vue.CreationTournoi;
import vue.SaisieResultat;

public class ControleurRecapInfosTournoi implements Initializable {

	@FXML
	Label lb_recapWarning;

	@FXML
	Label label_recapNom;

	@FXML
	Label label_recapLieu;

	@FXML
	Label label_recapArbitre;

	@FXML
	Label label_recapDateDeb;

	@FXML
	Label label_recapDateFin;

	@FXML
	Label label_recapNbRondes;

	@FXML
	Button button_recapModifierTournoi;

	@FXML
	ListView<Joueur> lv_recapJoueursInscrits;

	@FXML
	ListView<Departage> lv_recapDepartagesChoisis;

	@FXML
	Label lb_recapCadenceDeJeu;

	private LocalDate dateActuelle =  LocalDate.now();

	public void recapGererJoueurs(Event e){

			AjouterJoueurTournoi ajt=new AjouterJoueurTournoi(Main.getPrimaryStage());
			ajt.show();
			((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void fermerRecap(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	public void recapModifierTournoi(Event e){
		if(dateActuelle.isAfter(ModeleTournoi.getTournoi().getDateDeb())) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setHeaderText(null);
			alert.setContentText("Impossible de modifier un tournoi en cours !");
			alert.showAndWait();
		}
		else {
			CreationTournoi ct = new CreationTournoi(Main.getPrimaryStage());
			ct.show();
			((Node)e.getSource()).getScene().getWindow().hide();
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		label_recapNom.setText(ModeleTournoi.getTournoi().getNom());
		label_recapLieu.setText(ModeleTournoi.getTournoi().getLieu());
		label_recapArbitre.setText(ModeleTournoi.getTournoi().getArbitre());
		label_recapDateDeb.setText(String.valueOf(ModeleTournoi.getTournoi().getDateDeb()));
		label_recapDateFin.setText(String.valueOf(ModeleTournoi.getTournoi().getDateFin()));
		label_recapNbRondes.setText(String.valueOf(ModeleTournoi.getTournoi().getNbRondes()));
		lb_recapCadenceDeJeu.setText(String.valueOf(ModeleTournoi.getTournoi().getCadenceJeu()));
		lv_recapJoueursInscrits.setItems(ModeleTournoi.getTournoi().getListeJoueurs());
		lv_recapDepartagesChoisis.setItems(ModeleTournoi.getTournoi().getListeDepartages());
	}

	@FXML
	public void apparierJoueurs(){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==-1){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText("Tournoi Fini !");
			alert.showAndWait();
		}
		else{
			if(!ModeleTournoi.getTournoi().getRondeActuelle().isApp()){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Erreur");
				alert.setContentText("Veuillez préalablement saisir les résultats !");
				alert.showAndWait();
			}
			else{
				AppariementJoueur app = new AppariementJoueur(Main.getPrimaryStage());
				app.show();
			}
		}
	}

	@FXML
	public void saisirResultat(){
		if(!ModeleTournoi.getTournoi().getRondeActuelle().isSaisie()){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText("Veuillez préalablement apparier les joueurs !");
			alert.showAndWait();
		}
		else{
			SaisieResultat app = new SaisieResultat(Main.getPrimaryStage());
			app.show();
		}
	}

}
