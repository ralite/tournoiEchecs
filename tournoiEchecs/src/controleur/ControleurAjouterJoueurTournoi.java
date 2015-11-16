package controleur;


import java.awt.Color;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

















import metier.Joueur;
import metier.Joueur;
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

	private ObservableList<Joueur> data = FXCollections.observableArrayList();

	@FXML
	private Label lb_nomTournoi;

	@FXML

	private ListView<Joueur> listePersonne;


	@FXML
	TextField tf_numLicence;

	@FXML
	Label lb_info;

	@FXML
	public void ajouterJoueur(Event e) {
		lb_info.setText("");
		//recherche joueur
		if(Validation.estEntierPos(tf_numLicence)){


			Joueur j = ModeleJoueur.rechercherJoueur(Integer.parseInt(tf_numLicence.getText()));
			//test si le joueur retourn� n'est pas nulll !
			if(j==null){
			lb_info.setText("num�ro de licence introuvable");
			}
			else if(data.contains(j)){

					lb_info.setText("deja present");

					lb_info.setText("d�j� present");
				}
				else{
					data.add(j);
			}
		}
		else {
			lb_info.setText("num�ro de licence incoh�rent");
		}

		tf_numLicence.setText("");
	}

	@FXML
	public void retirerJoueur(Event e) {
		data.remove(
                (Joueur)listePersonne.getSelectionModel().getSelectedItem());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lb_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());

		Joueur j1 = new Joueur(1, "jean", "jacques");
		Joueur j2 = new Joueur(2, "pierre", "paul");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);


		listePersonne.setItems(data);

	}

}
