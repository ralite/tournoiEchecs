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
	
	private String Lieu;
	private LocalDate DateDeb;
	private LocalDate DateFin;
	private String arbitre;
	
	private StringProperty nbRondes = new SimpleStringProperty();
	public StringProperty groupeProperty(){return nbRondes;}
	public String getNbRondes() {return nbRondes.get();}
	public void setNbRondes(int nbRondes) {this.nbRondes.set(String.valueOf(nbRondes));}
	
	private ListProperty<Departage> listeDepartages = new SimpleListProperty<>(FXCollections.observableArrayList());
	public ListProperty<Departage> listeDepartagesProperty() {return this.listeDepartages;}
	public ObservableList<Departage> getListeDepartages() {return this.listeDepartagesProperty().get();}
	public void setListeDepartages(ObservableList<Departage> listeDepartages) {this.listeDepartagesProperty().set(listeDepartages);}

	
	public Tournoi(String nomTournoi, String lieu, LocalDate dateDeb, LocalDate dateFin, String arbitre,
			Integer nbRondes) {
		setNomTournoi(nomTournoi);
		setLieu(lieu);
		setDateDeb(dateDeb);
		setDateFin(dateFin);
		setArbitre(arbitre);
		setNbRondes(nbRondes);
		setListeDepartages(listeDepartages);
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
		return arbitre;
	}

	public void setArbitre(String Arbitre) {
		this.arbitre = Arbitre;
	}

	


}
