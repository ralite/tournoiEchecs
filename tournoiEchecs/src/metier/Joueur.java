package metier;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
	private String couleurs;
	private float score;

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
		this.typeElo = typeElo;
		this.federation = federation;
		this.categorie = categorie;
		this.club = club;
		this.couleurs = "";
		this.score = 0;
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

	public static String getCategorieCalculee(LocalDate dateNaissance)
	{
		String categorie="";
		LocalDate ref = LocalDate.parse(LocalDate.now().getYear()+"-01-01");

		long age = ChronoUnit.YEARS.between(dateNaissance, ref);

		if(age<8)
			categorie = "Petit Poussin";
		else if(age<10 && (age>8||age==8))
			categorie = "Poussin";
		else if(age<12 && (age>10||age==10))
			categorie = "Pupille";
		else if(age<14 && (age>12||age==12))
			categorie = "Benjamin";
		else if(age<16 && (age>14||age==14))
			categorie = "Minime";
		else if(age<18 && (age>16||age==16))
			categorie = "Cadet";
		else if(age<20 && (age>18||age==18))
			categorie = "Junior";
		else if((age>20||age==20) && age<55)
			categorie = "Sénior";
		else if(age==55||age>55)
			categorie="Vétéran";

		return categorie;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}
	
	public float getScore(){
		return score;
	}

	@Override
	public String toString() {
		return  numLicence + " " + nomJoueur
				+ " " + prenomJoueur + " " + elo;
	}
	
	public void joueBlanc(){
		if(couleurs.isEmpty()){
			couleurs="B";
		}
		else couleurs+="/B";
	}
	
	public void joueNoir(){
		if(couleurs.isEmpty())
			couleurs="N";
		else couleurs+="/N";
	}
	
	public void joueAbs(){
		if(couleurs.isEmpty())
			couleurs="a";
		else couleurs+="/a";
	}
	
	public void joueForfait(){
		if(couleurs.isEmpty())
			couleurs="f";
		else couleurs+="/f";
	}

	public void gagne1Point() {
		score+=1;
	}

	public void gagneDemiPoint() {
		score+=0.5;
	}

	public String getCouleur() {
		if(!couleurs.isEmpty())
			return couleurs;
		else return "";
	}
	
	public void initialiser() {
		score=0;
		couleurs="";
	}

	public void setScore(float s) {
		score=s;
	}

	public void setCouleur(String couleurJoueur) {
		couleurs=couleurJoueur;
	}

	public void setExempt() {
		if(couleurs.isEmpty())
			couleurs="X";
		else couleurs+="/X";
	}
	
	public String getCouleurRonde(int i){
		String[] res= couleurs.split("/");
		return res[i];
	}
	
}
