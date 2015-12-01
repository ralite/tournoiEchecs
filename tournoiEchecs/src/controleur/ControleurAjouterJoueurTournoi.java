package controleur;


import java.awt.Color;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;






import application.Main;
import metier.Joueur;
import metier.Joueur;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.Validation;
import vue.RecapTournoi;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurAjouterJoueurTournoi implements Initializable {

	private ObservableList<Joueur> data = FXCollections.observableArrayList();

	@FXML
	private Label lb_nomTournoi;

	@FXML

	private ListView<Joueur> listePersonne;

	@FXML
	Button button_OK;


	@FXML
	TextField tf_numLicence;

	@FXML
	Label lb_info;



	@FXML
	public void ajouterJoueur(Event e) {
		lb_info.setText("");
		if(Validation.verifNumLicence(tf_numLicence)){
		//recherche joueur
			Joueur j = ModeleJoueur.rechercherJoueur(tf_numLicence.getText());
			//test si le joueur retourné n'est pas nulll !
			if(j==null){
			lb_info.setText("numéro de licence introuvable");
			}
			else if(data.contains(j)){

					lb_info.setText("deja present");

				}
				else{
					ajouterTrier(data, j);
			}
		}else {
			lb_info.setText("numéro de licence incohérent");
		}

		tf_numLicence.setText("");
	}

	@FXML
	public void retirerJoueur(Event e) {
		data.remove(
                (Joueur)listePersonne.getSelectionModel().getSelectedItem());
	}


	@FXML
	public void actionOK(Event e){
		listePersonne.setItems(data);
		ModeleTournoi.ajouterJoueurs(listePersonne.getItems());
		RecapTournoi recap = new RecapTournoi(Main.getPrimaryStage());
		recap.show();
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lb_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());

		Joueur j1 = new Joueur("A11111", "jean", "jacques");
		Joueur j2 = new Joueur("A22222", "pierre", "paul");
		Joueur j3 = new Joueur("B11111", "pierre", "paul");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);
		ModeleJoueur.ajouterJoueur(j3);
		listePersonne.setItems(data);
		System.out.println(data.size());
	}

	void ajouterTrier(ObservableList<Joueur> data, Joueur j){
		int place=0;
		int i=0;
		if(data.size()!=0){
			while( i<data.size() && data.get(i).getNumLicence().compareTo(j.getNumLicence())<0 ){
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
