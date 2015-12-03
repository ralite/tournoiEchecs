package metier;

import java.time.LocalDate;
import java.util.Date;

public class Joueur {
	private String numLicence;
	private String nomJoueur;
	private String prenomJoueur;
	private String sexe;
	private LocalDate dateNaissance;
	private String titre;
	private String ligue;
	private int elo;
	private String typeElo;
	private String federation;
	private String categorie;
	private String club;

	public Joueur(String numLicence, String nomJoueur, String prenomJoueur, String sexe, LocalDate dateNaissance, String titre, String ligue, int elo, String typeElo, String federation, String categorie, String club) {
		super();
		this.numLicence = numLicence;
		this.nomJoueur = nomJoueur;
		this.prenomJoueur = prenomJoueur;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.titre = titre;
		this.ligue = ligue;
		this.elo = elo;
		this.setTypeElo(typeElo);
		this.setFederation(federation);
		this.categorie = categorie;
		this.club = club;
	}

	public Joueur(String numLicence, String nomJoueur, String prenomJoueur) {
		this.numLicence = numLicence;
		this.nomJoueur = nomJoueur;
		this.prenomJoueur = prenomJoueur;
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

	public String getTitre() {
		return titre;
	}

	public void setTitreFide(String titre) {
		this.titre = titre;
	}

	public String getLigue() {
		return ligue;
	}

	public void setLigue(String ligue) {
		this.ligue = ligue;
	}

	public int getElo() {
		return elo;
	}

	public void setElo(int elo) {
		this.elo = elo;
	}

	public String getTypeElo() {
		return typeElo;
	}

	public void setTypeElo(String typeElo) {
		this.typeElo = typeElo;
	}

	public String getFederation() {
		return federation;
	}

	public void setFederation(String federation) {
		this.federation = federation;
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