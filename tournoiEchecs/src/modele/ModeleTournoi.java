package modele;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import metier.Joueur;
import metier.Tournoi;

public class ModeleTournoi {
	private static Tournoi tournoi=null;
	
	public static void ajouterTournoi(Tournoi t) {
		tournoi=t;
	}
	
	public static void nouveauTournoi(){
		tournoi=null;
	}
	
	public static void ajouterJoueurs(ObservableList<Joueur> joueurs){
		tournoi.setListeJoueurs(joueurs);
	}
	
	public static Tournoi getTournoi() {
		return tournoi;
	}

	public static ObservableList<Joueur> getJoueurs() {
		return tournoi.getListeJoueurs();
	}
	
}
