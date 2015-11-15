package modele;


import metier.TestJooueur;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModeleJoueur {
	 
		private static final ListProperty<TestJooueur> collectionJoueurs = new SimpleListProperty<>(FXCollections.observableArrayList());
		public static final ListProperty<TestJooueur> collectionJoueursProperty() {return collectionJoueurs;}
		public static final ObservableList<TestJooueur> getcollectionJoueurs() {return collectionJoueursProperty().get();}
		public static final void setcollectionJoueurs(final ObservableList<TestJooueur> collectionJoueurs) {collectionJoueursProperty().set(collectionJoueurs);}
		
		public static void creerJoueur(int num, String prenom, String nom) {
			collectionJoueurs.add(new TestJooueur(num,prenom,nom));
		}
		
		public static void ajouterJoueur(TestJooueur j) {
			collectionJoueurs.add(j);
		}
		
		public static TestJooueur rechercherJoueur(int num) {
			for (TestJooueur j : collectionJoueurs) {
				if(j.getNum()==num){
					return j;
				}
			}
			return null;
		}
}
