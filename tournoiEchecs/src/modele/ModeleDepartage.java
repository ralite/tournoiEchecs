package modele;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.departage.Buchholz;
import metier.departage.Cumulatif;
import metier.departage.Departage;
import metier.departage.Departage3;

public class ModeleDepartage {
	
	
	private static final ListProperty<Departage> collectionDepartages = new SimpleListProperty<>(FXCollections.observableArrayList(new Cumulatif(), new Buchholz(), new Departage3()));
	public static final ListProperty<Departage> collectionDepartagesProperty() {return collectionDepartages;}
	public static final ObservableList<Departage> getcollectionDepartages() {return collectionDepartagesProperty().get();}
	public static final void setcollectionJoueurs(final ObservableList<Departage> collectionDepartages) {collectionDepartagesProperty().set(collectionDepartages);}
	
	public static void ajouterDepartageChoisi(Departage j) {
		collectionDepartages.add(j);
	}
}
