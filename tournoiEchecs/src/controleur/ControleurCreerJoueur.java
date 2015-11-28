package controleur;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import metier.Joueur;
import modele.ModeleJoueur;
import modele.Validation;

public class ControleurCreerJoueur implements Initializable {

	@FXML
	TextField tf_numLicence;

	@FXML
	TextField tf_nom;

	@FXML
	TextField tf_prenom;

	@FXML
	TextField tf_titreFide;

	@FXML
	ChoiceBox<String> chbx_sexe;
	private ObservableList<String> listeSexe = FXCollections.observableArrayList("Homme","Femme");

	@FXML
	TextField tf_ligue;

	@FXML
	TextField tf_classementElo;

	@FXML
	DatePicker dp_dateNaissance;

	@FXML
	TextField tf_categorie;

	@FXML
	TextField tf_club;

	@FXML
	CheckBox ckbx_national;

	@FXML
	CheckBox ckbx_etranger;

	@FXML
	CheckBox ckbx_fide;

	@FXML
	Label lb_erreurLicence;

	@FXML
	Label lb_erreurNom;

	@FXML
	Label lb_erreurPrenom;

	@FXML
	Label lb_erreurSexe;

	@FXML
	Label lb_erreurDate;

	@FXML
	Label lb_erreurTitreFide;

	@FXML
	Label lb_erreurLigue;

	@FXML
	Label lb_erreurElo;

	@FXML
	Label lb_erreurCategorie;

	@FXML
	Label lb_erreurClub;

	@FXML
	Label lb_erreur;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Joueur j1 = new Joueur("1", "jean", "jacques");
		Joueur j2 = new Joueur("2", "pierre", "paul");
		ModeleJoueur.ajouterJoueur(j1);
		ModeleJoueur.ajouterJoueur(j2);

		lb_erreurLicence.setText("");
		lb_erreurNom.setText("");
		lb_erreurPrenom.setText("");
		lb_erreurSexe.setText("");
		lb_erreurDate.setText("");
		lb_erreurTitreFide.setText("");
		lb_erreurLigue.setText("");
		lb_erreurElo.setText("");
		lb_erreurCategorie.setText("");
		lb_erreurClub.setText("");
		lb_erreur.setText("");

		chbx_sexe.setItems(listeSexe);
	}

	@FXML
	public void onClick_Valider(Event e)
	{
		if(formulaireRempli())
		{
			if(infosCorrectes())
			{
				lb_erreur.setText("ok");
				//ModeleJoueur.creerJoueur(tf_numLicence.getText().toString(),tf_prenom.getText().toString(),chbx_sexe.getValue().toString(),
				//dp_dateNaissance.getEditor().getText().toString(),tf_titreFide.getText().toString(),tf_ligue.getText().toString(),
				//Integer.parseInt(tf_classementElo.getText().toString()),tf_categorie.getText().toString(),tf_club.getTet().toString());

				//((Node)e.getSource()).getScene().getWindow().hide();
			}//infosCorrectes
		}//formulaireRempli

	}


	private boolean formulaireRempli()
	{//plusieurs if pour avoir plusieurs champs en rouge et pas seulement le premier testé

		boolean res = true;

		if(Validation.estVide(tf_numLicence))
			res = false;
		if(Validation.estVide(tf_nom))
			res = false;
		if(Validation.estVide(tf_prenom))
			res = false;
		if(chbx_sexe.getValue() == null)
		{
			lb_erreurSexe.setText("Selectionnez le sexe du joueur.");
			res = false;
		}else
			lb_erreurSexe.setText("");
		if(Validation.estVide(dp_dateNaissance))
			res = false;
		if(Validation.estVide(tf_titreFide))
			res = false;
		if(Validation.estVide(tf_ligue))
			res = false;
		if(Validation.estVide(tf_classementElo))
			res = false;
		if(Validation.estVide(tf_categorie))
			res = false;
		if(Validation.estVide(tf_club))
			res = false;
		if(!ckbx_national.selectedProperty().getValue() && !ckbx_etranger.selectedProperty().getValue() && !ckbx_fide.selectedProperty().getValue())
		{
			lb_erreurElo.setText("Sélectionnez un type d'ELO.");
			res = false;
		}else
			lb_erreurElo.setText("");



		return res;
	}

	private boolean infosCorrectes()
	{
		boolean res = true;

		//numLicence
		if(!Validation.verifNumLicence(tf_numLicence.getText().toString()))
		{
			lb_erreurLicence.setText("Le numéro de licence n'est pas au format A99999.");
			tf_numLicence.setStyle("-fx-control-inner-background : red; ");
			res = false;
		}else if(ModeleJoueur.rechercherJoueur(tf_numLicence.getText().toString()) != null)
		{
			lb_erreurLicence.setText("Le numéro de licence existe déjà.");
			tf_numLicence.setStyle("-fx-control-inner-background : red; ");
			res = false;
		}else{
			lb_erreurLicence.setText("");
			tf_numLicence.setStyle("-fx-control-inner-background : white; ");
		}


		if(!Validation.estChaine(tf_nom))
		{
			lb_erreurNom.setText("Saisissez un nom valide.");
			res = false;
		}else
		{
			lb_erreurNom.setText("");
		}

		if(!Validation.estChaine(tf_prenom))
		{
			lb_erreurPrenom.setText("Saisissez un prénom valide.");
			res = false;
		}else
		{
			lb_erreurPrenom.setText("");
		}

		if(!Validation.verifDate(dp_dateNaissance, new DatePicker(LocalDate.now())))
		{
			lb_erreurDate.setText("Saisissez une date inférieure à la date actuelle.");
			res = false;
		} 

		//titreFide

		//ligue


		if(!Validation.estEntierPos(tf_classementElo))
		{
			lb_erreurElo.setText("Saisissez un classement ELO valide.");
			tf_classementElo.setStyle("-fx-control-inner-background : red; ");
			res = false;
		}else
		{
			lb_erreurElo.setText("");
			tf_classementElo.setStyle("-fx-control-inner-background : white; ");
			if(Integer.parseInt(tf_classementElo.getText()) < 500 || Integer.parseInt(tf_classementElo.getText()) > 3000 )
			{
				lb_erreurElo.setText("Saisissez un classement ELO entre 500 et 3000.");
				tf_classementElo.setStyle("-fx-control-inner-background : red; ");
				res = false;
			}else
			{
				lb_erreurElo.setText("");
				tf_classementElo.setStyle("-fx-control-inner-background : white; ");
			}
		}



		//categorie

		if(!Validation.estChaine(tf_club))
		{
			lb_erreurClub.setText("Saisissez un nom de club valide.");
			res = false;
		}else
		{
			lb_erreurClub.setText("");
		}

		return res;
	}
}
