package metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partie {
	
	private Joueur joueurBlanc;
	private Joueur joueurNoir;
	private float scoreBlanc;
	private float scoreNoir;
	
	
	public float getScoreJoueurBlancPartie() {
		return scoreBlanc;
	}

	public float getScorejoueurNoirPartie() {
		return scoreNoir;
	}

	private String resultat = "";
	private int classement;


	public Partie(Joueur joueurBlanc, Joueur joueurNoir) {
		this.joueurBlanc = joueurBlanc;
		this.joueurNoir = joueurNoir;
	}
	
	public String getNumLicenceJoueurBlanc(){
		return joueurBlanc.getNumLicence();
	}
	
	public Joueur getJoueurBlanc(){
		return joueurBlanc;
	}
	
	public Joueur getJoueurNoir(){
		return joueurNoir;
	}
	
	public String getNomPrenomJoueurBlanc(){
		return joueurBlanc.getNomJoueur()+" "+joueurBlanc.getPrenomJoueur();
	}
	
	public float getScoreJoueurBlanc(){
		return joueurBlanc.getScore();
	}

	public String getNumLicenceJoueurNoir(){
		return joueurNoir.getNumLicence();
	}
	
	public String getNomPrenomJoueurNoir(){
		return joueurNoir.getNomJoueur()+" "+joueurNoir.getPrenomJoueur();
	}
	
	public float getScoreJoueurNoir(){
		return joueurNoir.getScore();
	}

	public boolean dejaRencontre(Joueur j1, Joueur j2) {
		if((j1==joueurNoir && j2==joueurBlanc)||(j1==joueurBlanc && j2==joueurNoir)){
			return true;
		}
		return false;
	}

	public void joueurNoirGagne() {
		resultat="noirGagne";
		
	}

	public void joueurBlancGagne() {
		resultat="blancGagne";
		
	}

	public void partieNulle() {
		resultat="partieNulle";
		
	}

	public void doubleForfait() {
		resultat="doubleForfait";
		
	}

	public void joueurBlancForfait() {
		resultat="blancForfait";
		
	}

	public void joueurNoirForfait() {
		resultat="noirForfait";
		
	}
	
	public void resutatNonSaisi(){
		resultat="";
	}
	
	public String getResultat() {
		return resultat;
	}
	

	public boolean rechercherPartie(String text) {
		if (joueurBlanc.getNomJoueur().equalsIgnoreCase(text) 
				|| joueurNoir.getNomJoueur().equalsIgnoreCase(text) 
				|| joueurBlanc.getNumLicence().equals(text)
				||joueurNoir.getNumLicence().equals(text)){
			return true;
		}else{
			return false;
		}
		
	}

	public void setCouleurJoueur() {
		joueurBlanc.joueBlanc();
		joueurNoir.joueNoir();
		
	}

	public void setScore() {
		switch (resultat) {
		case "noirGagne": case"blancForfait":
			joueurNoir.gagne1Point();
			break;
		case "blancGagne": case"noirForfait":
			joueurBlanc.gagne1Point();
			break;
		case "partieNulle":
			joueurBlanc.gagneDemiPoint();
			joueurNoir.gagneDemiPoint();
			break;
		}
		scoreBlanc=joueurBlanc.getScore();
		scoreNoir=joueurNoir.getScore();
	}

	public void setResultat(String resultat2) {
		resultat=resultat2;	
	}

	public int compareTo(Partie p2) {
		List<Integer> res=new ArrayList<>();
		res.add(Float.compare(joueurBlanc.getScore(),p2.joueurBlanc.getScore()));
		res.add(Float.compare(joueurBlanc.getScore(),p2.joueurNoir.getScore()));
		res.add(Float.compare(joueurNoir.getScore(),p2.joueurBlanc.getScore()));
		res.add(Float.compare(joueurNoir.getScore(),p2.joueurNoir.getScore()));
		
		return Collections.min(res) ;
	}

	public int getClassement() {
		return classement;
	}

	public void setClassement(int i) {
		classement=i;
		
	}

	public void setScorejoueurBlancPartie(float scoreBlanc) {
		this.scoreBlanc=scoreBlanc;
		
	}	
	
	public void setScorejoueurNoirPartie(float scoreNoir) {
		this.scoreNoir=scoreNoir;
		
	}
}
