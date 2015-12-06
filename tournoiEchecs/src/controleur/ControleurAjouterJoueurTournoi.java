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
		LocalDate dateActuelle = LocalDate.now();
		lb_info.setText("");
		if(Validation.verifDate(new DatePicker(dateActuelle), new DatePicker(ModeleTournoi.getTournoi().getDateDeb()))){
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
		else {
			lb_info.setText("Impossible d'ajouter des joueurs une fois le tournoi débuté");
		}
	}

	@FXML
	public void retirerJoueur(Event e) {
		data.remove(
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
		listePersonne.setItems(data);
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
			data.setAll(ModeleTournoi.getJoueurs());
		}
		listePersonne.setItems(data);
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
