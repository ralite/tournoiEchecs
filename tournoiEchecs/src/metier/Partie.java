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
		
}
