package metier;

public class TestJooueur {
	private int num;
	private String nom;
	private String prenom;
	
	@Override
	public String toString() {
		return num + " " + nom + " "
				+ prenom ;
	}

	public TestJooueur(int num, String nom, String p) {
		super();
		this.setNum(num);
		this.setNom(nom);
		this.setPrenom(p);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	};
		
}
