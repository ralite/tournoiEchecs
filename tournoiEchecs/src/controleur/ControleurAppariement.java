package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import vue.ItemAppariementFactory;
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
		//itemsParties.addAll(new Partie(new Joueur("11", "jen", "prenom"), new Joueur("12", "jenlll", "pren;,nom")));
		lv_appariements.setItems(itemsParties);
		itemsJoueursInscrits.addAll(ModeleTournoi.getTournoi().getListeJoueurs());
		lv_joueurInscrit.setItems(itemsJoueursInscrits);
		lv_appariements.setCellFactory(lv -> new ItemAppariementFactory());
		lv_absent.setItems(itemsJoueursAbsent);
		lv_forfait.setItems(itemsJoueursForafait);

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
	public void actionLancerRonde(){
		if(itemsJoueursInscrits.size()>1){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText("Tout les joueurs ne sont pas apairer !");
			alert.showAndWait();
		}
	}

}
