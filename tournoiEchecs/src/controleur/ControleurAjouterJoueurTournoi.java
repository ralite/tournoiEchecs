package controleur;


import java.awt.Color;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

















import metier.Joueur;
import metier.TestJooueur;
import modele.ModeleJoueur;
import modele.ModeleTournoi;
import modele.Validation;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurAjouterJoueurTournoi implements Initializable {
	
	private ObservableList<TestJooueur> data = FXCollections.observableArrayList();

	@FXML
	private Label lb_nomTournoi;
	
	@FXML
	private ListView<TestJooueur> listePersonne;
	
	@FXML
	TextField tf_numLicence;
	
	@FXML
	Label lb_info;
	
	@FXML
	public void ajouterJoueur(Event e) {
		lb_info.setText("");
		//recherche joueur
		if(Validation.estEntierPos(tf_numLicence)){
			TestJooueur j = ModeleJoueur.rechercherJoueur(Integer.parseInt(tf_numLicence.getText()));
			//test si le joueur retourné n'est pas nulll !
			if(j==null){
			lb_info.setText("numéro de licence introuvable");
			}
			else if(data.contains(j)){
					lb_info.setText("deja present");
				}
				else{
					data.add(j);
			}
		}
		else {
			lb_info.setText("numéro de licence incohérent");
		}
	
		tf_numLicence.setText("");
	}
	
	@FXML
	public void retirerJoueur(Event e) {
		data.remove(
                (TestJooueur)listePersonne.getSelectionModel().getSelectedItem());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lb_nomTournoi.setText(ModeleTournoi.getTournoi().getNomTournoi());
		
		TestJooueur j1 = new TestJooueur(1, "jean", "jacques");
		TestJooueur j2 = new TestJooueur(2, "boubi", "baaa");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);

		
		listePersonne.setItems(data);
		
	}

}
