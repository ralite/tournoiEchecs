package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.ItemAppariement;
import vue.ItemSaisieResultat;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurSaisieResultat implements Initializable{
	
	@FXML
	private ListView<Partie> lv_resultats;
	
	@FXML
	private TextField tf_recherche;
	
	private ObservableList<Partie> itemResultat;
	
	private ObservableList<Partie> itemRechercher;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemResultat = FXCollections.observableArrayList();
		itemResultat.addAll(ModeleTournoi.getTournoi().getPartieRondeActuelle());
		lv_resultats.setItems(itemResultat);
		lv_resultats.setCellFactory(lv -> new ItemSaisieResultat());
		
		itemRechercher = FXCollections.observableArrayList();
	}

	@FXML
	public void validerSaisieResultat(){
		ModeleTournoi.getTournoi().setPartiesRonde(itemResultat);
	}
	
	@FXML
	public void rechercherPartie(){
		itemRechercher.clear();
		ModeleTournoi.getTournoi().setPartiesRonde(itemResultat);
		for (Partie partie :itemResultat) {
			if(partie.rechercherPartie(tf_recherche.getText().toString()))
				itemRechercher.add(partie);
		}
		lv_resultats.setItems(itemRechercher);
	}
	
	@FXML
	public void toutesParties(){
		lv_resultats.setItems(itemResultat);
	}
}
