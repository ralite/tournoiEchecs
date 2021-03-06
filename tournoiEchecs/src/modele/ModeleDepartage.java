    package modele;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.departage.Buchholz;
import metier.departage.Cumulatif;
import metier.departage.Departage;
import metier.departage.PerfElo;

public class ModeleDepartage {

	private static final ListProperty<Departage> collectionDepartages = new SimpleListProperty<>(FXCollections.observableArrayList(new Cumulatif(), new Buchholz(), new PerfElo()));
	
	public static final ObservableList<Departage> getcollectionDepartages() {
		return collectionDepartages;
	}

	public static void ajouterDepartageChoisi(Departage j) {
		collectionDepartages.add(j);
	}

	public static ArrayList<Departage> factoryDepartage(ArrayList<String> list){
		ArrayList<Departage> list2 = new ArrayList<Departage>();
		for (String departage : list) {

			if(departage.equalsIgnoreCase("buchholz")){
				list2.add(new Buchholz());
			}

			if(departage.equalsIgnoreCase("cumulatif")){
				list2.add(new Cumulatif());
			}

			if(departage.equalsIgnoreCase("perfElo")){
				list2.add(new PerfElo());
			}
		}

		return list2;
	}
}
