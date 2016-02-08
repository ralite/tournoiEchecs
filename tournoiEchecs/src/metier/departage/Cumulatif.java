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
		for(int i=0;i<ModeleTournoi.getTournoi().getNumRondeActuelle();i++){
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
