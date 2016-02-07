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
		for(int i=0;i<ModeleTournoi.getTournoi().getNumRondeActuelle();i++){
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
