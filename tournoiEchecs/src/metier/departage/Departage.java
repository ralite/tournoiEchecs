package metier.departage;

import metier.Joueur;

public abstract class Departage {
	private String nom;

	public Departage(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj!=null){
			return toString().equals(obj.toString());
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return nom;
	}
	
	public abstract float calculDepartage(Joueur j);
	
}
