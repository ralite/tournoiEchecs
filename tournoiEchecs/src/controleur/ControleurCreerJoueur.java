package controleur;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import metier.Joueur;
import modele.ModeleJoueur;
import modele.Validation;
import modele.xml.JoueurXML;

public class ControleurCreerJoueur implements Initializable {

	@FXML
	TextField tf_numLicence;

	@FXML
	TextField tf_nom;

	@FXML
	TextField tf_prenom;

	@FXML
	DatePicker dp_dateNaissance;

	@FXML
	ChoiceBox<String> chbx_sexe;
	private ObservableList<String> listeSexe = FXCollections.observableArrayList("Homme","Femme");

	@FXML
	TextField tf_ligue;

	@FXML
	TextField tf_classementElo;

	@FXML
	RadioButton rb_national;

	@FXML
	RadioButton rb_fide;

	@FXML
	RadioButton rb_nouveau;

	@FXML
	ChoiceBox<String> chbx_titre;
	private ObservableList<String> listeTitre;

	@FXML
	TextField tf_club;

	@FXML
	TextField tf_federation;

	@FXML
	Label lb_categorie;

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
	Label lb_erreurType;

	@FXML
	Label lb_erreurCategorie;

	@FXML
	Label lb_erreurClub;

	@FXML
	Label lb_erreurFederation;

	@FXML
	Label lb_erreur;

	HashMap<String, Integer> mapEloInitial = new HashMap<String, Integer>();

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		clearFormulaire();

		if(ModeleJoueur.getJoueurAmodifier()!=null){
			chargerFormulaire();
		}

		tf_numLicence.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		    		if(Validation.estVide(tf_numLicence))
		    		{
		    			lb_erreurLicence.setText("Entrez le numéro de licence du joueur");
		    		}else
		    		{
		    			lb_erreurLicence.setText("");
		    			if(!Validation.verifNumLicence(tf_numLicence))
		    			{
		    				lb_erreurLicence.setText("Le numéro de licence n'est pas au format A99999");
		    			}else if(ModeleJoueur.rechercherJoueur(tf_numLicence.getText().toString()) != null)
		    			{
	    					lb_erreurLicence.setText("Le numéro de licence existe déjà");
		    			}else{
		    				lb_erreurLicence.setText("");
		    			}
		    		}
		        }
		    }
		});

		tf_nom.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		        	if(!Validation.estVide(tf_nom))
		            {
			    		if(!Validation.estNomCompose(tf_nom))
			    		{
			    			lb_erreurNom.setText("Saisissez un nom valide");
			    		}else{
			    			lb_erreurNom.setText("");
			    		}
		            }else{
		            	lb_erreurNom.setText("Entrez le nom du joueur");
		            }
		        }
		    }
		});

		tf_prenom.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		        	if(!Validation.estVide(tf_prenom))
		            {
			    		if(!Validation.estNomCompose(tf_prenom))
			    		{
			    			lb_erreurPrenom.setText("Saisissez un prénom valide");
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
		        if (oldPropertyValue)
		        {
		        	if(chbx_sexe.getValue() == "Homme")
		        	{
		        		listeTitre = FXCollections.observableArrayList("Aucun titre","Candidat Maître Masculin","Maître FIDE Masculin",
		        				"Maître International Masculin", "Grand Maître International Masculin");
		        	}else{
		        		listeTitre = FXCollections.observableArrayList("Aucun titre","Candidat Maître Féminin",
		        				"Candidat Maître Masculin","Maître FIDE Féminin","Maître International Féminin",
		        				"Maître FIDE Masculin","Grand Maître International Feminin","Maître International Masculin",
		        				"Grand Maître International Masculin");
		        	}
	        		chbx_titre.setItems(listeTitre);
	        		chbx_titre.setValue("Aucun titre");
		        }
		    }
		});

		dp_dateNaissance.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		        	if(Validation.recupValeursDate(dp_dateNaissance))
		        	{
		        		dp_dateNaissance.setStyle("-fx-control-inner-background : white; ");
		        		if(Validation.estDate(dp_dateNaissance))
		        		{
		        			lb_erreurDate.setText("");
			        		dp_dateNaissance.setStyle("-fx-control-inner-background : white; ");
		        			if(Validation.verifDate(dp_dateNaissance,new DatePicker(LocalDate.now())))
		        			{
				        		dp_dateNaissance.setStyle("-fx-control-inner-background : white; ");
		        				lb_erreurDate.setText("");
		        				lb_erreurCategorie.setText("");
		        				lb_categorie.setText(Joueur.getCategorieCalculee(dp_dateNaissance.getValue()));

		        				if(rb_nouveau.isSelected())
		        				{
		        					tf_classementElo.setText(String.valueOf(mapEloInitial.get(lb_categorie.getText())));
		        					lb_erreurElo.setText("");
		        				}else
		        					tf_classementElo.setText("");
		        			}else
		        			{
		        				lb_erreurDate.setText("Saisissez une date inférieure à la date actuelle");
				        		dp_dateNaissance.setStyle("-fx-control-inner-background : red; ");
		        			}
		        		}else
		        		{
		        			lb_erreurDate.setText("Saisissez une date au format JJ/MM/AAAA.");
			        		dp_dateNaissance.setStyle("-fx-control-inner-background : red; ");
		        		}
		        	}else
		        	{
		        		lb_erreurDate.setText("Entrez une date de naissance valide.");
		        		dp_dateNaissance.setStyle("-fx-control-inner-background : red; ");
		        		lb_erreurCategorie.setText("Entrez une date de naissance pour obtenir la catégorie du joueur");
		        		lb_categorie.setText("Non assignée");
		        	}
		        }
		    }
		});

		tf_classementElo.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		            if(!Validation.estVide(tf_classementElo))
		            {
	            		if(Validation.estEntierPos(tf_classementElo))
		        		{
		        			if(Integer.parseInt(tf_classementElo.getText()) > 499 && Integer.parseInt(tf_classementElo.getText()) < 3001 )
		        			{
		        				lb_erreurElo.setText("");
		        				tf_classementElo.setStyle("-fx-control-inner-background : white; ");
		        			}else
		        			{
		        				lb_erreurElo.setText("Saisissez un classement ELO entre 500 et 3000");
		        				tf_classementElo.setStyle("-fx-control-inner-background : red; ");

		        			}
		        		}else
		        		{
		        			lb_erreurElo.setText("Saisissez un classement ELO valide");
		        		}
		            }else{
		            	lb_erreurElo.setText("Saississez le classement ELO du joueur");
		            }

		            if((!rb_national.isSelected() && !rb_fide.isSelected() && !rb_nouveau.isSelected()))
	            	{
	            		lb_erreurType.setText("Sélectionnez un type d'ELO");
	            	}else{
	            		lb_erreurType.setText("");
	            	}
		        }
		    }
		});

		rb_national.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if(isNowSelected)
		        {
		        	lb_erreurType.setText("");
		            rb_fide.setSelected(wasPreviouslySelected);
		            rb_nouveau.setSelected(wasPreviouslySelected);
		            tf_classementElo.setDisable(false);//retour elo intial

		            //retour de elo initial sans date de naissance
		            if(tf_classementElo.getText().equalsIgnoreCase("Non assigné"))
		            {
		            	tf_classementElo.setText("");
		            	lb_erreurElo.setText("");
		            }
		        }
		    }
		});

		rb_fide.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if(isNowSelected)
		        {
		        	lb_erreurType.setText("");
		            rb_national.setSelected(wasPreviouslySelected);
		            rb_nouveau.setSelected(wasPreviouslySelected);
		            tf_classementElo.setDisable(false);//retour elo initial

		            //retour de elo initial sans date de naissance
		            if(tf_classementElo.getText().equalsIgnoreCase("Non assigné"))
		            {
		            	lb_erreurElo.setText("");
		            	tf_classementElo.setText("");
		            }
		        }
		      }
		});

		rb_nouveau.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if(isNowSelected) {
		            rb_national.setSelected(wasPreviouslySelected);
		            rb_fide.setSelected(wasPreviouslySelected);
		            tf_classementElo.setDisable(true);

		            tf_classementElo.setStyle("-fx-control-inner-background : white; ");//si ancien mauvais elo saisi

		            tf_classementElo.setText(String.valueOf(mapEloInitial.get(lb_categorie.getText())));//affichage elo initial

 					//si date de naissance non-saisie
 					if(Integer.parseInt(tf_classementElo.getText()) == -1)
 					{
 						lb_erreurElo.setText("Saisissez une date de naissance pour un ELO initial");
 						tf_classementElo.setText("Non assigné");
 					}
		        }
		    }
		});

		tf_federation.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		        	//validation fédération
		        	if(!Validation.estVide(tf_federation))
		            {
			    		if(!Validation.estChaine(tf_federation))
			    		{
			    			lb_erreurFederation.setText("Saisissez une fédération valide");
			    		}else{
			    			lb_erreurFederation.setText("");
			    		}
		            }else{
		            	lb_erreurFederation.setText("Entrez la fédération du joueur");
		            }

		        	//grisage de la ligue en cas de non-fr
		            if(tf_federation.getText().equalsIgnoreCase("Francaise") || tf_federation.getText().equalsIgnoreCase("Francais")
	            		|| tf_federation.getText().equalsIgnoreCase("Française")|| tf_federation.getText().equalsIgnoreCase("Français")
	            		|| tf_federation.getText().equalsIgnoreCase("Fr") || tf_federation.getText().equalsIgnoreCase("Fra")
	            		|| tf_federation.getText().equalsIgnoreCase("France"))
		        	{
		            	//si ligue vide, message erreur
		        		tf_ligue.setDisable(false);
		        	}else{
		        		tf_ligue.setDisable(true);
		        		tf_ligue.clear();
		        		tf_ligue.setStyle("-fx-control-inner-background : white; ");
		        		lb_erreurLigue.setText("");
		        	}

		            //passage sans saisie
		            if(tf_federation.getText().isEmpty())
		            {
		            	tf_ligue.setDisable(false);
		        		tf_ligue.setStyle("-fx-control-inner-background : white; ");
		        		lb_erreurLigue.setText("");
		            }
		        }
		    }
		});

		tf_ligue.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		        	if(tf_federation.getText().equalsIgnoreCase("Francaise") || tf_federation.getText().equalsIgnoreCase("Francais")
		            		|| tf_federation.getText().equalsIgnoreCase("Française")|| tf_federation.getText().equalsIgnoreCase("Français")
		            		|| tf_federation.getText().equalsIgnoreCase("Fr") || tf_federation.getText().equalsIgnoreCase("Fra")
		            		|| tf_federation.getText().equalsIgnoreCase("France") || tf_federation.getText().isEmpty())
		        	{
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
			            	lb_erreurLigue.setText("Entrez la ligue du joueur");
			            }
		        	}
		        }
		    }
		});

		tf_club.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (oldPropertyValue)
		        {
		        	if(!Validation.estVide(tf_club))
		            {
			    		/*if(!Validation.estChaineChiffree(tf_club))
			    		{
			    			lb_erreurClub.setText("Saisissez un club valide");
			    		}else{
			    			lb_erreurClub.setText("");
			    		}*/
		        		lb_erreurClub.setText("");
		            }else{
		            	lb_erreurClub.setText("Entrez le club du joueur");
		            }
		        }
		    }
		});

	}

	@FXML
	public void onClick_Quitter(Event e)
	{
		ModeleJoueur.setJoueurAmofifier(null);
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void onClick_Creer(Event e)
	{
		if(formulaireCorrect())
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Succès");
			alert.setHeaderText(null);

			String numLicence = tf_numLicence.getText().substring(0,1).toUpperCase().concat(tf_numLicence.getText().substring(1,6)) ;

			String nom = tf_nom.getText().toUpperCase();

			String prenom = tf_prenom.getText().substring(0,1).toUpperCase().concat(tf_prenom.getText().substring(1).toLowerCase()) ;

			String sexe = chbx_sexe.getValue();

			LocalDate dateNaissance = dp_dateNaissance.getValue();

			String categorie = lb_categorie.getText();

			String typeElo="";
			if(rb_national.isSelected())
				typeElo="National";
			else if(rb_fide.isSelected())
				typeElo="FIDE";
			else if(rb_nouveau.isSelected())
				typeElo="Nouveau";

			String federation = tf_federation.getText().substring(0,1).toUpperCase().concat(tf_federation.getText().substring(1).toLowerCase());

			String ligue = tf_ligue.getText().toUpperCase();

			int elo ;
			try{
				elo=Integer.parseInt(tf_classementElo.getText());
			}catch(Exception ex){
				elo=-1;
			}

			String club = tf_club.getText().substring(0,1).toUpperCase().concat(tf_club.getText().substring(1).toLowerCase());

			String titre = chbx_titre.getValue();

			if(ModeleJoueur.getJoueurAmodifier()==null){
				ModeleJoueur.creerJoueur(numLicence, nom, prenom, sexe, dateNaissance, titre, ligue, elo, typeElo, federation, categorie, club);
				alert.setContentText("Joueur créé avec succès !");
				alert.showAndWait();
			}else {
				ModeleJoueur.modifierJoueur(numLicence, nom, prenom, sexe, dateNaissance, titre, ligue, elo, typeElo, federation, categorie, club);
				ModeleJoueur.setJoueurAmofifier(null);
				alert.setContentText("Joueur modifié avec succès !");
				alert.showAndWait();
				((Node)e.getSource()).getScene().getWindow().hide();
			}

			JoueurXML.WriteXMLJoueur(JoueurXML.joueurFilePath, ModeleJoueur.getArrayJoueurs());
			clearFormulaire();
		}//formulaireCorrect
	}

	private void chargerFormulaire() {
		tf_numLicence.setDisable(true);
		tf_numLicence.setText(ModeleJoueur.getJoueurAmodifier().getNumLicence());
		tf_nom.setText(ModeleJoueur.getJoueurAmodifier().getNomJoueur());
		tf_prenom.setText(ModeleJoueur.getJoueurAmodifier().getPrenomJoueur());
		chbx_sexe.setValue(ModeleJoueur.getJoueurAmodifier().getSexe());
		dp_dateNaissance.setValue(ModeleJoueur.getJoueurAmodifier().getDateNaissance());
		lb_categorie.setText(ModeleJoueur.getJoueurAmodifier().getCategorie());
		tf_ligue.setText(ModeleJoueur.getJoueurAmodifier().getLigue());
		tf_classementElo.setText(String.valueOf(ModeleJoueur.getJoueurAmodifier().getElo()));
		tf_federation.setText(ModeleJoueur.getJoueurAmodifier().getFederation());
		tf_club.setText(ModeleJoueur.getJoueurAmodifier().getClub());
		chbx_titre.setValue(ModeleJoueur.getJoueurAmodifier().getTitre());
		String typeElo=ModeleJoueur.getJoueurAmodifier().getTypeElo();
		if(typeElo.equalsIgnoreCase("National"))
			rb_national.setSelected(true);
		else if(typeElo.equalsIgnoreCase("FIDE"))
			rb_fide.setSelected(true);
		else if(typeElo.equalsIgnoreCase("Nouveau")){
			rb_nouveau.setSelected(true);
			tf_classementElo.setDisable(true);
		}

	}

	private void clearFormulaire()
	{
		lb_erreurLicence.setText("");
		lb_erreurNom.setText("");
		lb_erreurPrenom.setText("");
		lb_erreurSexe.setText("");
		lb_erreurDate.setText("");
		lb_erreurCategorie.setText("");
		lb_erreurTitre.setText("");
		lb_erreurLigue.setText("");
		lb_erreurElo.setText("");
		lb_erreurType.setText("");
		lb_erreurClub.setText("");
		lb_erreurFederation.setText("");
		lb_erreur.setText("");
		lb_categorie.setText("Non assignée");

		chbx_sexe.setItems(listeSexe);
		chbx_sexe.setValue("Homme");
		listeTitre = FXCollections.observableArrayList("Aucun titre","Candidat Maître Masculin","Maître FIDE Masculin",
				"Maître International Masculin", "Grand Maître International Masculin");
		chbx_titre.setItems(listeTitre);
		chbx_titre.setValue("Aucun titre");

		tf_numLicence.clear();
		tf_nom.clear();
		tf_prenom.clear();
		dp_dateNaissance.setValue(null);
		tf_ligue.clear();
		tf_classementElo.clear();
		tf_federation.clear();
		tf_club.clear();

		rb_national.setSelected(false);
		rb_fide.setSelected(false);
		rb_nouveau.setSelected(false);


		int eloInitial = 1499;
		mapEloInitial.put("Vétéran", eloInitial);
		mapEloInitial.put("Sénior", eloInitial);
		mapEloInitial.put("Junior", eloInitial-100);
		mapEloInitial.put("Cadet", eloInitial-200);
		mapEloInitial.put("Minime", eloInitial-300);
		mapEloInitial.put("Benjamin", eloInitial-400);
		mapEloInitial.put("Pupille", eloInitial-500);
		mapEloInitial.put("Poussin", eloInitial-600);
		mapEloInitial.put("Petit Poussin", eloInitial-700);
		mapEloInitial.put("Non assignée", -1);
	}

	private boolean formulaireCorrect()
	{//plusieurs if pour avoir plusieurs champs en rouge et pas seulement le premier testé

		boolean res = true;

		//numLicence
		if(Validation.estVide(tf_numLicence))
		{
			lb_erreurLicence.setText("Entrez le numéro de licence du joueur");
			res = false;
		}else
		{
			if(!Validation.verifNumLicence(tf_numLicence))
			{
				lb_erreurLicence.setText("Le numéro de licence n'est pas au format A99999");
				res = false;
			}else if(ModeleJoueur.getJoueurAmodifier()==null && ModeleJoueur.rechercherJoueur(tf_numLicence.getText().toString()) != null)
			{
				lb_erreurLicence.setText("Le numéro de licence existe déjà");
				res = false;
			}else{
				lb_erreurLicence.setText("");
			}
		}

		//nom
		if(Validation.estVide(tf_nom))
		{
			lb_erreurNom.setText("Entrez le nom du joueur");
			res = false;
		}else
		{
			if(!Validation.estNomCompose(tf_nom))
			{
				lb_erreurNom.setText("Saisissez un nom valide");
				res = false;
			}else
			{
				lb_erreurNom.setText("");
			}
		}

		//prenom
		if(Validation.estVide(tf_prenom))
		{
			lb_erreurPrenom.setText("Entrez le prénom du joueur");
			res = false;
		}else
		{
			if(!Validation.estNomCompose(tf_prenom))
			{
				lb_erreurPrenom.setText("Saisissez un prénom valide");
				res = false;
			}else
			{
				lb_erreurPrenom.setText("");
			}
		}

		//date
		if(Validation.recupValeursDate(dp_dateNaissance))
		{
			dp_dateNaissance.setStyle("-fx-control-inner-background : white; ");

			if(Validation.verifDate(dp_dateNaissance,new DatePicker(LocalDate.now())))
			{
				lb_erreurCategorie.setText("");
				lb_erreurDate.setText("");
				lb_categorie.setText(Joueur.getCategorieCalculee(dp_dateNaissance.getValue()));

				if(rb_nouveau.isSelected())
				{
 					tf_classementElo.setText(String.valueOf(mapEloInitial.get(lb_categorie.getText())));
				}
			}else
			{
				lb_erreurDate.setText("Saisissez une date inférieure à la date actuelle");
				res = false;
			}
		}else
		{
			dp_dateNaissance.setStyle("-fx-control-inner-background : red; ");
			lb_erreurDate.setText("Entrez une date de naissance valide");
			lb_erreurCategorie.setText("Entrez une date de naissance pour obtenir la catégorie du joueur");
			lb_categorie.setText("Non assignée");
			res=false;
		}

		//fédération
		if(Validation.estVide(tf_federation))
		{
			lb_erreurFederation.setText("Entrez la fédération du joueur");
			res = false;
		}else
		{
			if(!Validation.estChaine(tf_federation))
			{
				lb_erreurFederation.setText("Saisissez un nom de fédération valide");
				res = false;
			}else
			{
				lb_erreurFederation.setText("");
			}
		}

		//ligue
		if(tf_federation.getText().equalsIgnoreCase("Francaise") || tf_federation.getText().equalsIgnoreCase("Francais")
				|| tf_federation.getText().equalsIgnoreCase("Française") || tf_federation.getText().equalsIgnoreCase("Français")
				|| tf_federation.getText().equalsIgnoreCase("Fr") || tf_federation.getText().equalsIgnoreCase("Fra")
				|| tf_federation.getText().equalsIgnoreCase("France"))
		{
			if(Validation.estVide(tf_ligue))
			{
				lb_erreurLigue.setText("Entrez la ligue du joueur");
				res = false;
			}else
			{
				if(!Validation.estChaine(tf_ligue) || tf_ligue.getText().length()!=3)
				{
					lb_erreurLigue.setText("Saisissez une ligue valide sous la forme 'AAA'");
					tf_ligue.setStyle("-fx-control-inner-background : red; ");
					res =false;
				}else{
					lb_erreurLigue.setText("");
					tf_ligue.setStyle("-fx-control-inner-background : white; ");
				}
			}
		}

		//classementElo
		if(!rb_nouveau.isSelected())
		{
			if(Validation.estVide(tf_classementElo))
			{
				lb_erreurElo.setText("Sélectionnez le classement ELO du joueur.");
				res = false;
			}else{
				if(!Validation.estEntierPos(tf_classementElo))
				{
					lb_erreurElo.setText("Saisissez un classement ELO valide");
					res = false;
				}else
				{
					if(Integer.parseInt(tf_classementElo.getText()) > 499 && Integer.parseInt(tf_classementElo.getText()) < 3001)
					{
						lb_erreurElo.setText("");
						tf_classementElo.setStyle("-fx-control-inner-background : white; ");
					}else
					{
						lb_erreurElo.setText("Saisissez un classement ELO entre 500 et 3000");
						tf_classementElo.setStyle("-fx-control-inner-background : red; ");
						res = false;
					}
				}
			}
		}

		//typeElo
		if((!rb_national.isSelected() && !rb_fide.isSelected() && !rb_nouveau.isSelected()))
		{
			lb_erreurType.setText("Sélectionnez un type d'ELO");
			res = false;
		}else{
			lb_erreurType.setText("");
		}

		//club
		if(Validation.estVide(tf_club))
		{
			lb_erreurClub.setText("Entrez le club du joueur");
			res = false;
		}else
		{
			/*if(!Validation.estChaineChiffree(tf_club))
			{
				lb_erreurClub.setText("Saisissez un nom de club valide");
				res = false;
			}else
			{
				lb_erreurClub.setText("");
			}*/
			lb_erreurClub.setText("");
		}

		return res;
	}

	@FXML
	public void limiteTexte(){//déclenché onKeyReleased des tf
		Validation.verifLongueurTexte(tf_nom,30);
		Validation.verifLongueurTexte(tf_prenom,30);
		Validation.verifLongueurTexte(tf_federation,30);
		Validation.verifLongueurTexte(tf_classementElo,4);
		Validation.verifLongueurTexte(tf_ligue,3);
		Validation.verifLongueurTexte(tf_club, 30);
	}

}