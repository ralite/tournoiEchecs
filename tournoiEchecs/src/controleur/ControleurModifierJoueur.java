package controleur;

import java.awt.List;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import vue.ModifierJoueur;
import metier.Joueur;
import modele.ModeleJoueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurModifierJoueur implements Initializable{
	
	private ObservableList<Joueur> data = FXCollections.observableArrayList();
	
	@FXML
	ListView<Joueur> lv_joueurs;
	
	@FXML
	TextField tf_recherche;

	@FXML
	public void actionChercher(){
		Joueur j;
		ArrayList<Joueur> joueurs;
		data.clear();
		j=ModeleJoueur.rechercherJoueur(tf_recherche.getText().toString());
		if(j==null){
			joueurs=ModeleJoueur.rechercherNomJoueur(tf_recherche.getText().toString());
			if(joueurs!=null){
				data.addAll(joueurs);
			}
		}
		else{
			data.add(j);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Joueur j1 = new Joueur("A11111", "jean", "jacques");
		Joueur j2 = new Joueur("A22222", "jean", "paul");
		Joueur j3 = new Joueur("B11111", "cpierre", "paul");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);
		ModeleJoueur.ajouterJoueur(j3);
		lv_joueurs.setItems(data);
	}

}
