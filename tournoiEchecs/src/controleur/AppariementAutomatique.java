package controleur;

import java.rmi.Remote;

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
		int i=0;
		int numrondePrec = ModeleTournoi.getTournoi().getNumRondeActuelle()-1;
		while(joueurs.size()>1){
			int k = i+1;
			while (ModeleTournoi.getTournoi().dejaRencontre(joueurs.get(i), joueurs.get(k)) && k<joueurs.size()){
				k++;
			}
			if(joueurs.get(i).getCouleurRonde(numrondePrec).equals("B")&&joueurs.get(i).getCouleurRonde(numrondePrec-1).equals("B"))
				parties.add(new Partie(joueurs.get(k), joueurs.get(i)));
			else if(joueurs.get(i).getCouleurRonde(numrondePrec).equals("N")&&joueurs.get(i).getCouleurRonde(numrondePrec-1).equals("N"))
					parties.add(new Partie(joueurs.get(i), joueurs.get(k)));
				else if(joueurs.get(k).getCouleurRonde(numrondePrec).equals("B")&&joueurs.get(k).getCouleurRonde(numrondePrec-1).equals("B"))
						parties.add(new Partie(joueurs.get(i), joueurs.get(k)));
					else if(joueurs.get(k).getCouleurRonde(numrondePrec).equals("N")&&joueurs.get(k).getCouleurRonde(numrondePrec-1).equals("N"))
							parties.add(new Partie(joueurs.get(k), joueurs.get(i)));
						 else
							 parties.add(new Partie(joueurs.get(i), joueurs.get(k)));
			joueurs.removeAll(joueurs.get(i),joueurs.get(k));
			
			
		}
		setJoueurExempt(joueurs,parties);
		
	}

	private static void setJoueurExempt(ObservableList<Joueur> joueurs, ObservableList<Partie> parties) {
		if(joueurs.size()%2!=0){
			Joueur joueurExempt = joueurs.get(joueurs.size()-1);
			if(joueurExempt.getCouleur().contains("X")){
				Partie p = parties.get(parties.size()-1);
				parties.remove(p);
				if(p.getJoueurBlanc().getScore()<p.getJoueurNoir().getScore()){
					parties.add(new Partie(joueurExempt, p.getJoueurNoir()));					
				}
				else{
					parties.add(new Partie(p.getJoueurBlanc(), joueurExempt));
				}
			}
			joueurs.clear();
			joueurs.add(joueurExempt);
		}
		else
			joueurs.clear();
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
		setJoueurExempt(joueurs,parties);
		
	}
	
	
}
