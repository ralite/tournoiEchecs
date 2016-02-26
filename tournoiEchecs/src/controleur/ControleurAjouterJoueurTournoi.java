package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import application.Main;
import metier.Joueur;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.xml.I_DALJoueur;
import modele.xml.I_DALTournoi;
import modele.xml.JoueurXML;
import modele.xml.TournoiXML;
import vue.RecapInfosTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurAjouterJoueurTournoi implements Initializable {

	private ObservableList<Joueur> joueurInscrit;

	private ObservableList<Joueur> resutatRecherche;

	@FXML
	private Label lb_nomTournoi;

	@FXML
	private ListView<Joueur> listePersonne;

	@FXML
	private ListView<Joueur> lv_recherche;


	@FXML
	Button button_OK;


	@FXML
	TextField tf_rechercher;

	@FXML
	Label lb_info;

	@FXML
	public void actionChercher(){
		Joueur j;
		ArrayList<Joueur> joueurs;
		resutatRecherche.clear();
		lb_info.setText("");
		j=ModeleJoueur.rechercherJoueur(tf_rechercher.getText().toString());
		if(j==null){
			joueurs=ModeleJoueur.rechercherNomJoueur(tf_rechercher.getText().toString());
			if(!joueurs.isEmpty()){
				resutatRecherche.addAll(joueurs);
			}
			else {
				lb_info.setStyle("-fx-color : red; ");
				lb_info.setText("Aucun joueur trouv�");
			}
		}
		else{
			resutatRecherche.add(j);
		}
	}

	@FXML
	public void ajouterJoueur(Event e) {
		Joueur joueurSelectionn� =  (Joueur)lv_recherche.getSelectionModel().getSelectedItem();
		if(joueurSelectionn�!=null){
			if(listePersonne.getItems().contains(joueurSelectionn�)){
				lb_info.setText("Ce joueur est d�j� inscrit");
			}
			else {
				lb_info.setText("");
				ajouterTrier(joueurInscrit, joueurSelectionn�);
				resutatRecherche.remove(joueurSelectionn�);
			}
		}
	}

	@FXML
	public void retirerJoueur(Event e) {
		joueurInscrit.remove((Joueur)listePersonne.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void actionAnnuler(Event e){
		RecapInfosTournoi recap = new RecapInfosTournoi(Main.getPrimaryStage());
		recap.show();
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void actionValider(Event e){
		for (Joueur joueur : joueurInscrit) {
			joueur.setDansTournoi(true);
			ModeleJoueur.modifDansTournoi(joueur.getNumLicence(), true);
		}
		I_DALJoueur joueurXML =  new JoueurXML();
		joueurXML.WriteJoueur(ModeleJoueur.getArrayJoueurs());
		
		listePersonne.setItems(joueurInscrit);
		
		ModeleTournoi.getTournoi().setListeJoueurs(listePersonne.getItems());
		
		I_DALTournoi tournoiXML = new TournoiXML(ModeleTournoi.getFichierTournoi());
		tournoiXML.writeTournoi(ModeleTournoi.getTournoi());
		RecapInfosTournoi recap = new RecapInfosTournoi(Main.getPrimaryStage());
		recap.show();
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		joueurInscrit = FXCollections.observableArrayList();
		 resutatRecherche = FXCollections.observableArrayList();
		
		lb_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());
		lb_info.setText("");
		if(ModeleTournoi.getTournoi().getListeJoueurs() != null){
			joueurInscrit.setAll(ModeleTournoi.getTournoi().getListeJoueurs());
		}
		listePersonne.setItems(joueurInscrit);
		lv_recherche.setItems(resutatRecherche);
		
	}

	void ajouterTrier(ObservableList<Joueur> data, Joueur j){

		data.add(j);
		FXCollections.sort(joueurInscrit, new Comparator<Joueur>() {

			@Override
			public int compare(Joueur j1, Joueur j2) {
				return j1.getNumLicence().compareTo(j2.getNumLicence());
			}
		});
	}

}
