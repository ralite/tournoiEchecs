package metier;

public class Partie {
	
	private Joueur joueurBlanc;
	private Joueur joueurNoir;

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
	
	public int getElojoueurBlanc(){
		return joueurBlanc.getElo();
	}

	public String getNumLicenceJoueurNoir(){
		return joueurNoir.getNumLicence();
	}
	
	public String getNomPrenomJoueurNoir(){
		return joueurNoir.getNomJoueur()+" "+joueurNoir.getPrenomJoueur();
	}
	
	public int getElojoueurNoir(){
		return joueurNoir.getElo();
	}

	public boolean dejaRencontre(Joueur j1, Joueur j2) {
		if((j1==joueurNoir && j2==joueurBlanc)||(j1==joueurBlanc && j2==joueurNoir)){
			return true;
		}
		return false;
	}
		
}
