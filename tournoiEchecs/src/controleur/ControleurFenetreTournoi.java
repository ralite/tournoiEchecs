package controleur;

import javafx.scene.Node;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import modele.ModeleDepartage;
import application.Main;
import metier.Departage;
import metier.TestJooueur;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;
import vue.AjouterJoueurTournoi;
import vue.CreationTournoi;
import metier.TestJooueur;
import metier.Tournoi;
import modele.ModeleJoueur;
import modele.ModeleTournoi;

public class ControleurFenetreTournoi implements Initializable {
	
	
	
	/*private ObjectProperty<Departage> DepartageSelectionne = new SimpleObjectProperty<>();
	public final ObjectProperty<Departage> DepartageSelectionneProperty() {return this.DepartageSelectionne;}
	public final Departage getDepartageSelectionne() {return this.DepartageSelectionneProperty().get();}
	public final void setDepartageSelectionne(final Departage DepartageSelectionne) {this.DepartageSelectionneProperty().set(DepartageSelectionne);}
*/
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
    private void actionFenetreJoueurs(Event e) {
		if (check(tf_nomTournoi) && check(tf_lieuTournoi) && check(tf_arbitre) && check(tf_nbRondes) && (dp_dateDeb.getValue()!=null) && (dp_dateFin.getValue()!=null)) {
			Tournoi tournoi = new Tournoi(tf_nomTournoi.getText(),tf_lieuTournoi.getText(),dp_dateDeb.getValue(),dp_dateFin.getValue(),tf_arbitre.getText(),Integer.valueOf(tf_nbRondes.getText()));
			ModeleTournoi.ajouterTournoi(tournoi);	
			
    	AjouterJoueurTournoi ajoutjoueur = new AjouterJoueurTournoi(Main.getPrimaryStage());
		ajoutjoueur.show();
		}
		//else showWarning("Merci de remplir tous les champs", ((Node)e.getSource()).getScene().getWindow());
    }
	
	
	@FXML
	public void actionAnnuler(Event e) {
		//if (showConfirm("Voulez-vous vraiment annuler l'opération ?", Main.getPrimaryStage()))
			((Node)e.getSource()).getScene().getWindow().hide();
	}
	
	

	private boolean check(TextField leChampDeSaisie) {
		if (leChampDeSaisie.getText().trim().isEmpty()) {
			leChampDeSaisie.setStyle("-fx-control-inner-background : red; ");
			return false;
		} else {
			leChampDeSaisie.setStyle("-fx-control-inner-background : white; ");
			return true;
		}
	}
	
	@FXML
	public void actionRajouterDepartage(Event e){
		itemsChoisis.add((Departage)lv_listeDepartages.getSelectionModel().getSelectedItem());
		items.remove(
                (Departage)lv_listeDepartages.getSelectionModel().getSelectedItem());
		Departage dep =  (Departage)lv_listeDepartages.getSelectionModel().getSelectedItem();
		itemsChoisis.add(dep);
		items.remove(dep);
	}
	
	
	@FXML
	public void actionEnleverDepartage(Event e){
		itemsChoisis.remove(
                (Departage)lv_listeDepartagesChoisis.getSelectionModel().getSelectedItem());
		items.add((Departage)lv_listeDepartagesChoisis.getSelectionModel().getSelectedItem());
	}
	
	
	private void chiffresSeulement(Number oldValue, Number newValue, TextField leChampDeSaisie){
		if(newValue.intValue() > oldValue.intValue()){
            char ch = leChampDeSaisie.getText().charAt(newValue.intValue()-1);
            if(!(ch >= '0' && ch <= '9' )){       
            	leChampDeSaisie.setText(leChampDeSaisie.getText().substring(0,leChampDeSaisie.getText().length()-1)); 
            }
       }
	}

	/*
	private boolean showConfirm(String message, Window owner) {
		return (Dialogs.create()
			      .owner(owner)
			      .title("Question")
			      .message(message)
			      .actions(Dialog.ACTION_YES,Dialog.ACTION_NO)
			      .showConfirm() == Dialog.ACTION_YES);
	}*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lv_listeDepartages.setItems(items);
		itemsChoisis =FXCollections.observableArrayList ();
		lv_listeDepartagesChoisis.setItems(itemsChoisis);
		tf_nbRondes.lengthProperty().addListener((observable,oldValue,newValue)->chiffresSeulement(oldValue,newValue,tf_nbRondes));
		//DepartageSelectionne.bind(lv_listeDepartages.getSelectionModel().selectedItemProperty());
	}

}
