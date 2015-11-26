package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import metier.Joueur;
import modele.ModeleJoueur;
import modele.Validation;

public class ControleurCreerJoueur implements Initializable {

	@FXML
	TextField tf_numLicence;

	@FXML
	Label lb_ErreurLicence;




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Joueur j1 = new Joueur("1", "jean", "jacques");
		Joueur j2 = new Joueur("2", "pierre", "paul");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);

	}

	@FXML
	public void onClick_Valider(Event e)
	{
		if(check(tf_numLicence) /*check pour comboBox et checkBox*/ /*AUTREs CHECK*/)
		{
			//tester numlicence
			if(ModeleJoueur.rechercherJoueur(tf_numLicence.getText().toString())==null)
			{
				//surcharger pour +infos
				//ModeleJoueur.creerJoueur(tf_numLicence.getText().toString(),);
				//Integer.parseInt(tf_classementElo.getText.toString());

				((Node)e.getSource()).getScene().getWindow().hide();
			}else{
				lb_ErreurLicence.setText("Le numéro de licence existe déjà.");
			}
		}
	}

	private boolean check(TextField tf)
	{
		if(Validation.estVide(tf))
		{
			tf.setStyle("-fx-control-inner-background : red; ");
			return false;
		} else {
			tf.setStyle("-fx-control-inner-background : white; ");
			return true;
		}

	}
}
