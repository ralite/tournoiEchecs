package controleur;

import java.net.URL;

import java.util.ResourceBundle;

import metier.Joueur;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ControleurAjouterJoueurTournoi implements Initializable {

	@FXML
	private ListView<Joueur> listePersonne;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}

}
