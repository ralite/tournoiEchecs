package metier.departage;

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
		// TODO Auto-generated method stub
		if(obj!=null){
			return toString().equals(obj.toString());
		}
		else{
			return false;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nom;
	}
	
	public abstract float calculDepartage();
	
}
