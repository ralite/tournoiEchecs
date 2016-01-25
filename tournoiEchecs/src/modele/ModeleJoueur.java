package modele;

import metier.Joueur;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class ModeleJoueur {

		private static final ListProperty<Joueur> collectionJoueurs = new SimpleListProperty<>(FXCollections.observableArrayList());

		private static Joueur joueurAmodifier=null;

		public static void setJoueurAmofifier(Joueur j){
			joueurAmodifier=j;
		}

		public static Joueur getJoueurAmodifier(){
			return joueurAmodifier;
		}

		public static void creerJoueur(String numLicence, String nomJoueur, String prenomJoueur, String sexe, LocalDate dateNaissance, String titre, String ligue, int elo, String typeElo, String federation, String categorie, String club ) {
			collectionJoueurs.add(new Joueur(numLicence,nomJoueur,prenomJoueur,sexe,dateNaissance,titre, ligue, elo, typeElo, federation, categorie,club));
		}

		public static void ajouterJoueur(Joueur j) {
			collectionJoueurs.add(j);
		}

		public static Joueur rechercherJoueur(String num) {
			for (Joueur j : collectionJoueurs) {
				if(j.getNumLicence().equalsIgnoreCase(num)){
					return j;
				}
			}
			return null;
		}
		
		public static ArrayList<Joueur> rechercherNomJoueur(String nom) {
			ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
			for (Joueur j : collectionJoueurs) {
				if(j.getNomJoueur().equalsIgnoreCase(nom)){
					joueurs.add(j);
				}
			}
			return joueurs;
		}

		public static final ArrayList<Joueur> getArrayJoueurs() {
			ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
			for (Joueur joueur : collectionJoueurs) {
				joueurs.add(joueur);
			}
			return joueurs;
		}
		public static void modifierJoueur(String numLicence, String nom,
				String prenom, String sexe, LocalDate dateNaissance,
				String titre, String ligue, int elo, String typeElo,
				String federation, String categorie, String club) {

			joueurAmodifier.setNumLicence(numLicence);
			joueurAmodifier.setNomJoueur(nom);
			joueurAmodifier.setPrenomJoueur(prenom);
			joueurAmodifier.setSexe(sexe);
			joueurAmodifier.setDateNaissance(dateNaissance);
			joueurAmodifier.setTitreFide(titre);
			joueurAmodifier.setLigue(ligue);
			joueurAmodifier.setElo(elo);
			joueurAmodifier.setTypeElo(typeElo);
			joueurAmodifier.setFederation(federation);
			joueurAmodifier.setCategorie(categorie);
			joueurAmodifier.setClub(club);
		}
		public static void supprimerJoueur(Joueur joueur) {
			collectionJoueurs.remove(joueur);

		}

		public static void initialiser() {
			for (Joueur joueur : collectionJoueurs) {
				joueur.initialiser();
			}
			
		}




}
