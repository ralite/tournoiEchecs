package metier;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tournoi {

	private String Nom;
	private String Lieu;
	private LocalDate DateDeb;
	private LocalDate DateFin;
	private String Arbitre;
	private int NbRondes;

	private ArrayList<Departage> ListeDepartages;

	public Tournoi(String nom, String lieu, LocalDate dateDeb, LocalDate dateFin, String arbitre, int nbRondes) {
		Nom = nom;
		Lieu = lieu;
		DateDeb = dateDeb;
		DateFin = dateFin;
		Arbitre = arbitre;
		NbRondes = nbRondes;
	}

	private ArrayList<Departage> getListeDepartages() {
		return this.ListeDepartages;
	}

	private void setListeDepartages(ArrayList<Departage> liste) {
		this.ListeDepartages=liste;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public LocalDate getDateDeb() {
		return DateDeb;
	}

	public void setDateDeb(LocalDate dateDeb) {
		DateDeb = dateDeb;
	}

	public LocalDate getDateFin() {
		return DateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		DateFin = dateFin;
	}

	public String getArbitre() {
		return Arbitre;
	}

	public void setArbitre(String arbitre) {
		Arbitre = arbitre;
	}

	public int getNbRondes() {
		return NbRondes;
	}

	public void setNbRondes(int nbRondes) {
		NbRondes = nbRondes;
	}
}
