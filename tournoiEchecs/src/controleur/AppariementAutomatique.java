package controleur;

import org.omg.CORBA.PRIVATE_MEMBER;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.collections.ObservableList;

public class AppariementAutomatique {
	public static void calculAppariementAuto(ObservableList<Joueur> joueurs, ObservableList<Partie> parties){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==0)
			calculAppariementPremiereRonde(joueurs,parties);
		else
			calculAppariement(joueurs,parties);
	}

	private static void calculAppariement(ObservableList<Joueur> joueurs,ObservableList<Partie> parties) {
		int i =0;
		int j = 1;
		while(j<joueurs.size()){
			if(i!=j-1){
				parties.add(new Partie(joueurs.get(i), joueurs.get(j)));
				i=j+1;
				j+=2;
			}
			else{
				if(ModeleTournoi.getTournoi().dejaRencontre(joueurs.get(i), joueurs.get(j))){
					j++;
					parties.add(new Partie(joueurs.get(i), joueurs.get(j)));
					i++;
					j++;
					
				}
				else{
					parties.add(new Partie(joueurs.get(i), joueurs.get(j)));
					i+=2;
					j+=2;
				}
			}
			
		}
		setJoueurExempt(joueurs);
		
	}

	private static void setJoueurExempt(ObservableList<Joueur> joueurs) {
		if(joueurs.size()%2!=0){
			Joueur joueurExempt = joueurs.get(joueurs.size()-1);
			joueurs.clear();
			joueurs.add(joueurExempt);
		}
	}

	private static void calculAppariementPremiereRonde(ObservableList<Joueur> joueurs, ObservableList<Partie> parties) {
		int j=joueurs.size()/2;
		int i=0;
		while(i<joueurs.size()/2 && j<joueurs.size()){
			System.out.println(i);
			System.out.println(j);
			parties.add(new Partie(joueurs.get(i), joueurs.get(j)));
			j++;
			i++;
		}
		setJoueurExempt(joueurs);
		
	}
	
	
}
