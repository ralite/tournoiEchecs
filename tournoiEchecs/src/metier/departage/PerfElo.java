package metier.departage;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;

public class PerfElo extends Departage {
	
	public PerfElo(){
		super("PerfElo");
	}

	@Override
	public float calculDepartage(Joueur j) {
		float moyElo=0;
		float bonusMalus; 
		int nbPartie=0;
		int numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1)
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		for(int i=0;i<numRonde;i++){
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
		return (float)Math.round(moyElo/nbPartie+bonusMalus);
	}

}
