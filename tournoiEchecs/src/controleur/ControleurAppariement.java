package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import vue.ItemAppariement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class ControleurAppariement implements Initializable {

	@FXML
	private Label lb_joueurNoir;

	@FXML
	private Label lb_joueurBlanc;

	@FXML
	private ListView<Joueur> lv_joueurInscrit;

	@FXML
	private ListView<Partie> lv_appariements;

	@FXML
	private ListView<Joueur> lv_absent;

	@FXML
	private ListView<Joueur> lv_forfait;

	private ObservableList<Partie> itemsParties;

	private ObservableList<Joueur> itemsJoueursInscrits;

	private ObservableList<Joueur> itemsJoueursAbsent;

	private ObservableList<Joueur> itemsJoueursForafait;

	private Joueur joueurBlanc=null;
	
	private Joueur joueurNoir=null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemsParties = FXCollections.observableArrayList();
		itemsJoueursInscrits = FXCollections.observableArrayList();
		itemsJoueursAbsent = FXCollections.observableArrayList();
		itemsJoueursForafait = FXCollections.observableArrayList();
		itemsJoueursInscrits.addAll(ModeleTournoi.getTournoi().getListeJoueurs());
		if(ModeleTournoi.getTournoi().getPartieRondeActuelle()!=null){
			itemsParties.addAll(ModeleTournoi.getTournoi().getPartieRondeActuelle());
			itemsJoueursInscrits.removeAll(ModeleTournoi.getTournoi().getJoueursRondeActuelle());
		}
		lv_appariements.setItems(itemsParties);
		lv_joueurInscrit.setItems(itemsJoueursInscrits);
		
		lv_appariements.setCellFactory(lv -> new ItemAppariement());
		
		if(ModeleTournoi.getTournoi().getJoueurAbsRondeActuelle()!=null){
			itemsJoueursAbsent.addAll(ModeleTournoi.getTournoi().getJoueurAbsRondeActuelle());
			itemsJoueursInscrits.removeAll(itemsJoueursAbsent);
		}
		lv_absent.setItems(itemsJoueursAbsent);
		if(ModeleTournoi.getTournoi().getJoueurForfaitRondeActuelle()!=null){
			itemsJoueursForafait.addAll(ModeleTournoi.getTournoi().getJoueurForfaitRondeActuelle());
			itemsJoueursInscrits.removeAll(itemsJoueursForafait);
		}
		lv_forfait.setItems(itemsJoueursForafait);

	}

	@FXML
	public void onClickNoir(){
		if(joueurNoir!=null){
			itemsJoueursInscrits.add(joueurNoir);
			joueurNoir=null;
			lb_joueurNoir.setText("");
		}
		joueurNoir=lv_joueurInscrit.getSelectionModel().getSelectedItem();
		if(joueurNoir!=null){
			lb_joueurNoir.setText(joueurNoir.toString());
			itemsJoueursInscrits.remove(joueurNoir);
		}

	}

	@FXML
	public void onClickBlanc(){
		if(joueurBlanc!=null){
			itemsJoueursInscrits.add(joueurBlanc);
			joueurBlanc=null;
			lb_joueurBlanc.setText("");
		}
		joueurBlanc=lv_joueurInscrit.getSelectionModel().getSelectedItem();
		if(joueurBlanc != null){
			lb_joueurBlanc.setText(joueurBlanc.toString());
			itemsJoueursInscrits.remove(joueurBlanc);
		}

		
	}

	@FXML
	public void onClickAjouter(){
		if( joueurBlanc != null && joueurNoir!=null){
			if(ModeleTournoi.getTournoi().dejaRencontre(joueurNoir, joueurBlanc)){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Erreur");
				alert.setContentText("Ces deux joueurs ont déjà joué ensemble !");
				alert.showAndWait();
			}
			else{
				itemsParties.add(new Partie(joueurBlanc, joueurNoir));
				joueurBlanc=null;
				joueurNoir=null;
				lb_joueurBlanc.setText("");
				lb_joueurNoir.setText("");
			}
		}
	}
	
	@FXML
	public void actionRetirerPaire(){
		Partie partieSelectionnée =  (Partie)lv_appariements.getSelectionModel().getSelectedItem();
		if(partieSelectionnée!=null){
			itemsJoueursInscrits.add(partieSelectionnée.getJoueurBlanc());
			itemsJoueursInscrits.add(partieSelectionnée.getJoueurNoir());
			itemsParties.remove(partieSelectionnée);
		}
	}

	@FXML
	public void actionAjouterAbsent(){
		Joueur joueurSelectionné =  (Joueur)lv_joueurInscrit.getSelectionModel().getSelectedItem();
		if(joueurSelectionné!=null){
			itemsJoueursAbsent.add(joueurSelectionné);
			itemsJoueursInscrits.remove(joueurSelectionné);
		}
	}

	@FXML
	public void actionAjouterForfait(){
		Joueur joueurSelectionné =  (Joueur)lv_joueurInscrit.getSelectionModel().getSelectedItem();
		if(joueurSelectionné!=null){
			itemsJoueursForafait.add(joueurSelectionné);
			itemsJoueursInscrits.remove(joueurSelectionné);
		}
	}

	@FXML
	public void actionAnnuler(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void actionValider(){
		if(itemsJoueursInscrits.size()>1){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText("Tout les joueurs ne sont pas apairer !");
			alert.showAndWait();
		}
		else{
			ModeleTournoi.getTournoi().setPartiesRonde(itemsParties);
			ModeleTournoi.getTournoi().setAbsentRonde(itemsJoueursAbsent);
			ModeleTournoi.getTournoi().setForfaitRonde(itemsJoueursForafait);
		}
		
	}
	
	@FXML
	public void actionRetirerAbsent(){
		Joueur joueurSelectionné =  (Joueur)lv_absent.getSelectionModel().getSelectedItem();
		if(joueurSelectionné!=null){
			itemsJoueursAbsent.remove(joueurSelectionné);
			itemsJoueursInscrits.add(joueurSelectionné);
		}
	}

	@FXML
	public void actionRetirerForfait(){
		Joueur joueurSelectionné =  (Joueur)lv_forfait.getSelectionModel().getSelectedItem();
		if(joueurSelectionné!=null){
			itemsJoueursForafait
			.remove(joueurSelectionné);
			itemsJoueursInscrits.add(joueurSelectionné);
		}
	}
}
