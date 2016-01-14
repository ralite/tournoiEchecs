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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import metier.Joueur;
import metier.Partie;
import metier.Ronde;
import metier.Tournoi;
import metier.departage.Departage;
import modele.ModeleDepartage;
import modele.ModeleJoueur;

public class TournoiXML {

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
			Integer i2 = (Integer)tournoi.getCadenceJeu();
			cadenceJeu.appendChild(doc.createTextNode(i2.toString()));
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
			
			///////////
			
			int indiceRonde = 0;
			
			for(Ronde ron : tournoi.getListeRondes()) {
				Element ronde = doc.createElement("ronde" + indiceRonde);
				rootElement.appendChild(ronde);
				
				Element num = doc.createElement("num");
				Integer i3 = (Integer)ron.getNumeroRonde();
				num.appendChild(doc.createTextNode(i3.toString()));
				ronde.appendChild(num);
				
				int indicePartie = 0;
				
				for(Partie par : ron.getListePartie()) {
					Element partie = doc.createElement("partie" + indicePartie);
					ronde.appendChild(partie);
					
					Element joueurBlanc = doc.createElement("joueurBlanc");
					joueurBlanc.appendChild(doc.createTextNode(par.getNumLicenceJoueurBlanc()));
					partie.appendChild(joueurBlanc);
					
					Element joueurNoir = doc.createElement("joueurNoir");
					joueurNoir.appendChild(doc.createTextNode(par.getNumLicenceJoueurNoir()));
					partie.appendChild(joueurNoir);
					
					indicePartie++;
				}
				
				int indiceJoueurAbs = 0;
				
				for(Joueur jouabs : ron.getListeJoueurAbs()) {
					Element joueurAbs = doc.createElement("joueurAbs" + indiceJoueurAbs);
					joueurAbs.appendChild(doc.createTextNode(jouabs.getNumLicence()));
					ronde.appendChild(joueurAbs);
					
					indiceJoueurAbs++;
				}
				
				int indiceJoueurFor = 0;
				
				for(Joueur joufor : ron.getListeJoueurForfait()) {
					Element joueurFor = doc.createElement("joueurFor" + indiceJoueurFor);
					joueurFor.appendChild(doc.createTextNode(joufor.getNumLicence()));
					ronde.appendChild(joueurFor);
					
					indiceJoueurFor++;
				}
				
				indiceRonde++;
			}
			
			//////////

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result;
			result = new StreamResult(new File(savePath));
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
	
}
