package controleur;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	ChoiceBox<String> chbx_titre;
	private ObservableList<String> listeTitre = FXCollections.observableArrayList("Aucun titre","Matre International Masculin", "Maître International Féminin", "Grand Maître International Masculin", "Grand Maître International Feminin", "Maittre FIDE Masculin", "Maitre FIDE Féminin");

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
	ChoiceBox<String> chbx_categorie;
	private ObservableList<String> listeCategorie = FXCollections.observableArrayList("Petit Poussin","Poussin","Pupille","Benjamin", "Minime","Cadet","Junior","Senior","Veteran");

	@FXML
	TextField tf_club;

	@FXML
	TextField tf_federation;

	@FXML
	CheckBox ckbx_national;

	@FXML
	CheckBox ckbx_fide;

	@FXML
	CheckBox ckbx_nouveau;

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
	Label lb_erreurTitre;

	@FXML
	Label lb_erreurLigue;

	@FXML
	Label lb_erreurElo;

	@FXML
	Label lb_erreurCategorie;

	@FXML
	Label lb_erreurClub;

	@FXML
	Label lb_erreurFederation;

	@FXML
	Label lb_erreur;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lb_erreurLicence.setText("");
		lb_erreurNom.setText("");
		lb_erreurPrenom.setText("");
		lb_erreurSexe.setText("");
		lb_erreurDate.setText("");
		lb_erreurTitre.setText("");
		lb_erreurLigue.setText("");
		lb_erreurElo.setText("");
		lb_erreurCategorie.setText("");
		lb_erreurClub.setText("");
		lb_erreurFederation.setText("");
		lb_erreur.setText("");

		chbx_sexe.setItems(listeSexe);
		chbx_titre.setItems(listeTitre);
		chbx_categorie.setItems(listeCategorie);

		tf_numLicence.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(!Validation.estVide(tf_numLicence))
		            {
			    		if(!Validation.verifNumLicence(tf_numLicence))
			    		{
			    			lb_erreurLicence.setText("Le numéro de licence n'est pas au format A99999.");
			    		}else if(ModeleJoueur.rechercherJoueur(tf_numLicence.getText().toString()) != null)
			    		{
			    			lb_erreurLicence.setText("Le numéro de licence existe déjà.");
			    		}else{
			    			lb_erreurLicence.setText("");
			    		}
		            }else{
		            	lb_erreurLicence.setText("Entrez un numéro de licence.");
		            }
		        }
		    }
		});

		tf_nom.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(!Validation.estVide(tf_nom))
		            {
			    		if(!Validation.estChaine(tf_nom))
			    		{
			    			lb_erreurNom.setText("Saisissez un nom valide.");
			    		}else{
			    			lb_erreurNom.setText("");
			    		}
		            }else{
		            	lb_erreurNom.setText("Entrez le nom du joueur.");
		            }
		        }
		    }
		});

		tf_prenom.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(!Validation.estVide(tf_prenom))
		            {
			    		if(!Validation.estChaine(tf_prenom))
			    		{
			    			lb_erreurPrenom.setText("Saisissez un prénom valide.");
			    		}else{
			    			lb_erreurPrenom.setText("");
			    		}
		            }else{
		            	lb_erreurPrenom.setText("Entrez le prénom du joueur.");
		            }
		        }
		    }
		});

		chbx_sexe.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(chbx_sexe.getValue() != null)
		            {
			    		lb_erreurSexe.setText("");
		            }else{
		            	lb_erreurSexe.setText("Sélectionnez le sexe du joueur.");
		            }
		        }
		    }
		});

		dp_dateNaissance.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate d = LocalDate.parse(dp_dateNaissance.getEditor().getText().toString(),formatter);
					dp_dateNaissance.setValue(d);
		            if(!Validation.estVide(dp_dateNaissance))
		            {
		        		if(Validation.estDate(dp_dateNaissance))
		        		{
		        			lb_erreurDate.setText("");
		        			if(!Validation.verifDate(dp_dateNaissance,new DatePicker(LocalDate.now())))
		        			{
		        				lb_erreurDate.setText("Saisissez une date inférieure à la date actuelle.");
		        			}else
		        			{
		        				lb_erreurDate.setText("");
		        			}
		        		}else
		        		{
		        			lb_erreurDate.setText("Saisissez une date au format JJ/MM/AAAA.");
		        		}
		            }else{
		            	lb_erreurDate.setText("Entrez la date de naissance du joueur.");
		            }
		        }
		    }
		});

		chbx_titre.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(chbx_titre.getValue() != null)
		            {
			    		lb_erreurTitre.setText("");
		            }else{
		            	lb_erreurTitre.setText("Sélectionnez le titre du joueur.");
		            }
		        }
		    }
		});

		tf_ligue.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(!Validation.estVide(tf_ligue))
		            {
			    		if(!Validation.estChaine(tf_ligue)||tf_ligue.getText().length()!=3)
						{
							lb_erreurLigue.setText("Saisissez une ligue valide sous la forme 'AAA'");
							tf_ligue.setStyle("-fx-control-inner-background : red; ");
						}else{
							lb_erreurLigue.setText("");
							tf_ligue.setStyle("-fx-control-inner-background : white; ");
						}
		            }else{
		            	lb_erreurLigue.setText("Entrez la ligue du joueur.");
		            }
		        }
		    }
		});

		tf_classementElo.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {

		        }
		        else{
		            if(!Validation.estVide(tf_classementElo) && (!ckbx_national.isSelected() || !ckbx_fide.isSelected() || !ckbx_nouveau.isSelected()))
		            {
		            	if(!Validation.estVide(tf_classementElo))
		            	{
		            		if(!Validation.estEntierPos(tf_classementElo))
			        		{
			        			lb_erreurElo.setText("Saisissez un classement ELO valide.");
			        			tf_classementElo.setStyle("-fx-control-inner-background : red; ");
			        		}else
			        		{
			        			lb_erreurElo.setText("");
			        			tf_classementElo.setStyle("-fx-control-inner-background : white; ");
			        			if(Integer.parseInt(tf_classementElo.getText()) < 500 || Integer.parseInt(tf_classementElo.getText()) > 3000 )
			        			{
			        				lb_erreurElo.setText("Saisissez un classement ELO entre 500 et 3000.");
			        				tf_classementElo.setStyle("-fx-control-inner-background : red; ");
			        			}else
			        			{
			        				lb_erreurElo.setText("");
			        				tf_classementElo.setStyle("-fx-control-inner-background : white; ");
			        			}
			        		}
		            	}else{
		            		lb_erreurElo.setText("Saississez le classement Elo du joueur");
		            	}
		            	if((!ckbx_national.isSelected() || !ckbx_fide.isSelected() || !ckbx_nouveau.isSelected()))
		            	{
		            		lb_erreurElo.setText("Sélectionnez un type d'Elo");
		            	}else{
		            		lb_erreurElo.setText("");
		            	}
		            	
		            }else{
		            	lb_erreurElo.setText("Sélectionnez un classement et un type d'ELO.");
		            }
		        }
		    }
		});

		chbx_categorie.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(chbx_categorie.getValue() != null)
		            {
			    		lb_erreurCategorie.setText("");
		            }else{
		            	lb_erreurCategorie.setText("Selectionnez la catégorie du joueur.");
		            }
		        }
		    }
		});

		tf_club.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(!Validation.estVide(tf_club))
		            {
			    		if(!Validation.estChaine(tf_club))
			    		{
			    			lb_erreurClub.setText("Saisissez un club valide.");
			    		}else{
			    			lb_erreurClub.setText("");
			    		}
		            }else{
		            	lb_erreurClub.setText("Entrez le club du joueur.");
		            }
		        }
		    }
		});

		tf_federation.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		           //System.out.println("Textfield on focus");
		        }
		        else{
		            if(!Validation.estVide(tf_federation))
		            {
			    		if(!Validation.estChaine(tf_federation))
			    		{
			    			lb_erreurFederation.setText("Saisissez une fédération valide.");
			    		}else{
			    			lb_erreurFederation.setText("");
			    		}
		            }else{
		            	lb_erreurFederation.setText("Entrez la fédération du joueur.");
		            }
		        }
		    }
		});
	}

	/*@FXML
	public void onCheck_National(Event e)
	{
		if(ckbx_national.isSelected())
			ckbx_national.setSelected(false);
		else{
			ckbx_etranger.setSelected(false);
			ckbx_nouveau.setSelected(false);
			ckbx_national.setSelected(true);
		}
	}*/

	@FXML
	public void onClick_Quitter(Event e)
	{
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void onClick_Creer(Event e)
	{
		/*String numLicence = tf_numLicence.getText().substring(0,1).toUpperCase().concat(tf_numLicence.getText().substring(1,6)) ;
		lb_erreurLicence.setText(numLicence);

		String nom = tf_nom.getText().toUpperCase();
		lb_erreurNom.setText(nom);

		String prenom = tf_prenom.getText().substring(0,1).toUpperCase().concat(tf_prenom.getText().substring(1).toLowerCase()) ;
		lb_erreurPrenom.setText(prenom);

		String sexe = chbx_sexe.getValue();

		LocalDate dateNaissance = dp_dateNaissance.getValue();

		String ligue = tf_ligue.getText().toUpperCase();
		lb_erreurLigue.setText(ligue);

		String titre = chbx_titre.getValue();

		int elo = Integer.parseInt(tf_classementElo.getText());

		String typeElo;
		if(ckbx_national.isSelected())
			typeElo="N";
		if(ckbx_fide.isSelected())
			typeElo="F";
		if(ckbx_nouveau.isSelected())
			typeElo="E";

		String categorie = chbx_categorie.getValue();
		String club = tf_club.getText().substring(0,1).toUpperCase().concat(tf_club.getText().substring(1).toLowerCase());
		String federation = tf_federation.getText().substring(0,1).toUpperCase().concat(tf_federation.getText().substring(1).toLowerCase());
		*/
		if(formulaireRempli())
		{
			if(infosCorrectes())
			{
				lb_erreur.setText("ok");
				//ModeleJoueur.creerJoueur(tf_numLicence.getText().toString(),tf_prenom.getText().toString(),chbx_sexe.getValue().toString(),
				//dp_dateNaissance.getEditor().getText().toString(),tf_titre.getText().toString(),tf_ligue.getText().toString(),
				//Integer.parseInt(tf_classementElo.getText().toString()),tf_categorie.getText().toString(),tf_club.getTet().toString());
			}//infosCorrectes
		}//formulaireRempli
	}

	private boolean formulaireRempli()
	{//plusieurs if pour avoir plusieurs champs en rouge et pas seulement le premier testé


		//test raloute date

		boolean res = true;

		if(Validation.estVide(tf_numLicence))
		{
			lb_erreurLicence.setText("Entrez le numéro de licence du joueur.");
			res = false;
		}else
			lb_erreurLicence.setText("");

		if(Validation.estVide(tf_nom))
		{
			lb_erreurNom.setText("Entrez le nom du joueur.");
			res = false;
		}else
			lb_erreurNom.setText("");

		if(Validation.estVide(tf_prenom))
		{
			lb_erreurPrenom.setText("Entrez le prénom du joueur.");
			res = false;
		}else
			lb_erreurPrenom.setText("");

		if(chbx_sexe.getValue() == null)
		{
			lb_erreurSexe.setText("Selectionnez le sexe du joueur.");
			res = false;
		}else
			lb_erreurSexe.setText("");

		if(Validation.estVide(dp_dateNaissance))
		{
			lb_erreurDate.setText("Entrez la date de naissance du joueur.");
			res = false;
		}else
			lb_erreurDate.setText("");

		if(chbx_titre.getValue() == null)
		{
			lb_erreurTitre.setText("Sélectionnez le titre du joueur.");
			res = false;
		}else
			lb_erreurTitre.setText("");

		if(Validation.estVide(tf_classementElo) && (!ckbx_national.isSelected() && !ckbx_fide.isSelected() && !ckbx_nouveau.isSelected() ))
		{
			lb_erreurElo.setText("Sélectionnez un classement et un type d'ELO.");
			res = false;
		}else{
			lb_erreurElo.setText("");
			if(Validation.estVide(tf_classementElo))
			{
				lb_erreurElo.setText("Entrez le classement ELO du joueur.");
				res = false;
			}else if(!ckbx_national.isSelected() && !ckbx_fide.isSelected() && !ckbx_nouveau.isSelected() )
			{
				lb_erreurElo.setText("Sélectionnez un type d'ELO.");
				res = false;
			}
		}

		if(Validation.estVide(tf_ligue))
		{
			lb_erreurLigue.setText("Entrez la ligue du joueur.");
			res = false;
		}else
			lb_erreurLigue.setText("");

		if(chbx_categorie.getValue() == null)
		{
			lb_erreurCategorie.setText("Selectionnez la catégorie du joueur.");
			res = false;
		}else
			lb_erreurCategorie.setText("");

		if(Validation.estVide(tf_club))
		{
			lb_erreurClub.setText("Entrez le club du joueur.");
			res = false;
		}else
			lb_erreurClub.setText("");

		if(Validation.estVide(tf_federation))
		{
			lb_erreurFederation.setText("Entrez la fédération du joueur.");
			res = false;
		}else
			lb_erreurFederation.setText("");

		return res;
	}

	private boolean infosCorrectes()
	{
		boolean res = true;

		//numLicence
		if(!Validation.verifNumLicence(tf_numLicence))
		{
			lb_erreurLicence.setText("Le numéro de licence n'est pas au format A99999.");
			res = false;
		}else if(ModeleJoueur.rechercherJoueur(tf_numLicence.getText().toString()) != null)
		{
			lb_erreurLicence.setText("Le numéro de licence existe déjà.");
			res = false;
		}else{
			lb_erreurLicence.setText("");
		}

		//nom
		if(!Validation.estNomCompose(tf_nom))
		{
			lb_erreurNom.setText("Saisissez un nom valide.");
			res = false;
		}else
		{
			lb_erreurNom.setText("");
		}

		//prenom
		if(!Validation.estChaine(tf_prenom))
		{
			lb_erreurPrenom.setText("Saisissez un prénom valide.");
			res = false;
		}else
		{
			lb_erreurPrenom.setText("");
		}

		//date
		if(Validation.estDate(dp_dateNaissance))
		{
			lb_erreurDate.setText("");
			if(!Validation.verifDate(dp_dateNaissance,new DatePicker(LocalDate.now())))
			{
				lb_erreurDate.setText("Saisissez une date inférieure à la date actuelle.");
				res = false;
			}else
			{
				lb_erreurDate.setText("");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate d = LocalDate.parse(dp_dateNaissance.getEditor().getText().toString(),formatter);
				dp_dateNaissance.setValue(d);
			}
		}else
		{
			lb_erreurDate.setText("Saisissez une date au format JJ/MM/AAAA.");
		}


		//ligue
		if(!Validation.estChaine(tf_ligue)||tf_ligue.getText().length()!=3)
		{
			lb_erreurLigue.setText("Saisissez une ligue valide sous la forme 'AAA'");
			tf_ligue.setStyle("-fx-control-inner-background : red; ");
		}else{
			lb_erreurLigue.setText("");
			tf_ligue.setStyle("-fx-control-inner-background : white; ");
		}

		//classement elo
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

		//club
		if(!Validation.estChaine(tf_club))
		{
			lb_erreurClub.setText("Saisissez un nom de club valide.");
			res = false;
		}else
		{
			lb_erreurClub.setText("");
		}


		//federation
		if(!Validation.estChaine(tf_federation))
		{
			lb_erreurFederation.setText("Saisissez un nom de fédération valide.");
			res = false;
		}else
		{
			lb_erreurFederation.setText("");
		}

		return res;
	}
}