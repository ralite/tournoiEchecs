package controleur;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import metier.Joueur;
import metier.Partie;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.xml.TournoiXML;
import modele.xml.JoueurXML;
import vue.ItemAppariement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

public class ControleurAppariement implements Initializable {
	
	@FXML
	private Label label_titreAppariementJoueurs;

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

	private ObservableList<Joueur> itemsJoueursForfait;

	private Joueur joueurBlanc=null;

	private Joueur joueurNoir=null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(ModeleTournoi.getTournoi().getListeJoueurs());
		label_titreAppariementJoueurs.setText("Ronde "+String.valueOf(ModeleTournoi.getTournoi().getNumRondeActuelle()+1));
		System.out.println(ModeleTournoi.getTournoi());
		itemsParties = FXCollections.observableArrayList();
		itemsJoueursInscrits = FXCollections.observableArrayList();
		itemsJoueursAbsent = FXCollections.observableArrayList();
		itemsJoueursForfait = FXCollections.observableArrayList();
		itemsJoueursInscrits.addAll(ModeleTournoi.getTournoi().getListeJoueurs());
		if(ModeleTournoi.getTournoi().getPartieRondeActuelle()!=null){
			itemsParties.addAll(ModeleTournoi.getTournoi().getPartieRondeActuelle());
			itemsJoueursInscrits.removeAll(ModeleTournoi.getTournoi().getJoueursRondeActuelle());
		}
		lv_appariements.setItems(itemsParties);
		lv_joueurInscrit.setCellFactory(new Callback<ListView<Joueur>, ListCell<Joueur>>() {

		    @Override
		    public ListCell<Joueur> call(ListView<Joueur> p) {
		        return new ListCell<Joueur>() {

		            @Override
		            protected void updateItem(Joueur value, boolean empty) {
		            	String text=null;
		                super.updateItem(value, empty);
		                if (!empty && value != null) { 
		                	text = value.getNomJoueur()+" "+value.getPrenomJoueur()+ " " + value.getElo() + " | "+value.getScore()+" pts "+ value.getCouleur();
				               
		                }
		                 setText(text);     
		            }
		        };
		    
		}});
		lv_joueurInscrit.setItems(itemsJoueursInscrits);
		joueursTriesParPoints(itemsJoueursInscrits);

		lv_appariements.setCellFactory(lv -> new ItemAppariement());

		if(ModeleTournoi.getTournoi().getJoueurAbsRondeActuelle()!=null){
			itemsJoueursAbsent.addAll(ModeleTournoi.getTournoi().getJoueurAbsRondeActuelle());
			itemsJoueursInscrits.removeAll(itemsJoueursAbsent);
		}
		lv_absent.setItems(itemsJoueursAbsent);
		if(ModeleTournoi.getTournoi().getJoueurForfaitRondeActuelle()!=null){
			itemsJoueursForfait.addAll(ModeleTournoi.getTournoi().getJoueurForfaitRondeActuelle());
			itemsJoueursInscrits.removeAll(itemsJoueursForfait);
		}
		lv_forfait.setItems(itemsJoueursForfait);

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
				alert.setContentText("Ces deux joueurs ont déjà joués ensemble !");
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
			itemsJoueursForfait.add(joueurSelectionné);
			itemsJoueursInscrits.remove(joueurSelectionné);
		}
	}

	@FXML
	public void actionAnnuler(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void actionValider(){
		enregistrerApp();

	}

	@FXML
	public void actionLancerRonde(Event e){
		if(itemsJoueursInscrits.size()>1 || joueurBlanc!=null || joueurNoir!=null){
			AfficherAlerte();
		}
		else{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Appariement terminé");
			alert.setContentText("Voulez-vous vraiment lancer la ronde ?\n( Attention, il ne sera plus possible de modifier l'appariement !)");
			alert.showAndWait();
			if(alert.getResult().getText().equals("OK")){
				if(itemsJoueursInscrits.size()==1)
					itemsJoueursInscrits.get(0).gagne1Point();

				for (Partie partie : itemsParties) {
					partie.setCouleurJoueur();
				}
				for (Joueur joueur : itemsJoueursAbsent) {
					joueur.joueAbs();
				}
				for (Joueur joueur : itemsJoueursForfait) {
					joueur.joueForfait();
				}
				enregistrerApp();
				ModeleTournoi.getTournoi().getRondeActuelle().setApp(false);
				ModeleTournoi.getTournoi().getRondeActuelle().setSaisie(true);
				((Node)e.getSource()).getScene().getWindow().hide();
			}


		}

	}

	private void enregistrerApp() {
		if(itemsJoueursInscrits.size()==1){
			ModeleTournoi.getTournoi().getListeJoueurs().remove(itemsJoueursInscrits.get(0));
			ModeleTournoi.getTournoi().getListeJoueurs().add(itemsJoueursInscrits.get(0));
		}
		ModeleTournoi.getTournoi().setPartiesRonde(itemsParties);
		ModeleTournoi.getTournoi().setAbsentRonde(itemsJoueursAbsent);
		ModeleTournoi.getTournoi().setForfaitRonde(itemsJoueursForfait);
		TournoiXML.writeXMLTournoi(ModeleTournoi.getTournoi(), ModeleTournoi.getFichierTournoi());
	}

	private void AfficherAlerte() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Erreur");
		alert.setContentText("Tout les joueurs ne sont pas apairer !");
		alert.showAndWait();
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
			itemsJoueursForfait
			.remove(joueurSelectionné);
			itemsJoueursInscrits.add(joueurSelectionné);
		}
	}
	
	void joueursTriesParPoints(ObservableList<Joueur> data){

		FXCollections.sort(itemsJoueursInscrits, new Comparator<Joueur>() {

			@Override
			public int compare(Joueur j1, Joueur j2) {
				if(j1.getScore()==j2.getScore()){
					return Integer.compare(j2.getElo(),j1.getElo());
				}
				else
					return Float.compare(j2.getScore(),j1.getScore());
			}

		});
	}
}
