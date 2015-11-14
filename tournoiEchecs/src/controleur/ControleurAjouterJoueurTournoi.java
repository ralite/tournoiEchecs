package controleur;


import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;













import metier.Joueur;
import metier.TestJooueur;
import modele.ModeleJoueur;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControleurAjouterJoueurTournoi implements Initializable {
	
	private ObservableList<TestJooueur> data = FXCollections.observableArrayList();

	@FXML
	private ListView<TestJooueur> listePersonne;
	
	@FXML
	TextField tf_numLicence;
	
	@FXML
	public void ajouterJoueur(Event e) {
		//recherche joueur
		TestJooueur j = ModeleJoueur.rechercherJoueur(Integer.parseInt(tf_numLicence.getText()));
		//test si le joueur retourné n'est pas nulll !
		if(j==null){
		System.out.println("num licence introuvé");
		}
		else if(data.contains(j)){
				System.out.println("deja present");
			}
			else{
				data.add(j);
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
		TestJooueur j1 = new TestJooueur(1, "jean", "jacques");
		TestJooueur j2 = new TestJooueur(2, "boubi", "baaa");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);

		
		listePersonne.setItems(data);
		
	}

}
