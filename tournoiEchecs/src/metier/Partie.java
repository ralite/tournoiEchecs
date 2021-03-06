package metier;

public class Partie {

	private int numEchequier;
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

	/*public int compareTo(Partie p2) {
		int res;
		float m1,m2;
		if(getScoreJoueurBlancPartie()>getScorejoueurNoirPartie())
			m1=getScoreJoueurBlancPartie();
		else {
			m1=getScorejoueurNoirPartie();
		}
		if(p2.getScoreJoueurBlancPartie()>p2.getScorejoueurNoirPartie())
			m2=p2.getScoreJoueurBlancPartie();
		else {
			m2=p2.getScorejoueurNoirPartie();
		}
		res=Float.compare(m1, m2);
		return res ;
	}*/


	public void setScorejoueurBlancPartie(float scoreBlanc) {
		this.scoreBlanc=scoreBlanc;

	}

	public void setScorejoueurNoirPartie(float scoreNoir) {
		this.scoreNoir=scoreNoir;

	}

	public boolean joueurEstDansPartie(Joueur j) {
		if(j==joueurBlanc || j==joueurNoir)
			return true;
		else
			return false;
	}

	public String getAffichageGa(Joueur joueur, int i) {
		String resAff="";
		if(joueur==joueurBlanc){
			if(resultat.equals("blancGagne")){
				resAff="+";
			}
			else if(resultat.equals("noirForfait")){
				resAff=">";
			}			
			else if(resultat.equals("blancForfait")){
				resAff="<";
			}
			else if(resultat.equals("partieNulle")){
				resAff="=";
			}
			else{
				resAff="-";
			}
			resAff+=" "+joueurNoir.getClassement();
			resAff+=joueurBlanc.getCouleurRonde(i);
		}
		else {
			if(resultat.equals("noirGagne")){
				resAff="+";
			}
			else if(resultat.equals("blancForfait")){
				resAff=">";
			}
			else if(resultat.equals("noirForfait")){
				resAff="<";
			}
			else if(resultat.equals("partieNulle")){
				resAff="=";
			}
			else{
				resAff="-";
			}
			resAff+=" "+joueurBlanc.getClassement();
			resAff+=joueurNoir.getCouleurRonde(i);
		}
		return resAff;
	}

	public void setNumEchequier(int i) {
		numEchequier=i;
		
	}

	public int getNumEchequier() {
		return numEchequier;
	}
}
