package metier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ronde {

	private int numeroRonde;
	private ObservableList<Partie> ListePartie;
	private ObservableList<Joueur> ListeJoueurAbs;
	private ObservableList<Joueur> ListeJoueurForfait;
	
	public Ronde(int numeroRonde) {
		this.numeroRonde = numeroRonde;
	}
	
	public int getNumeroRonde() {
		return numeroRonde;
	}
	
	public void setNumeroRonde(int numeroRonde) {
		this.numeroRonde = numeroRonde;
	}
	
	public ObservableList<Partie> getListePartie() {
		return ListePartie;
	}
	
	public void setListePartie(ObservableList<Partie> listePartie) {
		ListePartie = listePartie;
	}

	public ObservableList<Joueur> getListeJoueurAbs() {
		return ListeJoueurAbs;
	}

	public void setListeJoueurAbs(ObservableList<Joueur> listeJoueurAbs) {
		ListeJoueurAbs = listeJoueurAbs;
	}

	public void setListeJoueur(ObservableList<Joueur> joueursForfait) {
		ListeJoueurForfait = joueursForfait;
	}

	public boolean dejaRencontre(Joueur j1, Joueur j2) {
		boolean dejaRencontre=false;
		int i = 0,size=0;
		if(ListePartie!=null){
			size=ListePartie.size();
		}
		while(!dejaRencontre && i<size){
			dejaRencontre=ListePartie.get(i).dejaRencontre(j1,j2);
			i++;
		}
		return dejaRencontre;
	}

	public ObservableList<Partie> getParties() {
		return ListePartie;
		
	}

	public ObservableList<Joueur> getListeJoueurForfait() {
		return ListeJoueurForfait;
	}
	
}
