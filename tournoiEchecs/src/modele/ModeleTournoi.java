package modele;

import metier.Tournoi;

public class ModeleTournoi {
	private static Tournoi tournoi=null;
	
	public static void ajouterTournoi(Tournoi t) {
		tournoi=t;
	}
	
	public static Tournoi getTournoi() {
		return tournoi;
	}
	
}
