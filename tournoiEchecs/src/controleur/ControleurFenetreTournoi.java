package controleur;

import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.jws.Oneway;

import com.sun.javafx.scene.control.skin.ButtonSkin;

import modele.ModeleDepartage;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vue.FenetreFileChooser;
import vue.RecapTournoi;
import metier.Tournoi;
import metier.departage.Departage;
import modele.ModeleTournoi;
import modele.Validation;
import modele.xml.TournoiXML;

public class ControleurFenetreTournoi implements Initializable {

	private ObservableList<Departage> items;
	
	private ObservableList<Departage> itemsChoisis;
	@FXML
	ListView<Departage> lv_listeDepartages;

	@FXML
	ListView<Departage> lv_listeDepartagesChoisis;

	@FXML
	DatePicker dp_dateDeb;

	@FXML
	DatePicker dp_dateFin;

	@FXML
	TextField tf_nomTournoi;

	@FXML
	TextField tf_lieuTournoi;

	@FXML
	TextField tf_nbRondes;

	@FXML
	TextField tf_arbitre;

	@FXML
	Label lb_erreurDate;

	@FXML
	ComboBox<String> cb_cadences;
	
	@FXML
	TextArea ta_messageAide ;


	@FXML
    private void actionValider(Event e) {
		if (formulaireRempli()) {
			if (infosCorrectes()){
				if(ModeleTournoi.getTournoi()==null){
					Tournoi tournoi = new Tournoi(tf_nomTournoi.getText(),tf_lieuTournoi.getText(),dp_dateDeb.getValue(),dp_dateFin.getValue(),tf_arbitre.getText(),Integer.valueOf(tf_nbRondes.getText()),cb_cadences.getSelectionModel().getSelectedItem());
					tournoi.setListeDepartages(itemsChoisis);
					ModeleTournoi.ajouterTournoi(tournoi);
					File file = FenetreFileChooser.EnregistrerTournoi(Main.getPrimaryStage());
					ModeleTournoi.setFichierTournoi(file.getPath() + "\\tournoi_" + tournoi.getNom() + "_" + tournoi.getLieu() + "_" + tournoi.getDateDeb().toString() + ".xml");
					TournoiXML.writeXMLTournoi(ModeleTournoi.getTournoi(), ModeleTournoi.getFichierTournoi());
				}
				else{
					ModeleTournoi.getTournoi().setNom(tf_nomTournoi.getText());
					ModeleTournoi.getTournoi().setLieu(tf_lieuTournoi.getText());
					ModeleTournoi.getTournoi().setDateDeb(dp_dateDeb.getValue());
					ModeleTournoi.getTournoi().setDateFin(dp_dateFin.getValue());
					ModeleTournoi.getTournoi().setArbitre(tf_arbitre.getText());
					ModeleTournoi.getTournoi().setNbRondes(Integer.valueOf(tf_nbRondes.getText()));
					ModeleTournoi.getTournoi().setCadenceJeu(cb_cadences.getSelectionModel().getSelectedItem());
					TournoiXML.writeXMLTournoi(ModeleTournoi.getTournoi(), ModeleTournoi.getFichierTournoi());
				}

				RecapTournoi rt = new RecapTournoi(Main.getPrimaryStage());
				rt.show();
				((Node)e.getSource()).getScene().getWindow().hide();
			}
		}
	}

	private boolean formulaireRempli(){
		boolean res = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lb_erreurDate.setText("");
		if(!Validation.recupValeursDate(dp_dateDeb)){
			lb_erreurDate.setText("Remplir la date de debut");
			res=false;
		}

		if(!Validation.recupValeursDate(dp_dateFin)){
			lb_erreurDate.setText("Remplir la date de fin");
			res=false;
		}

		if(Validation.estVide(tf_nomTournoi))
			res = false;
		if(Validation.estVide(tf_lieuTournoi))
			res = false;
		if(Validation.estVide(dp_dateDeb)){
			res = false;
		}else {
			dp_dateDeb.setValue(LocalDate.parse(dp_dateDeb.getEditor().getText().toString(),formatter));

		}
		if(Validation.estVide(dp_dateFin)){
			res = false;
		}else{
			dp_dateFin.setValue(LocalDate.parse(dp_dateFin.getEditor().getText().toString(),formatter));
		}
		if(Validation.estVide(tf_arbitre))
			res = false;
		if(Validation.estVide(tf_nbRondes))
			res = false;
		if(Validation.estVide(lv_listeDepartagesChoisis))
			res = false;
		return res;
	}

	private boolean infosCorrectes(){

		boolean res = true;
		DatePicker dateActuelle = new DatePicker(LocalDate.now());
		lb_erreurDate.setText("");
		if(!Validation.verifDate(dp_dateDeb,dp_dateFin)){
			lb_erreurDate.setText("Verifiez les dates");
			res = false;
		}
		else if(!Validation.verifDate(dateActuelle, dp_dateDeb)){
			lb_erreurDate.setText("date actuelle < date de début");
			res=false;
			}
			else if(!Validation.verifDate(dateActuelle, dp_dateFin)){
				lb_erreurDate.setText("1 date actuelle < date de fin");
				res=false;
				}
		if(!Validation.estNomCompose(tf_arbitre))
			res = false;
		if (lv_listeDepartagesChoisis.getItems().size()<3){
			lv_listeDepartagesChoisis.setStyle("-fx-control-inner-background : red; ");
			res=false;
		}
		return res;

	}

	@FXML
	public void actionAnnuler(Event e) {
		if(ModeleTournoi.getTournoi()!=null){
			RecapTournoi rp = new RecapTournoi(Main.getPrimaryStage());
			rp.show();
		}
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void actionRajouterDepartage(){
		Departage dep =  (Departage)lv_listeDepartages.getSelectionModel().getSelectedItem();
		if(dep!=null){
			itemsChoisis.add(dep);
			items.remove(dep);
		}
	}

	@FXML
	public void actionEnleverDepartage(){
		Departage dep=(Departage)lv_listeDepartagesChoisis.getSelectionModel().getSelectedItem();
		if(dep!=null){
		itemsChoisis.remove(dep);
		items.add(dep);
		}
		else {
		}
	}

	@FXML
	public void limiteTexte(){
		Validation.verifLongueurTexte(tf_nomTournoi,50);
		Validation.verifLongueurTexte(tf_lieuTournoi,50);
		Validation.verifLongueurTexte(tf_arbitre,30);
		Validation.verifLongueurTexte(tf_nbRondes,6);
	}

	private void chiffresSeulement(Number oldValue, Number newValue, TextField leChampDeSaisie){
		if(newValue.intValue() > oldValue.intValue()){
            if(!Validation.estChiffre(leChampDeSaisie)){
            	leChampDeSaisie.setText(leChampDeSaisie.getText().replaceAll("[^0-9]",""));
            }
		}
	}
	
	public void affichageAideSurvolNomCadence() {
		if(cb_cadences.isFocused()){
			texteAideCadences();
			ta_messageAide.setVisible(true);
		}
		else ta_messageAide.setVisible(false);
	}
	
	public void texteAideCadences(){
		switch (cb_cadences.getSelectionModel().getSelectedItem()){
			case "Blitz" :
				ta_messageAide.setText("Les parties de blitz sont des parties rapides dont la cadence est inférieure à 15 minutes par joueur, et presque toujours, dans la pratique, de 5 minutes par joueur.");
				break;
			case "Semi-rapide" :
				ta_messageAide.setText("La cadence semi-rapide est une cadence intermédiaire. Les parties semi-rapides sont les parties qui créditent les joueurs de 15 minutes chacun au minimum, et 60 minutes chacun au maximum, ou l’équivalent en cadence « Fischer ».");
				break;
			case "Cadence longue" : 
				ta_messageAide.setText("Les parties longues ou « parties sérieuses » sont les parties qui créditent les joueurs de plus de 60 minutes chacun, ou l’équivalent en cadence « Fischer ».");
				break;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Blitz",
			        "Semi-rapide",
			        "Cadence longue"
			    );
		cb_cadences.setItems(options);
		items =FXCollections.observableArrayList (ModeleDepartage.getcollectionDepartages());
		itemsChoisis =FXCollections.observableArrayList ();
		tf_nbRondes.lengthProperty().addListener((observable,oldValue,newValue)->chiffresSeulement(oldValue,newValue,tf_nbRondes));
		
		if(ModeleTournoi.getTournoi()!=null){
			tf_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());
			tf_lieuTournoi.setText(ModeleTournoi.getTournoi().getLieu());
			dp_dateDeb.setValue(ModeleTournoi.getTournoi().getDateDeb());
			dp_dateFin.setValue(ModeleTournoi.getTournoi().getDateFin());
			tf_arbitre.setText(ModeleTournoi.getTournoi().getArbitre());
			tf_nbRondes.setText(String.valueOf(ModeleTournoi.getTournoi().getNbRondes()));
			itemsChoisis.addAll(ModeleTournoi.getTournoi().getListeDepartages());
			items.removeAll(itemsChoisis);
			cb_cadences.getSelectionModel().getSelectedItem();
			}
		
		lv_listeDepartages.setItems(items);
		lv_listeDepartagesChoisis.setItems(itemsChoisis);

	}

}
