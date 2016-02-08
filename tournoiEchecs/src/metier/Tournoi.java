package metier;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import metier.departage.Departage;

public class Tournoi {

	private String Nom;
	private String Lieu;
	private LocalDate DateDeb;
	private LocalDate DateFin;
	private String Arbitre;
	private int NbRondes;
	private ObservableList<Joueur> ListeJoueurs;
	private ObservableList<Departage> ListeDepartages;
	private ObservableList<Ronde> ListeRondes;
	private String cadenceJeu;
	private int rondeActuelle;

	public Tournoi(String nom, String lieu, LocalDate dateDeb, LocalDate dateFin, String arbitre, int nbRondes, String cadence) {
		Nom = nom;
		Lieu = lieu;
		DateDeb = dateDeb;
		DateFin = dateFin;
		Arbitre = arbitre;
		NbRondes = nbRondes;
		cadenceJeu=cadence;
		ListeJoueurs = FXCollections.observableArrayList();
		ListeDepartages = FXCollections.observableArrayList();
		ListeRondes = FXCollections.observableArrayList();
		for(int i=0;i<nbRondes;i++){
			ListeRondes.add(new Ronde(i+1));
		}
		rondeActuelle=0;
			
	}

	public void AddJoueur(Joueur j) {
		this.ListeJoueurs.add(j);
	}
	
	public void RemoveJoueurs(Joueur j) {
		this.ListeJoueurs.remove(j);
	}

	public ObservableList<Joueur> getListeJoueurs() {
		return this.ListeJoueurs;
	}

	public void setListeJoueurs(ObservableList<Joueur> liste) {
		this.ListeJoueurs=liste;
	}

	public void AddDepartages(Departage d) {
		this.ListeDepartages.add(d);
	}

	public void RemoveDepartages(Departage d) {
		this.ListeDepartages.remove(d);
	}

	public ObservableList<Departage> getListeDepartages() {
		return this.ListeDepartages;
	}

	public void setListeDepartages(ObservableList<Departage> liste) {
		this.ListeDepartages=liste;
	}

	public ObservableList<Ronde> getListeRondes() {
		return ListeRondes;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public LocalDate getDateDeb() {
		return DateDeb;
	}

	public void setDateDeb(LocalDate dateDeb) {
		DateDeb = dateDeb;
	}

	public LocalDate getDateFin() {
		return DateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		DateFin = dateFin;
	}

	public String getArbitre() {
		return Arbitre;
	}

	public void setArbitre(String arbitre) {
		Arbitre = arbitre;
	}

	public int getNbRondes() {
		return NbRondes;
	}
	
	public int getNumRondeActuelle(){
		return rondeActuelle;
	}

	public Ronde getRondeActuelle(){
		System.out.println(rondeActuelle);
		return ListeRondes.get(rondeActuelle);
	}
	
	public void setNbRondes(int nbRondes) {
		for(int i=NbRondes;i<nbRondes;i++){
			ListeRondes.add(new Ronde(i+1));
		}
		NbRondes = nbRondes;
	}

	public String getCadenceJeu() {
		return cadenceJeu;
	}

	public void setCadenceJeu(String cadenceJeu) {
		this.cadenceJeu = cadenceJeu;
	}


	public void setAbsentRonde(ObservableList<Joueur> joueurs) {
		ListeRondes.get(rondeActuelle).setListeJoueurAbs(joueurs);
	}
	
	@Override
	public String toString() {
		return "Tournoi [Nom=" + Nom + ", Lieu=" + Lieu + ", DateDeb=" + DateDeb + ", DateFin=" + DateFin + ", Arbitre="
				+ Arbitre + ", NbRondes=" + NbRondes + ", CadenceJeu=" + cadenceJeu + "]";
	}

	public void setForfaitRonde(ObservableList<Joueur> JoueursForfait) {
		ListeRondes.get(rondeActuelle).setListeJoueurForfait(JoueursForfait);
		
	}
	
	public boolean dejaRencontre(Joueur j1, Joueur j2){
		boolean dejaRencontre=false;
		int i = 0;
		while(!dejaRencontre && i<NbRondes){
			dejaRencontre=ListeRondes.get(i).dejaRencontre(j1,j2);
			i++;
		}
		return dejaRencontre;
		
	}

	public ObservableList<Partie> getPartieRondeActuelle() {
		return ListeRondes.get(rondeActuelle).getParties();
	}
	
	public void setPartiesRonde(ObservableList<Partie> listePartie){
		ListeRondes.get(rondeActuelle).setListePartie(listePartie);
	}
	
	public ObservableList<Joueur> getJoueursRondeActuelle() {
		ObservableList<Joueur> joueurs = FXCollections.observableArrayList();
		for (Partie partie : ListeRondes.get(rondeActuelle).getParties()) {
			joueurs.addAll(partie.getJoueurBlanc(),partie.getJoueurNoir());
		}
		return joueurs;
	}
	
	public ObservableList<Joueur> getJoueurAbsRondeActuelle() {
		return ListeRondes.get(rondeActuelle).getListeJoueurAbs();
	}

	public ObservableList<Joueur> getJoueurForfaitRondeActuelle() {
		return ListeRondes.get(rondeActuelle).getListeJoueurForfait();
	}

	public void rondeSuivante() {
		rondeActuelle++;
	}

	public void tournoiFini() {
		rondeActuelle=-1;
	}

	public void setRondeActuelle(int rondeActuelle2) {
		rondeActuelle=rondeActuelle2;
	}

	public ObservableList<Partie> getPartieRonde(int numRonde) {
		return ListeRondes.get(numRonde).getListePartie();
	}

	public Ronde getRonde(int numRonde) {
		return ListeRondes.get(numRonde);
	}
	
	public void calculerDepartagesJoueurs(){
		Float pointsDepartage=0.f;
		for (Departage departage : ListeDepartages) {
			for (Joueur joueur : ListeJoueurs) {
				pointsDepartage=departage.calculDepartage(joueur);
				joueur.setPointsDepartage(departage.toString(),pointsDepartage);
			}
		}
	}

}
