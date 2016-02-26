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

public class JoueurXML implements I_DALJoueur{

	public  String joueurFilePath = "src\\ressource\\saveJoueur.xml";

	public  void WriteXMLJoueur(ArrayList<Joueur> listJoueur){
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
				
				Element isDansTournoi = doc.createElement("isdanstournoi");
				isDansTournoi.appendChild(doc.createTextNode(String.valueOf(jou.isDansTournoi())));
				joueur.appendChild(isDansTournoi);
				
				indiceJoueur++;
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(joueurFilePath));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public  ArrayList<Joueur> readXMLJoueur(){
		try {
			File XMLFile = new File(joueurFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();

			ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
			
			Element racine = doc.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {
				
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
				boolean isDansTournoi = false;
				
				for (int j = 0; j < nbJoueurNoeuds; j++) {
					
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
					if(sousNode.getNodeName() == "isdanstournoi"){
						isDansTournoi = Boolean.parseBoolean(sousNode.getTextContent());
					}
				}
				
				listJoueur.add(new Joueur(num,nom,prenom,sexe,dateNaissance,titre,ligue,elo,typeElo,federation,categorie,club,isDansTournoi));
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
