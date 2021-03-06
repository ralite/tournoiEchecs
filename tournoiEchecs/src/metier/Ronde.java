 package metier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ronde {

	private int numeroRonde;
	private ObservableList<Partie> ListePartie;
	private ObservableList<Joueur> ListeJoueurAbs;
	private ObservableList<Joueur> ListeJoueurForfait;
	private boolean saisiePossible= false;
	private boolean appariementPossible= true;
	private Joueur joueurExempt;
	private float scoreJoueurExemptRonde;
	
	public void setJoueurExempt(Joueur joueurExempt) {
		this.joueurExempt = joueurExempt;
	}

	public boolean isSaisie() {
		return saisiePossible;
	}

	public void setSaisie(boolean saisie) {
		this.saisiePossible = saisie;
	}

	public boolean isApp() {
		return appariementPossible;
	}

	public void setApp(boolean app) {
		this.appariementPossible = app;
	}

	public Ronde(int numeroRonde) {
		this.numeroRonde = numeroRonde;
		ListePartie = FXCollections.observableArrayList();
		ListeJoueurAbs = FXCollections.observableArrayList();
		ListeJoueurForfait = FXCollections.observableArrayList();
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

	public void setListeJoueurForfait(ObservableList<Joueur> listejoueursForfait) {
		ListeJoueurForfait = listejoueursForfait;
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

	public ObservableList<Joueur> getListeJoueurForfait() {
		return ListeJoueurForfait;
	}

	public String getNumJoueurExempt() {
		return joueurExempt.getNumLicence();
	}

	public Joueur getJoueurExempt() {
		return joueurExempt;
	}

	public float getScoreJoueurExemptRonde() {
		return scoreJoueurExemptRonde;
	}

	public void setScoreJoueurExemptRonde(float scoreJoueurExemptRonde) {
		this.scoreJoueurExemptRonde = scoreJoueurExemptRonde;
	}
}
