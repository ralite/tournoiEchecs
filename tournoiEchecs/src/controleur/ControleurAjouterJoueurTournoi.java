package controleur;


import java.awt.Color;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;







import application.Main;
import metier.Joueur;
import metier.Joueur;
import metier.departage.Departage;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.Validation;
import modele.xml.StockageXML;
import vue.RecapTournoi;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurAjouterJoueurTournoi implements Initializable {

	private ObservableList<Joueur> joueurInscrit = FXCollections.observableArrayList();
	
	private ObservableList<Joueur> resutatRecherche = FXCollections.observableArrayList();

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
				lb_info.setText("Aucun joueurs trouvés");
			}
		}
		else{
			resutatRecherche.add(j);
		}
	}

	@FXML
	public void ajouterJoueur(Event e) {
		Joueur joueurSelectionné =  (Joueur)lv_recherche.getSelectionModel().getSelectedItem();
		if(joueurSelectionné!=null){
			ajouterTrier(joueurInscrit, joueurSelectionné);
			resutatRecherche.remove(joueurSelectionné);
		}
	}

	@FXML
	public void retirerJoueur(Event e) {
		joueurInscrit.remove(
                (Joueur)listePersonne.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void actionAnnuler(Event e){
		RecapTournoi recap = new RecapTournoi(Main.getPrimaryStage());
		recap.show();
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	public void actionValider(Event e){
		listePersonne.setItems(joueurInscrit);
		ModeleTournoi.ajouterJoueurs(listePersonne.getItems());
		StockageXML.writeXMLTournoi(ModeleTournoi.getTournoi(), ModeleTournoi.getFichierTournoi().getPath(),1);
		RecapTournoi recap = new RecapTournoi(Main.getPrimaryStage());
		recap.show();
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lb_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());

		if(ModeleTournoi.getJoueurs()!=null){
			joueurInscrit.setAll(ModeleTournoi.getJoueurs());
		}
		listePersonne.setItems(joueurInscrit);
		lv_recherche.setItems(resutatRecherche);
	}

	void ajouterTrier(ObservableList<Joueur> data, Joueur j){
		int place=0;
		int i=0;
		if(data.size()!=0){
			while( i<data.size() && data.get(i).getNomJoueur().compareTo(j.getNomJoueur())<0 ){
				i++;
			}
		}
		data.add(j);
		place=i;

		for(;i<data.size()-1;i++){
			data.set(i+1, data.get(i));
		}
		data.set(place, j);

	}

}
