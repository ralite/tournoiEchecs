package modele;



import metier.Joueur;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModeleJoueur {

		private static final ListProperty<Joueur> collectionJoueurs = new SimpleListProperty<>(FXCollections.observableArrayList());
		public static final ListProperty<Joueur> collectionJoueursProperty() {return collectionJoueurs;}
		public static final ObservableList<Joueur> getcollectionJoueurs() {return collectionJoueursProperty().get();}
		public static final void setcollectionJoueurs(final ObservableList<Joueur> collectionJoueurs) {collectionJoueursProperty().set(collectionJoueurs);}

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
}