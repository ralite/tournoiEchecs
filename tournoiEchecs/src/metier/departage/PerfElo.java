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
		float somElo=0;
		float bonusMalus; 
		int nbPartie=0;
		float score=j.getScore();
		int numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1)
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		//pout toute les ronde du tournoi
		for(int i=0;i<numRonde;i++){
			//pour toutes les parties d'une ronde
			for (Partie partie : ModeleTournoi.getTournoi().getPartieRonde(i)) {
				//si le joueur est dans la partie
				if(partie.joueurEstDansPartie(j)){
					if(j==partie.getJoueurBlanc()){
						if(!partie.getResultat().equals("blancForfait")||!partie.getResultat().equals("noirForfait")){
							somElo+=partie.getJoueurNoir().getElo();
							nbPartie++;
						}
						if(partie.getResultat().equals("noirForfait")){
							score+=-1;
						}
					}
					else{
						if(!partie.getResultat().equals("noirForfait")||!partie.getResultat().equals("blancForfait")){
							somElo+=partie.getJoueurBlanc().getElo();
							nbPartie++;
						}
						if(partie.getResultat().equals("blancForfait")){
							score+=-1;
						}
					}
					//le joueur est trouvé on passe a la ronde suivante
					break;
				}
			}
		}
		//si le joueur est exempt
		if(j.getCouleur().contains("X")){
			score+=-1;
		}
		//calcul perf elo
		bonusMalus=((score/nbPartie)-0.5f)*750;
		return (float)Math.round(somElo/nbPartie+bonusMalus);
	}

}
