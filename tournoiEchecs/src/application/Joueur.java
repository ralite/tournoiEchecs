package application;

public class Joueur {
	private String numLicence;
	private String nomJoueur;
	private String prenomJoueur;
	private float elo;
	private String categorie;
	
	public Joueur(String numLicence, String nomJoueur, String prenomJoueur, float elo, String categorie) {
		super();
		this.numLicence = numLicence;
		this.nomJoueur = nomJoueur;
		this.prenomJoueur = prenomJoueur;
		this.elo = elo;
		this.categorie = categorie;
	}

	public String getNumLicence() {
		return numLicence;
	}

	public void setNumLicence(String numLicence) {
		this.numLicence = numLicence;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public String getPrenomJoueur() {
		return prenomJoueur;
	}

	public void setPrenomJoueur(String prenomJoueur) {
		this.prenomJoueur = prenomJoueur;
	}

	public float getElo() {
		return elo;
	}

	public void setElo(float elo) {
		this.elo = elo;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	

}
