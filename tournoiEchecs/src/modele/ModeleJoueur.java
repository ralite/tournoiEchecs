package modele;



import metier.Joueur;

import java.time.LocalDate;
import java.time.temporal.JulianFields;
import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;


public class ModeleJoueur {

		private static final ListProperty<Joueur> collectionJoueurs = new SimpleListProperty<>(FXCollections.observableArrayList());
		public static final ListProperty<Joueur> collectionJoueursProperty() {return collectionJoueurs;}
		public static final ObservableList<Joueur> getcollectionJoueurs() {return collectionJoueursProperty().get();}
		public static final void setcollectionJoueurs(final ObservableList<Joueur> collectionJoueurs) {collectionJoueursProperty().set(collectionJoueurs);}

		private static Joueur joueurAmodifier=null;
		
		public static void setJoueurAmofifier(Joueur j){
			joueurAmodifier=j;
		}
		
		public static Joueur getJoueurAmodifier(){
			return joueurAmodifier;
		}
		
		public static void creerJoueur(String numLicence, String nomJoueur, String prenomJoueur, String sexe, LocalDate dateNaissance, String titre, String ligue, int elo, String categorie, String club ) {
			collectionJoueurs.add(new Joueur(numLicence,nomJoueur,prenomJoueur,sexe,dateNaissance,titre, ligue,elo,categorie,club));
		}

		public static void ajouterJoueur(Joueur j) {
			collectionJoueurs.add(j);
		}

		public static Joueur rechercherJoueur(String num) {
			for (Joueur j : collectionJoueurs) {
				if(j.getNumLicence().equals(num)){
					return j;
				}
			}
			return null;
		}
		public static ArrayList<Joueur> rechercherNomJoueur(String nom) {
			ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
			for (Joueur j : collectionJoueurs) {
				if(j.getNomJoueur().equals(nom)){
					joueurs.add(j);
				}
			}
			return joueurs;
		}
		
		

}