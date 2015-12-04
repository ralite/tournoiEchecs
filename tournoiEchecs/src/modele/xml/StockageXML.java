package modele.xml;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import metier.Joueur;
import metier.Tournoi;
import metier.departage.Departage;

public class StockageXML {

	public static String joueurFilePath = "saveJoueur.xml";

	public static void writeXMLTournoi(Tournoi tournoi,String savePath){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("tournoi");
			doc.appendChild(rootElement);

			Element nom = doc.createElement("nom");
			nom.appendChild(doc.createTextNode(tournoi.getNom()));
			rootElement.appendChild(nom);

			Element lieu = doc.createElement("lieu");
			lieu.appendChild(doc.createTextNode(tournoi.getLieu()));
			rootElement.appendChild(lieu);

			Element dateDebut = doc.createElement("datedebut");
			dateDebut.appendChild(doc.createTextNode(tournoi.getDateDeb().toString()));
			rootElement.appendChild(dateDebut);

			Element dateFin = doc.createElement("datefin");
			dateFin.appendChild(doc.createTextNode(tournoi.getDateFin().toString()));
			rootElement.appendChild(dateFin);

			Element arbitre = doc.createElement("arbitre");
			arbitre.appendChild(doc.createTextNode(tournoi.getArbitre()));
			rootElement.appendChild(arbitre);

			Element nbRondes = doc.createElement("nbrondes");
			Integer i = (Integer)tournoi.getNbRondes();
			nbRondes.appendChild(doc.createTextNode(i.toString()));
			rootElement.appendChild(nbRondes);

			Element cadenceJeu = doc.createElement("cadencejeu");
			Integer j = (Integer)tournoi.getCadenceJeu();
			cadenceJeu.appendChild(doc.createTextNode(j.toString()));
			rootElement.appendChild(cadenceJeu);

			for(Departage dep : tournoi.getListeDepartages()) {
				Element departage = doc.createElement("departage");
				departage.appendChild(doc.createTextNode(dep.toString()));
				rootElement.appendChild(departage);
			}

			for(Joueur jou : tournoi.getListeJoueurs()) {
				Element joueur = doc.createElement("joueur");
				joueur.appendChild(doc.createTextNode(jou.getNumLicence()));
				rootElement.appendChild(joueur);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(savePath + "\\tournoi_" + tournoi.getNom() + "_" + tournoi.getLieu() + "_" + tournoi.getDateDeb().toString() + ".xml"));

			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static Tournoi readXMLTournoi(String filePath){
		try {
			File XMLFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();

			// code lecture fichier XML

			//Tournoi returnTournoi = new Tournoi(nom, lieu, dateDeb, dateFin, arbitre, nbRondes, cadenceJeu);

			//return returnTournoi;
			return null;

		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void WriteXMLJoueur(String savePath,ArrayList<Joueur> listJoueur){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("joueurs");
			doc.appendChild(rootElement);

			for(Joueur jou : listJoueur) {
				Element joueur = doc.createElement("joueur");
				rootElement.appendChild(joueur);

				Element num = doc.createElement("num");
				num.appendChild(doc.createTextNode(jou.getNumLicence()));
				joueur.appendChild(num);

				Element nom = doc.createElement("nom");
				nom.appendChild(doc.createTextNode(jou.getNomJoueur()));
				joueur.appendChild(nom);

				Element prenom = doc.createElement("prenom");
				prenom.appendChild(doc.createTextNode(jou.getPrenomJoueur()));
				joueur.appendChild(prenom);

				Element sexe = doc.createElement("sexe");
				sexe.appendChild(doc.createTextNode(jou.getSexe()));
				joueur.appendChild(sexe);

				Element dateNaissance = doc.createElement("datenaissance");
				dateNaissance.appendChild(doc.createTextNode(jou.getDateNaissance().toString()));
				rootElement.appendChild(dateNaissance);

				Element titre = doc.createElement("titre");
				titre.appendChild(doc.createTextNode(jou.getTitre()));
				joueur.appendChild(titre);

				Element ligue = doc.createElement("ligue");
				ligue.appendChild(doc.createTextNode(jou.getLigue()));
				joueur.appendChild(ligue);

				Element elo = doc.createElement("elo");
				Integer i = (Integer)jou.getElo();
				elo.appendChild(doc.createTextNode(i.toString()));
				joueur.appendChild(elo);

				Element typeElo = doc.createElement("typeelo");
				typeElo.appendChild(doc.createTextNode(jou.getTypeElo()));
				joueur.appendChild(typeElo);

				Element federation = doc.createElement("federation");
				federation.appendChild(doc.createTextNode(jou.getFederation()));
				joueur.appendChild(federation);

				Element categorie = doc.createElement("categorie");
				categorie.appendChild(doc.createTextNode(jou.getCategorie()));
				joueur.appendChild(categorie);

				Element club = doc.createElement("club");
				club.appendChild(doc.createTextNode(jou.getClub()));
				joueur.appendChild(club);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(savePath));

			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static ArrayList<Joueur> readXMLJoueur(String filePath){
		try {
			File XMLFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();

			// code lecture fichier XML

			return null;

		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
