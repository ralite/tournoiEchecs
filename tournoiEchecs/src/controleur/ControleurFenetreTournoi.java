package controleur;

import javafx.scene.Node;
import javafx.scene.control.*;

import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import modele.ModeleDepartage;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vue.AjouterJoueurTournoi;
import vue.RecapTournoi;
import metier.Tournoi;
import metier.departage.Departage;
import modele.ModeleTournoi;
import modele.Validation;

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
	Button button_addDepartage;
	
	@FXML
	Button button_removeDepartage;

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
    private void actionFenetreJoueurs(Event e) {

		if (formulaireRempli()) {
			if (infosCorrectes()){
				Tournoi tournoi = new Tournoi(tf_nomTournoi.getText(),tf_lieuTournoi.getText(),dp_dateDeb.getValue(),dp_dateFin.getValue(),tf_arbitre.getText(),Integer.valueOf(tf_nbRondes.getText()));
				tournoi.setListeDepartages(new ArrayList<Departage>(itemsChoisis));
				ModeleTournoi.ajouterTournoi(tournoi);	
				
				RecapTournoi rt = new RecapTournoi(Main.getPrimaryStage());
				rt.show();
				((Node)e.getSource()).getScene().getWindow().hide();
			}
		}
	}
	
 
	private boolean formulaireRempli(){
		boolean res = true;

		if(Validation.estVide(tf_nomTournoi))
			res = false;
		if(Validation.estVide(tf_lieuTournoi))
			res = false;
		if(Validation.estVide(dp_dateDeb))
			res = false;
		if(Validation.estVide(dp_dateFin))
			res = false;
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

		if(!Validation.verifDate(dp_dateDeb,dp_dateFin))
			res = false;
		if(!Validation.nomcompos�(tf_arbitre))
			res = false;
		if (lv_listeDepartagesChoisis.getItems().size()<3){
			lv_listeDepartagesChoisis.setStyle("-fx-control-inner-background : red; ");
			res=false;
		}
		return res;
		
	}
	@FXML
	public void actionAnnuler(Event e) {
		//if (showConfirm("Voulez-vous vraiment annuler l'op�ration ?", Main.getPrimaryStage()))
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
		Validation.verifLongueurTexte(tf_nomTournoi,30);
		Validation.verifLongueurTexte(tf_lieuTournoi,30);
		Validation.verifLongueurTexte(tf_arbitre,30);
		Validation.verifLongueurTexte(tf_nbRondes,6);
		
	}

	

	private void chiffresSeulement(Number oldValue, Number newValue, TextField leChampDeSaisie){
		if(newValue.intValue() > oldValue.intValue()){
            char ch = leChampDeSaisie.getText().charAt(newValue.intValue()-1);
            if(!Validation.estChiffre(leChampDeSaisie)){
            	leChampDeSaisie.setText(leChampDeSaisie.getText().replaceAll("[a-zA-Z]",""));
            }
       }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		items =FXCollections.observableArrayList (
				ModeleDepartage.getcollectionDepartages());
		itemsChoisis =FXCollections.observableArrayList ();
		tf_nbRondes.lengthProperty().addListener((observable,oldValue,newValue)->chiffresSeulement(oldValue,newValue,tf_nbRondes));
		
		if(ModeleTournoi.getTournoi()!=null){
			tf_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());
			tf_lieuTournoi.setText(ModeleTournoi.getTournoi().getNom());
			dp_dateDeb.setValue(ModeleTournoi.getTournoi().getDateDeb());
			dp_dateFin.setValue(ModeleTournoi.getTournoi().getDateFin());
			tf_arbitre.setText(ModeleTournoi.getTournoi().getArbitre());
			tf_nbRondes.setText(String.valueOf(ModeleTournoi.getTournoi().getNbRondes()));
			itemsChoisis.addAll(ModeleTournoi.getTournoi().getListeDepartages());
			items.removeAll(itemsChoisis);
		}
		lv_listeDepartages.setItems(items);
		lv_listeDepartagesChoisis.setItems(itemsChoisis);
		
	}

}
