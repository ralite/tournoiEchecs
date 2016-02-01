package metier;

public class Partie {
	
	private Joueur joueurBlanc;
	private Joueur joueurNoir;
	private String resultat = "";


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
	}

	public void setResultat(String resultat2) {
		resultat=resultat2;	
	}	
}
