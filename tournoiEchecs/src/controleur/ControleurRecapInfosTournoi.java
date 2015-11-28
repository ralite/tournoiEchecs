package controleur;

import java.awt.Window;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import metier.Joueur;
import metier.Tournoi;
import metier.departage.Departage;
import modele.ModeleDepartage;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import vue.AjouterJoueurTournoi;
import vue.CreationTournoi;

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
	
	private LocalDate dateActuelle =  LocalDate.now();
	
	public void recapAjouterJoueur(Event e){
		if(dateActuelle.isAfter(ModeleTournoi.getTournoi().getDateDeb())) {
			button_recapModifierTournoi.isDisabled();
		}
		else {
			AjouterJoueurTournoi ajt=new AjouterJoueurTournoi(Main.getPrimaryStage());
			ajt.show();
		}
	}
	
	public void recapModifierTournoi(Event e){
		if(dateActuelle.isAfter(ModeleTournoi.getTournoi().getDateDeb())) {
			button_recapModifierTournoi.isDisabled();
		}
		else {
			CreationTournoi ct = new CreationTournoi(Main.getPrimaryStage());
			ct.show();
			((Node)e.getSource()).getScene().getWindow().hide();
		}
	}
	
		@FXML
		public void retirerJoueur(Event e) {
			if(dateActuelle.isAfter(ModeleTournoi.getTournoi().getDateDeb())) {
				button_recapModifierTournoi.isDisabled();
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
		lv_recapJoueursInscrits.setItems(ModeleJoueur.getcollectionJoueurs());
		lv_recapDepartagesChoisis.setItems(ModeleDepartage.getcollectionDepartages());
	}

}
