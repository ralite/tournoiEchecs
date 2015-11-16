package modele;



import metier.Joueur;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModeleJoueur {
	 
		private static final ListProperty<Joueur> collectionJoueurs = new SimpleListProperty<>(FXCollections.observableArrayList());
		public static final ListProperty<Joueur> collectionJoueursProperty() {return collectionJoueurs;}
		public static final ObservableList<Joueur> getcollectionJoueurs() {return collectionJoueursProperty().get();}
		public static final void setcollectionJoueurs(final ObservableList<Joueur> collectionJoueurs) {collectionJoueursProperty().set(collectionJoueurs);}
		
		public static void creerJoueur(int num, String prenom, String nom) {
			collectionJoueurs.add(new Joueur(num,prenom,nom));
		}
		
		public static void ajouterJoueur(Joueur j) {
			collectionJoueurs.add(j);
		}
		
		public static Joueur rechercherJoueur(int num) {
			for (Joueur j : collectionJoueurs) {
				if(j.getNumLicence()==num){
					return j;
				}
			}
			return null;
		}
}
