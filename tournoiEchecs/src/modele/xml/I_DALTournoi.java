package modele.xml;

import metier.Tournoi;

public interface I_DALTournoi {
	public void writeTournoi(Tournoi tournoi);
	
	public Tournoi readTournoi();
}
