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
		t = new Tournoi("test", "ici", LocalDate.parse("2016-05-02"), LocalDate.parse("2016-05-10"), "moi", 5, "Blitz");
		ModeleTournoi.ajouterTournoi(t);
		t.setRondeActuelle(1);
		j1 = new Joueur("A11111", "dfbgdfjgbgg", "fdbdfjb", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 1500, "FIDE", "Senegal", "Benjamin", "MTP");
		j2 = new Joueur("B11111", "fnb", "fdbdfgfjb", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 1700, "FIDE", "Senegal", "Benjamin", "MTP");
		j3 = new Joueur("C11111", "gfgfgffg", "egt", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 2000, "FIDE", "Senegal", "Benjamin", "MTP");
		j4 = new Joueur("D11111", "sd", "dfh", "Masculin", LocalDate.parse("1994-10-27"), "Aucun titre", "CBV", 700, "FIDE", "Senegal", "Benjamin", "MTP");
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
		t.getRonde(0).getListePartie().add(new Partie(j1,j2));
		t.getRonde(0).getListePartie().add(new Partie(j3,j4));
		t.getRonde(0).setApp(true);
		t.getRonde(0).getListePartie().get(0).setResultat("blancGagne");
		t.getRonde(0).getListePartie().get(0).setScorejoueurBlancPartie(1);
		t.getRonde(0).getListePartie().get(0).setScorejoueurNoirPartie(0);
		t.getRonde(0).getListePartie().get(0).setScore();
		t.getRonde(0).getListePartie().get(1).setResultat("noirGagne");
		t.getRonde(0).getListePartie().get(1).setScorejoueurBlancPartie(0);
		t.getRonde(0).getListePartie().get(1).setScorejoueurNoirPartie(1);
		t.getRonde(0).getListePartie().get(1).setScore();
	}

	@Test
	public void testClassementJ1_J2() {
		boolean b = j1.getClassement() > j2.getClassement();
		assertFalse("1er assert",b);
	}

	@Test
	public void testClassementJ3_J4() {
		boolean b = j3.getClassement() < j4.getClassement();
		assertFalse("2er assert",b);
	}
	
	@Test
	public void testDepartage() {
		t.calculerDepartagesJoueurs();
		boolean b = j2.getPointsDepartage("Buchholz") == 1.0;
		System.out.println(j2.getPointsDepartage("Buchholz"));
		assertTrue("3er assert",b);
	}
}
