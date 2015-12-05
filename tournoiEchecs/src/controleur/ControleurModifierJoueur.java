package controleur;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import vue.CreerJoueur;
import vue.ModifierJoueur;
import metier.Joueur;
import modele.ModeleJoueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
				lb_info.setText("Aucun joueurs trouvés");
			}
		}
		else{
			data.add(j);
		}
	}
	
	@FXML
	public void actionModifier(Event e){
		ModeleJoueur.setJoueurAmofifier((Joueur)lv_joueurs.getSelectionModel().getSelectedItem());
		if(ModeleJoueur.getJoueurAmodifier()==null){
			lb_info.setText("Veuillez selectionner un joueur");
		}
		else{
			CreerJoueur cj = new CreerJoueur(Main.getPrimaryStage());
			cj.show();
			((Node)e.getSource()).getScene().getWindow().hide();
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
