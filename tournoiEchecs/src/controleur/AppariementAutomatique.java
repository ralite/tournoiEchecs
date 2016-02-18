package controleur;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AppariementAutomatique {
	public static void calculAppariementAuto(ObservableList<Joueur> joueurs, ObservableList<Partie> parties){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==0)
			calculAppariementPremiereRonde(joueurs,parties);
		else
			calculAppariement(joueurs,parties);
	}

	private static void calculAppariement(ObservableList<Joueur> joueurs,ObservableList<Partie> parties) {
		int i=0;
		boolean appImpossible=false;
		while(joueurs.size()>1 && !appImpossible){
			int k = i+1;
			while (k<joueurs.size()&& ModeleTournoi.getTournoi().dejaRencontre(joueurs.get(i), joueurs.get(k))&&!appImpossible){
				k++;
				if(k==joueurs.size()){
					k+=-1;
					appImpossible = appariementImpossible();

				}
			}
			if(!appImpossible){
				if(nbFoisJoueurJoueBlanc(joueurs.get(i))<nbFoisJoueurJoueBlanc(joueurs.get(k)))
					parties.add(new Partie(joueurs.get(i),joueurs.get(k)));
				else if(nbFoisJoueurJoueBlanc(joueurs.get(i))==nbFoisJoueurJoueBlanc(joueurs.get(k))){
					if(joueurs.get(i).getCouleurRonde(ModeleTournoi.getTournoi().getNumRondeActuelle()-1).equals("B"))
						parties.add(new Partie(joueurs.get(k),joueurs.get(i)));
					else
						parties.add(new Partie(joueurs.get(i),joueurs.get(k)));
				}
				else
					parties.add(new Partie(joueurs.get(k),joueurs.get(i)));

				joueurs.removeAll(joueurs.get(i),joueurs.get(k));
			}

		}
		if(!appImpossible)
			setJoueurExempt(joueurs,parties);

	}

	private static void setJoueurExempt(ObservableList<Joueur> joueurs, ObservableList<Partie> parties) {
		boolean appImpossible =false;
		if(joueurs.size()%2!=0){
			Joueur joueurExempt = joueurs.get(joueurs.size()-1);
			if(joueurExempt.getCouleur().contains("X")){
				Partie p = parties.get(parties.size()-1);
				parties.remove(p);
				if(!ModeleTournoi.getTournoi().dejaRencontre(joueurExempt, p.getJoueurBlanc())&&!ModeleTournoi.getTournoi().dejaRencontre(joueurExempt, p.getJoueurNoir())){
					if(p.getJoueurBlanc().getScore()<p.getJoueurNoir().getScore()){
						parties.add(new Partie(joueurExempt, p.getJoueurNoir()));
						joueurExempt=p.getJoueurBlanc();
					}
					else{
						parties.add(new Partie(p.getJoueurBlanc(), joueurExempt));
						joueurExempt=p.getJoueurNoir();
					}
				}
				else{
					if(ModeleTournoi.getTournoi().dejaRencontre(joueurExempt, p.getJoueurBlanc())){
						parties.add(new Partie(joueurExempt, p.getJoueurNoir()));
						joueurExempt=p.getJoueurBlanc();
					}
					else if(ModeleTournoi.getTournoi().dejaRencontre(joueurExempt, p.getJoueurNoir())){
						parties.add(new Partie(joueurExempt, p.getJoueurBlanc()));
						joueurExempt=p.getJoueurNoir();
					}
					else{
						appImpossible = appariementImpossible();
					}
				}
			}
			if(!appImpossible){
				joueurs.clear();
				joueurs.add(joueurExempt);
			}
			else{
				parties.clear();
			}
		}
		else
			joueurs.clear();
	}

	private static boolean appariementImpossible() {
		boolean appImpossible;
		appImpossible=true;
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Erreur");
		alert.setContentText("Vueillez appairer les joueurs à la main s'il vous plait !");
		alert.showAndWait();
		return appImpossible;
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

	private static int nbFoisJoueurJoueBlanc(Joueur j){
		int compteur = 0;
		String str =j.getCouleur();
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == 'B')
				System.out.println(str.charAt(i));
		compteur++;
		return compteur;
	}


}
