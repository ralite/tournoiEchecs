package metier.departage;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;

public class Cumulatif extends Departage {

	public Cumulatif() {
		super("Cumulatif");
	}

	@Override
	public float calculDepartage(Joueur j) {
		float cumul=0;
		int numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1)
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		for(int i=0;i<numRonde;i++){
			for (Partie partie : ModeleTournoi.getTournoi().getPartieRonde(i)) {
				if(partie.joueurEstDansPartie(j)){
					if(j==partie.getJoueurBlanc()){
						cumul+=partie.getScoreJoueurBlancPartie();
					}
					else{
						cumul+=partie.getScorejoueurNoirPartie();
					}
				}
			}
		}
		return cumul;
	}
}
