package metier;

import java.time.LocalDate;
import java.util.Date;

public class Joueur {
	private int numLicence;
	private String nomJoueur;
	private String prenomJoueur;
	private String sexe;
	private LocalDate dateNaissance;
	private String titreFide;
	private String ligue;
	private float elo;
	private String categorie;
	private String club;

	public Joueur(int numLicence, String nomJoueur, String prenomJoueur, String sexe, LocalDate dateNaissance, String titreFide, String ligue, float elo, String categorie, String club) {
		super();
		this.numLicence = numLicence;
		this.nomJoueur = nomJoueur;
		this.prenomJoueur = prenomJoueur;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.titreFide = titreFide;
		this.ligue = ligue;
		this.elo = elo;
		this.categorie = categorie;
		this.club = club;
	}

	public Joueur(int numLicence, String nomJoueur, String prenomJoueur) {
		this.numLicence = numLicence;
		this.nomJoueur = nomJoueur;
		this.prenomJoueur = prenomJoueur;
	}

	public int getNumLicence() {
		return numLicence;
	}

	public void setNumLicence(int numLicence) {
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTitreFide() {
		return titreFide;
	}

	public void setTitreFide(String titreFide) {
		this.titreFide = titreFide;
	}

	public String getLigue() {
		return ligue;
	}

	public void setLigue(String ligue) {
		this.ligue = ligue;
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

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return  numLicence + " " + nomJoueur
				+ " " + prenomJoueur;
	}
}
