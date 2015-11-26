package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import metier.Joueur;
import metier.Tournoi;
import metier.departage.Departage;
import modele.ModeleDepartage;
import modele.ModeleJoueur;
import modele.ModeleTournoi;

public class ControleurRecapInfosTournoi implements Initializable {
	
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
	ListView<Joueur> lv_recapJoueursInscrits;
	
	@FXML
	ListView<Departage> lv_recapDepartagesChoisis;
	
	
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
