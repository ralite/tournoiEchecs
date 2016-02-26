package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import vue.CreerJoueur;
import metier.Joueur;
import modele.ModeleJoueur;
import modele.xml.I_DALJoueur;
import modele.xml.JoueurXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleurModifierJoueur implements Initializable{

	private ObservableList<Joueur> data = FXCollections.observableArrayList();

	@FXML
	ListView<Joueur> lv_joueurs;

	@FXML
	TextField tf_recherche;

	@FXML
	Label lb_info;

	@FXML
	public void actionChercher(){
		Joueur j;
		ArrayList<Joueur> joueurs;
		data.clear();
		lb_info.setText("");
		j=ModeleJoueur.rechercherJoueur(tf_recherche.getText().toString());
		if(j==null){
			joueurs=ModeleJoueur.rechercherNomJoueur(tf_recherche.getText().toString());
			if(!joueurs.isEmpty()){
				data.addAll(joueurs);
			}
			else {
				lb_info.setText("Aucun joueur trouv�");
			}
		}
		else{
			data.add(j);
		}
	}

	@FXML
	public void actionAnnuler(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void actionModifier(Event e){
		ModeleJoueur.setJoueurAmofifier((Joueur)lv_joueurs.getSelectionModel().getSelectedItem());
		if(ModeleJoueur.getJoueurAmodifier()==null){
			lb_info.setText("Veuillez s�lectionner un joueur");
		}
		else{
			CreerJoueur cj = new CreerJoueur(Main.getPrimaryStage());
			cj.show();
			((Node)e.getSource()).getScene().getWindow().hide();
		}
	}

	@FXML
	public void actionSupprimer(Event e){
		Joueur joueurSelectionn�=(Joueur)lv_joueurs.getSelectionModel().getSelectedItem();
		if(joueurSelectionn�==null){
			lb_info.setText("Veuillez s�lectionner un joueur");
		}
		else{
			if(joueurSelectionn�.isDansTournoi()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur suppression joueur");
				alert.setContentText("Ce joueur est inscrit dans un ou plusieurs tournoi, vous ne pouvez donc pas le supprimer !");
				alert.showAndWait();
			}else{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Suppression joueur");
				alert.setContentText("Voulez-vous vraiment supprimer ce joueur ?\n");
				alert.showAndWait();
				if(alert.getResult().getText().equals("OK")){
					ModeleJoueur.supprimerJoueur(joueurSelectionn�);
					I_DALJoueur joueurXML = new JoueurXML();
					joueurXML.WriteJoueur(ModeleJoueur.getArrayJoueurs());
					((Node)e.getSource()).getScene().getWindow().hide();
				}else{
					data.clear();
					tf_recherche.clear();
				}				
			}	
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lv_joueurs.setItems(data);
	}

}
