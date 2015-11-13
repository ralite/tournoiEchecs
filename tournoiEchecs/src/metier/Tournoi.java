package metier;

import java.util.ArrayList;
import java.util.Date;

public class Tournoi {
	
	private String Lieu;
	private Date DateDeb;
	private Date DateFin;
	private String arbitre;
	private int nbRondes;
	private ArrayList<String> ListeDepartages;
	
	public Tournoi(String nomTournoi, String lieu, Date dateDeb, Date dateFin, String arbitre,
			int nbRondes, ArrayList<String> listeDepartages) {
		super();
		this.nomTournoi = nomTournoi;
		Lieu = lieu;
		DateDeb = dateDeb;
		DateFin = dateFin;
		this.arbitre=arbitre;
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

	public String getArbitre() {
		return arbitre;
	}

	public void setArbitre(String Arbitre) {
		this.arbitre = Arbitre;
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

}
