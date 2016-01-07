package metier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ronde {

	private int numeroRonde;
	private ObservableList<Partie> ListePartie;
	
	public Ronde(int numeroRonde) {
		this.numeroRonde = numeroRonde;
		ListePartie = FXCollections.observableArrayList();
	}
	
	public int getNumeroRonde() {
		return numeroRonde;
	}
	
	public void setNumeroRonde(int numeroRonde) {
		this.numeroRonde = numeroRonde;
	}
	
	public ObservableList<Partie> getListePartie() {
		return ListePartie;
	}
	
	public void setListePartie(ObservableList<Partie> listePartie) {
		ListePartie = listePartie;
	}
	
}
