package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.ItemAppariement;
import vue.ItemResultatsRonde;
import vue.ItemSaisieResultat;
import metier.Partie;
import modele.ModeleTournoi;
import modele.xml.TournoiXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleurSaisieResultat implements Initializable{

	@FXML
	private Label label_titreSaisieResultats;

	@FXML
	private ListView<Partie> lv_resultats;

	@FXML
	private ListView<Partie> lv_parties;

	@FXML
	private TextField tf_recherche;

	@FXML
	private ChoiceBox<String> cb_res;


	private ObservableList<Partie> itemResultat;

	private ObservableList<Partie> itemRechercher;

	private ObservableList<Partie> itemPartie;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cb_res.setItems(FXCollections.observableArrayList("Blancs gagnent","Noirs gagnent","Partie nulle","Blancs forfaits","Noirs Forfaits","Double forfait"));
		label_titreSaisieResultats.setText("Résultats de la ronde "+String.valueOf(ModeleTournoi.getTournoi().getNumRondeActuelle()+1));
		itemResultat = FXCollections.observableArrayList();
		itemPartie = FXCollections.observableArrayList();
		itemPartie.addAll(ModeleTournoi.getTournoi().getPartieRondeActuelle());
		lv_parties.setItems(itemPartie);
		lv_parties.setCellFactory(lv -> new ItemAppariement());
		lv_resultats.setItems(itemResultat);
		lv_resultats.setCellFactory(lv -> new ItemSaisieResultat());
		itemRechercher = FXCollections.observableArrayList();
		for (Partie partie : itemPartie) {
			if(!partie.getResultat().equals("")){
				itemResultat.add(partie);
			}
		}
		itemPartie.removeAll(itemResultat);
	}

	@FXML
	private void validerResultat(){
		Partie p =lv_parties.getSelectionModel().getSelectedItem();
		String res = cb_res.getValue();
		if(res==null || p==null){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Veuillez selectionner une partie et un resultat !");
			alert.showAndWait();
		}
		else{
			switch (res) {
			case "Blancs gagnent":
				p.joueurBlancGagne();
				break;
			case "Noirs gagnent":
				p.joueurNoirGagne();
				break;
			case "Partie nulle":
				p.partieNulle();
				break;
			case "Blancs forfaits":
				p.joueurBlancForfait();
				break;
			case "Noirs forfaits":
				p.joueurNoirForfait();
				break;
			case "Double forfait":
				p.doubleForfait();
				break;
			}
			itemResultat.add(p);
			itemPartie.remove(p);
			if(itemRechercher.contains(p)){
				itemRechercher.remove(p);
			}
		}
	}


	@FXML
	public void validerSaisieResultat(Event e){
		itemRechercher.addAll(itemPartie);
		ModeleTournoi.getTournoi().setPartiesRonde(itemResultat);
		TournoiXML.writeXMLTournoi(ModeleTournoi.getTournoi(), ModeleTournoi.getFichierTournoi());

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("L'appariement a bien été sauvegardé !");
		alert.showAndWait();
		if(alert.getResult().getText().equals("OK"))
			((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void terminerSaisieResultat(Event e){
		if(itemPartie.size()>0){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setContentText("Toutes les parties ne sont pas saisies !");
			alert.showAndWait();
		}else{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Saisie terminée");
			alert.setContentText("Voulez-vous vraiment mettre fin à la saisie de résultat de cette ronde?"
					+ "\n(Attention, les résultats de cette ronde seront non-modifiables !)");
			alert.showAndWait();
			if(alert.getResult().getText().equals("OK")){
				for (Partie partie : itemResultat) {
					partie.setScore();
				}
				ModeleTournoi.getTournoi().setPartiesRonde(itemResultat);
				ModeleTournoi.getTournoi().getRondeActuelle().setSaisie(false);
				if(ModeleTournoi.getTournoi().getNumRondeActuelle()+1<ModeleTournoi.getTournoi().getNbRondes()){
					ModeleTournoi.getTournoi().rondeSuivante();
					ModeleTournoi.getTournoi().getRondeActuelle().setApp(true);
				}
				else{
					ModeleTournoi.getTournoi().tournoiFini();
				}
				TournoiXML.writeXMLTournoi(ModeleTournoi.getTournoi(), ModeleTournoi.getFichierTournoi());
				((Node)e.getSource()).getScene().getWindow().hide();
			}

		}

	}

	@FXML
	public void retirerPartie(){
		Partie p =lv_resultats.getSelectionModel().getSelectedItem();
		if(p!=null){
			itemResultat.remove(p);
			itemPartie.add(p);
		}
	}


	@FXML
	public void rechercherPartie(){
		itemRechercher.clear();
		for (Partie partie :itemPartie) {
			if(partie.rechercherPartie(tf_recherche.getText().toString()))
				itemRechercher.add(partie);
		}
		lv_parties.setItems(itemRechercher);
	}

	@FXML
	public void toutesParties(){
		lv_parties.setItems(itemPartie);
	}

}
