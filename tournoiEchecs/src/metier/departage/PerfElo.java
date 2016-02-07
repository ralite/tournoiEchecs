package metier.departage;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;

public class PerfElo extends Departage {
	
	public PerfElo(){
		super("Departage3");
	}

	@Override
	public float calculDepartage(Joueur j) {
		float moyElo=0;
		float bonusMalus; 
		int nbPartie=0;
		for(int i=0;i<ModeleTournoi.getTournoi().getNumRondeActuelle();i++){
			for (Partie partie : ModeleTournoi.getTournoi().getPartieRonde(i)) {
				if(partie.joueurEstDansPartie(j)){
					nbPartie++;
					if(j==partie.getJoueurBlanc()){
						moyElo+=partie.getJoueurNoir().getElo();
					}
					else{
						moyElo+=partie.getJoueurBlanc().getElo();
					}
				}
			}
		}
		bonusMalus=((j.getScore()/nbPartie)-0.5f)*750;
		return Math.round(moyElo+bonusMalus);
	}

}
