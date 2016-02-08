package controleur;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import vue.ItemClassementFinal;
import metier.Joueur;
import metier.Partie;
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
		FXCollections.sort(itemsJoueur, new Comparator<Joueur>() {

			@Override
			public int compare(Joueur j1, Joueur j2) {
				//a modifier on fonction des departage
				return Float.compare(j2.getScore(),j1.getScore());
			}
		});
		for(int i=0;i<itemsJoueur.size();i++){
			itemsJoueur.get(i).setClassement(i+1);
		}
		
	}

}