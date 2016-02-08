package metier.departage;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;

public class Buchholz extends Departage{

	public Buchholz() {
		super("Buchholz");
	}

	@Override
	public float calculDepartage(Joueur j) {
		float som=0;
		int numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1)
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		for(int i=0;i<numRonde;i++){
			for (Partie partie : ModeleTournoi.getTournoi().getPartieRonde(i)) {
				if(partie.joueurEstDansPartie(j)){
					if(j==partie.getJoueurBlanc()){
						som+=partie.getScoreJoueurNoir();
					}
					else{
						som+=partie.getScoreJoueurBlanc();
					}
				}
			}
		}
		return som;
	}
}
