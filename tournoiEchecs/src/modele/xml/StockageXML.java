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
import modele.ModeleDepartage;
import modele.ModeleJoueur;

public class StockageXML {

	public static String joueurFilePath = "src\\ressource\\saveJoueur.xml";

	public static void writeXMLTournoi(Tournoi tournoi,String savePath,int existe){
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
			
			StreamResult result;
			
			if(existe == 1){
				result = new StreamResult(new File(savePath));
			}else{
				result = new StreamResult(new File(savePath + "\\tournoi_" + tournoi.getNom() + "_" + tournoi.getLieu() + "_" + tournoi.getDateDeb().toString() + ".xml"));
			}
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

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			boolean b1 = false;
			boolean b2 = false;
			
			String nom = null;
			String lieu = null;
			LocalDate dateDeb = null;
			LocalDate dateFin = null;
			String arbitre = null;
			int nbRondes = 0;
			int cadenceJeu = 0;
			ArrayList<String> listNomDepartage = new ArrayList<String>();
			ArrayList<Departage> listDepartage = new ArrayList<Departage>();
			ArrayList<String> listNumJoueur = new ArrayList<String>();

			Element racine = doc.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {

				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Node node = racineNoeuds.item(i);

					if(node.getNodeName() == "nom"){
						nom = node.getTextContent();
					}
					if(node.getNodeName() == "lieu"){
						lieu = node.getTextContent();
					}
					if(node.getNodeName() == "datedebut"){
						dateDeb = LocalDate.parse(node.getTextContent(), formatter);
					}
					if(node.getNodeName() == "datefin"){
						dateFin = LocalDate.parse(node.getTextContent(), formatter);
					}
					if(node.getNodeName() == "arbitre"){
						arbitre = node.getTextContent();
					}
					if(node.getNodeName() == "nbrondes"){
						nbRondes = Integer.parseInt(node.getTextContent());
					}
					if(node.getNodeName() == "cadencejeu"){
						cadenceJeu = Integer.parseInt(node.getTextContent());
					}
					if(node.getNodeName() == "departage"){
						listNomDepartage.add(node.getTextContent());
						b1 = true;
					}
					if(node.getNodeName() == "joueur"){
						listNumJoueur.add(node.getTextContent());
						b2 = true;
					}
				}
			}
			
			Tournoi returnTournoi = new Tournoi(nom, lieu, dateDeb, dateFin, arbitre, nbRondes, cadenceJeu);
			
			if(b1){
				listDepartage = ModeleDepartage.factoryDepartage(listNomDepartage);
				for (Departage departage : listDepartage) {
					returnTournoi.AddDepartages(departage);
				}
			}
			if(b2){
				for (String numJoueur : listNumJoueur) {
					returnTournoi.AddJoueur(ModeleJoueur.rechercherJoueur(numJoueur));
				}
			}
			return returnTournoi;

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

			int indiceJoueur = 0;
			
			for(Joueur jou : listJoueur) {
				Element joueur = doc.createElement("joueur" + indiceJoueur);
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
				joueur.appendChild(dateNaissance);

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
				
				indiceJoueur++;
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

			ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
			
			Element racine = doc.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {

				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Node node = racineNoeuds.item(i);
					NodeList joueurNoeuds = node.getChildNodes();
					int nbJoueurNoeuds = joueurNoeuds.getLength();
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					
					String num = null;
					String nom = null;
					String prenom = null;
					String sexe = null;
					LocalDate dateNaissance = null;
					String titre = null;
					String ligue = null;
					int elo = 0;
					String typeElo = null;
					String federation = null;
					String categorie = null;
					String club = null;
					
					for (int j = 0; j < nbJoueurNoeuds; j++) {

						if (joueurNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE) {
							
							Node sousNode = joueurNoeuds.item(j);
							
							if(sousNode.getNodeName() == "num"){
								num = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "nom"){
								nom = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "prenom"){
								prenom = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "sexe"){
								sexe = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "datenaissance"){
								dateNaissance = LocalDate.parse(sousNode.getTextContent(), formatter);
							}
							if(sousNode.getNodeName() == "titre"){
								titre = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "ligue"){
								ligue = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "elo"){
								elo = Integer.parseInt(sousNode.getTextContent());
							}
							if(sousNode.getNodeName() == "typeelo"){
								typeElo = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "federation"){
								federation = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "categorie"){
								categorie = sousNode.getTextContent();
							}
							if(sousNode.getNodeName() == "club"){
								club = sousNode.getTextContent();
							}
						}
					}
					
					listJoueur.add(new Joueur(num,nom,prenom,sexe,dateNaissance,titre,ligue,elo,typeElo,federation,categorie,club));
				}
			}

			return listJoueur;

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
