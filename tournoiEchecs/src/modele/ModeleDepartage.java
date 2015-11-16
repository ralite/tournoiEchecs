package modele;

import javafx.beans.property.ListProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.Departage;

public class ModeleDepartage {
	
	
	private static final ListProperty<Departage> collectionDepartages = new SimpleListProperty<>(FXCollections.observableArrayList());
	public static final ListProperty<Departage> collectionDepartagesProperty() {return collectionDepartages;}
	public static final ObservableList<Departage> getcollectionDepartages() {return collectionDepartagesProperty().get();}
	public static final void setcollectionJoueurs(final ObservableList<Departage> collectionDepartages) {collectionDepartagesProperty().set(collectionDepartages);}
	
	public static void ajouterDepartageChoisi(Departage j) {
		collectionDepartages.add(j);
	}
}
