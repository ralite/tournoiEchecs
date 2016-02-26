package modele.xml;

import java.util.ArrayList;

import metier.Joueur;

public interface I_DALJoueur {

	public void WriteJoueur(ArrayList<Joueur> listJoueur);
	
	public ArrayList<Joueur> readJoueur();
}
