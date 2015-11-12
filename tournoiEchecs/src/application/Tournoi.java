package application;

import java.util.ArrayList;
import java.util.Date;

public class Tournoi {
	
	private String Lieu;
	private Date DateDeb;
	private Date DateFin;
	private String nomArbitre;
	private String prenomArbitre;
	private int nbRondes;
	private ArrayList<String> ListeDepartages;
	
	public Tournoi(String nomTournoi, String lieu, Date dateDeb, Date dateFin, String nomArbitre, String prenomArbitre,
			int nbRondes, ArrayList<String> listeDepartages) {
		super();
		this.nomTournoi = nomTournoi;
		Lieu = lieu;
		DateDeb = dateDeb;
		DateFin = dateFin;
		this.nomArbitre = nomArbitre;
		this.prenomArbitre = prenomArbitre;
		this.nbRondes = nbRondes;
		ListeDepartages = listeDepartages;
	}

	private String nomTournoi;
	public String getNomTournoi() {
		return nomTournoi;
	}

	public void setNomTournoi(String nomTournoi) {
		this.nomTournoi = nomTournoi;
	}

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public Date getDateDeb() {
		return DateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		DateDeb = dateDeb;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}

	public String getNomArbitre() {
		return nomArbitre;
	}

	public void setNomArbitre(String nomArbitre) {
		this.nomArbitre = nomArbitre;
	}

	public String getPrenomArbitre() {
		return prenomArbitre;
	}

	public void setPrenomArbitre(String prenomArbitre) {
		this.prenomArbitre = prenomArbitre;
	}

	public int getNbRondes() {
		return nbRondes;
	}

	public void setNbRondes(int nbRondes) {
		this.nbRondes = nbRondes;
	}

	public ArrayList<String> getListeDepartages() {
		return ListeDepartages;
	}

	public void setListeDepartages(ArrayList<String> listeDepartages) {
		ListeDepartages = listeDepartages;
	}

	
public String getArbitre(){
	return this.nomArbitre;
}

}
