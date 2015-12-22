package controleur;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import metier.Joueur;
import metier.Partie;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import vue.ItemAppariementFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControleurAppariement implements Initializable {
	
	@FXML
	private Label lb_joueurNoir;
	
	@FXML
	private Label lb_joueurBlanc;
	
	@FXML
	private ListView<Joueur> lv_joueurInscrit;
	
	@FXML
	private ListView<Partie> lv_appariements;
	
	private ObservableList<Partie> itemsParties;
	
	private ObservableList<Joueur> itemsJoueursInscrits;
	
	private Joueur joueurBlanc=null;
	private Joueur joueurNoir=null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemsParties = FXCollections.observableArrayList();
		itemsJoueursInscrits = FXCollections.observableArrayList();
		//itemsParties.addAll(new Partie(new Joueur("11", "jen", "prenom"), new Joueur("12", "jenlll", "pren;,nom")));
		lv_appariements.setItems(itemsParties);
		itemsJoueursInscrits.addAll(ModeleTournoi.getJoueurs());
		lv_joueurInscrit.setItems(itemsJoueursInscrits);
		lv_appariements.setCellFactory(lv -> new ItemAppariementFactory());
		
	}
	
	@FXML
	public void onClickNoir(){
		joueurNoir=lv_joueurInscrit.getSelectionModel().getSelectedItem();
		if(joueurNoir!=joueurBlanc){
			lb_joueurNoir.setText(joueurNoir.toString());
		}
		else{
			joueurNoir=null;
			lb_joueurNoir.setText("");
		}
	}
	
	@FXML
	public void onClickBlanc(){
		joueurBlanc=lv_joueurInscrit.getSelectionModel().getSelectedItem();
		if(joueurNoir!=joueurBlanc){
			lb_joueurBlanc.setText(joueurBlanc.toString());
		}
		else {
			joueurBlanc=null;
			lb_joueurBlanc.setText("");
		}
		
	}

	@FXML
	public void onClickAjouter(){
		itemsParties.add(new Partie(joueurBlanc, joueurNoir));
		itemsJoueursInscrits.removeAll(joueurBlanc,joueurNoir);
		joueurBlanc=null;
		joueurNoir=null;
	}
	
}
