package modele;

import metier.Tournoi;

public class ModeleTournoi {
	
	private static Tournoi tournoi=null;
	private static String fichierTournoi = null;

	public static void ajouterTournoi(Tournoi t) {
		tournoi=t;
	}

	public static void nouveauTournoi(){
		tournoi=null;
		ModeleJoueur.initialiser();
	}

	public static Tournoi getTournoi() {
		return tournoi;
	}

	public static String getFichierTournoi() {
		return ModeleTournoi.fichierTournoi;
	}

	public static void setFichierTournoi(String fichierTournoi) {
		ModeleTournoi.fichierTournoi = fichierTournoi;
	}
}
