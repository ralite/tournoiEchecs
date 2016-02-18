package metier.departage;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import metier.Joueur;
import metier.Partie;
import metier.Tournoi;
import modele.ModeleTournoi;

public class TestDepartages {

	Tournoi t;
	Joueur j1;
	Joueur j2;
	Joueur j3;
	Joueur j4;
	Departage d1;
	Departage d2;
	Departage d3;
	
	@Before
	public void setUp() throws Exception {
		//test tournoi ronde 1
		
		testClassementRonde1();
				
	}

	private void testClassementRonde2() {
		//ronde 2
		
		t.getRonde(1).getListePartie().add(new Partie(j1,j3));
		t.getRonde(1).getListePartie().add(new Partie(j2,j4));
		t.getRonde(1).setApp(true);
		t.getRonde(1).getListePartie().get(0).setResultat("blancGagne");
		t.getRonde(1).getListePartie().get(0).setScore();
		t.getRonde(1).getListePartie().get(1).setResultat("partieNulle");
		t.getRonde(1).getListePartie().get(1).setScore();
		
	}
	
	private void testClassementRonde3() {
		//ronde 3
		
		t.getRonde(1).getListePartie().add(new Partie(j1,j4));
		t.getRonde(1).getListePartie().add(new Partie(j2,j3));
		t.getRonde(1).setApp(true);
		t.getRonde(1).getListePartie().get(0).setResultat("blancForfait");
		t.getRonde(1).getListePartie().get(0).setScore();
		t.getRonde(1).getListePartie().get(1).setResultat("noirForfait");
		t.getRonde(1).getListePartie().get(1).setScore();
		
	}

	private void testClassementRonde1() {
		t = new Tournoi("test", "ici", LocalDate.parse("2016-05-02"), LocalDate.parse("2016-05-10"), "moi", 5, "Blitz");
		ModeleTournoi.ajouterTournoi(t);
		
		j1 = new Joueur("A11111", "dfbgdfjgbgg", "fdbdfjb", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 1500, "FIDE", "Senegal", "Benjamin", "MTP",true);
		j2 = new Joueur("B11111", "fnb", "fdbdfgfjb", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 1700, "FIDE", "Senegal", "Benjamin", "MTP",true);
		j3 = new Joueur("C11111", "gfgfgffg", "egt", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 2000, "FIDE", "Senegal", "Benjamin", "MTP",true);
		j4 = new Joueur("D11111", "sd", "dfh", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 700, "FIDE", "Senegal", "Benjamin", "MTP",true);
		t.setRondeActuelle(1);
		t.AddJoueur(j1);
		t.AddJoueur(j2);
		t.AddJoueur(j3);
		t.AddJoueur(j4);
		d1 = new Buchholz();
		d2 = new Cumulatif();
		d3 = new PerfElo();
		t.AddDepartages(d1);
		t.AddDepartages(d2);
		t.AddDepartages(d3);
		//ronde 1
		t.getRonde(0).getListePartie().add(new Partie(j1,j2));
		t.getRonde(0).getListePartie().add(new Partie(j3,j4));
		t.getRonde(0).setApp(true);
		t.getRonde(0).getListePartie().get(0).setResultat("blancGagne");
		t.getRonde(0).getListePartie().get(0).setScore();
		t.getRonde(0).getListePartie().get(1).setResultat("noirGagne");
		t.getRonde(0).getListePartie().get(1).setScore();
	}
	
	//test ronde 1

	@Test
	public void testClassementRonde1J1SupérieurJ2() {
		boolean b = j1.getClassement() > j2.getClassement();
		assertFalse("1er assert",b);
	}

	@Test
	public void testClassementRonde1J3InferieurJ4() {
		boolean b = j3.getClassement() < j4.getClassement();
		assertFalse("2er assert",b);
	}
	
	@Test
	public void testClassementRonde1J3InferieurJ1() {
		boolean b = j3.getClassement() < j1.getClassement();
		assertFalse("2er assert",b);
	}
	
	@Test
	public void testDepartagejoueur2BuchholzRonde1() {
		t.calculerDepartagesJoueurs();
		boolean b = j2.getPointsDepartage("Buchholz") == 1.0;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartageJoueur1CumulatifRonde1() {
		t.calculerDepartagesJoueurs();
		boolean b = j1.getPointsDepartage("Cumulatif") == 1.0;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartageJoueur2PerfEloRonde1() {
		t.calculerDepartagesJoueurs();
		boolean b = j2.getPointsDepartage("PerfElo") == 1125;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartageJoueur1PerfEloRonde1() {
		t.calculerDepartagesJoueurs();
		boolean b = j1.getPointsDepartage("PerfElo") == 2075;
		assertTrue("3er assert",b);
	}
	
	//test ronde 2
	
	@Test
	public void testClassementRonde2J1SupérieurJ3() {
		testClassementRonde2();
		boolean b = j1.getClassement() > j3.getClassement();
		assertFalse("1er assert",b);
	}
	
	@Test
	public void testClassementRonde2J4SupérieurJ2() {
		boolean b = j4.getClassement() > j2.getClassement();
		assertFalse("1er assert",b);
	}
	
	@Test
	public void testClassementRonde2J3SupérieurJ2() {
		boolean b = j3.getClassement() > j2.getClassement();
		assertFalse("1er assert",b);
	}
	
	@Test
	public void testDepartagejoueur1BuchholzRonde2() {
		t.setRondeActuelle(2);
		testClassementRonde2();
		t.calculerDepartagesJoueurs();
		boolean b = j1.getPointsDepartage("Buchholz") == 0.5;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartagejoueur2BuchholzRonde2() {
		t.setRondeActuelle(2);
		testClassementRonde2();
		t.calculerDepartagesJoueurs();
		boolean b = j2.getPointsDepartage("Buchholz") == 3.5;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartagejoueur4CumulatifRonde2() {
		t.setRondeActuelle(2);
		testClassementRonde2();
		t.calculerDepartagesJoueurs();
		boolean b = j4.getPointsDepartage("Cumulatif") == 2.5;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartagejoueur1PerfEloRonde2() {
		t.setRondeActuelle(2);
		testClassementRonde2();
		t.calculerDepartagesJoueurs();
		boolean b = j1.getPointsDepartage("PerfElo") == 2225;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartagejoueur4PerfEloRonde3() {
		t.setRondeActuelle(3);
		testClassementRonde2();
		testClassementRonde3();
		t.calculerDepartagesJoueurs();
		boolean b = j4.getPointsDepartage("PerfElo") == 2038;
		assertTrue("3er assert",b);
	}
	
	@Test
	public void testDepartagejoueur3PerfEloRonde3() {
		t.setRondeActuelle(3);
		testClassementRonde2();
		testClassementRonde3();
		t.calculerDepartagesJoueurs();
		boolean b = j3.getPointsDepartage("PerfElo") == 725;
		assertTrue("3er assert",b);
	}
	
}
