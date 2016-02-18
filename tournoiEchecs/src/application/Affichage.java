package application;

import java.util.HashMap;
import java.util.Map;

public class Affichage {

    public static final Map<String,String> mapTitre = new HashMap<String,String>();
    public static final Map<String,String> mapSexe = new HashMap<String,String>();
    public static final Map<String,String> mapCategorie = new HashMap<String,String>();
    public static final Map<String,String> mapTypeElo = new HashMap<String,String>();
    public static final Map<String,String> mapResultat = new HashMap<String, String>();
    public static final Map<String,String> mapDepartages = new HashMap<String, String>();

    public static void chargerMapsGrilleAEtClassements(){
    	mapTitre.put("Ma�tre FIDE Masculin", "f");
        mapTitre.put("Ma�tre FIDE F�minin", "f");
        mapTitre.put("Ma�tre International Masculin", "m");
        mapTitre.put("Ma�tre International F�minin", "m");
        mapTitre.put("Grand Ma�tre International Masculin", "g");
        mapTitre.put("Grand Ma�tre International F�minin", "g");
        mapTitre.put("Candidat Ma�tre Masculin", " ");
        mapTitre.put("Candidat Ma�tre F�minin", " ");
        mapTitre.put("Aucun titre", " ");

        mapCategorie.put("V�t�ran", "Vet");
        mapCategorie.put("S�nior", "Sen");
        mapCategorie.put("Junior", "Jun");
        mapCategorie.put("Cadet", "Cad");
        mapCategorie.put("Minime", "Min");
        mapCategorie.put("Benjamin", "Ben");
        mapCategorie.put("Pupille", "Pup");
        mapCategorie.put("Poussin", "Pou");
        mapCategorie.put("Petit Poussin", "Pet");

        mapTypeElo.put("FIDE", "F");
        mapTypeElo.put("National", "N");
        mapTypeElo.put("Nouveau", "E");

        mapSexe.put("Homme", "M");
        mapSexe.put("Femme", "F");

        mapResultat.put("blancGagne", "1-0");
		mapResultat.put("noirGagne", "0-1");
		mapResultat.put("partieNulle", "X-X");
		mapResultat.put("blancForfait", "F-1");
		mapResultat.put("noirForfait", "1-F");
		mapResultat.put("EXEMPT", "1-F");
		mapResultat.put("doubleForfait", "F-F");

		mapDepartages.put("Cumulatif", "Cu.");
		mapDepartages.put("Buchholz", "Tr.");
		mapDepartages.put("PerfElo", "Perf");
    }

}
