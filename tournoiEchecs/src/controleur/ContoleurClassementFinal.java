package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.ItemClassementFinal;
import metier.Joueur;
import modele.ModeleTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ContoleurClassementFinal implements Initializable {
	
	@FXML
	ListView<Joueur> lv_classement;
	
	ObservableList<Joueur> itemsJoueur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemsJoueur=FXCollections.observableArrayList();
		itemsJoueur.addAll(ModeleTournoi.getTournoi().getListeJoueurs());
		lv_classement.setItems(itemsJoueur);
		lv_classement.setCellFactory(lv->new ItemClassementFinal());
		
	}

}
