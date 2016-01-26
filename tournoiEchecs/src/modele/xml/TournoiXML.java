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
			cadenceJeu.appendChild(doc.createTextNode(tournoi.getCadenceJeu()));
			rootElement.appendChild(cadenceJeu);

			for(Departage dep : tournoi.getListeDepartages()) {
				Element departage = doc.createElement("departage");
				departage.appendChild(doc.createTextNode(dep.toString()));
				rootElement.appendChild(departage);
			}

			for(Joueur jou : tournoi.getListeJoueurs()) {
				Element joueur = doc.createElement("joueur");
				
				Element numLicence = doc.createElement("numLicence");
				numLicence.appendChild(doc.createTextNode(jou.getNumLicence()));
				joueur.appendChild(numLicence);
				
				Element couleurJoueur = doc.createElement("couleurJoueur");
				couleurJoueur.appendChild(doc.createTextNode(jou.getCouleur()));
				joueur.appendChild(couleurJoueur);
				
				Element scoreJoueur = doc.createElement("scoreJoueur");
				scoreJoueur.appendChild(doc.createTextNode(String.valueOf(jou.getScore())));
				joueur.appendChild(scoreJoueur);
				
				rootElement.appendChild(joueur);
			}
			
			if(!tournoi.getListeRondes().isEmpty()){
			
				Element rondes = doc.createElement("rondes");
				rootElement.appendChild(rondes);
				
				int indiceRonde = 0;
				
				for(Ronde ron : tournoi.getListeRondes()) {
					Element ronde = doc.createElement("ronde" + indiceRonde);
					rondes.appendChild(ronde);
					
					Element num = doc.createElement("num");
					Integer i3 = (Integer)ron.getNumeroRonde();
					num.appendChild(doc.createTextNode(i3.toString()));
					ronde.appendChild(num);
					
					Element isapp = doc.createElement("isapp");
					isapp.appendChild(doc.createTextNode(String.valueOf(ron.isApp())));
					ronde.appendChild(isapp);
					
					Element isres = doc.createElement("isres");
					isres.appendChild(doc.createTextNode(String.valueOf(ron.isSaisie())));
					ronde.appendChild(isres);
					
					if(!ron.getListePartie().isEmpty()){
					
						Element parties = doc.createElement("parties");
						ronde.appendChild(parties);
						
						int indicePartie = 0;
						
						for(Partie par : ron.getListePartie()) {
							
							Element partie = doc.createElement("partie" + indicePartie);
							parties.appendChild(partie);
							
							Element joueurBlanc = doc.createElement("joueurBlanc");
							joueurBlanc.appendChild(doc.createTextNode(par.getNumLicenceJoueurBlanc()));
							partie.appendChild(joueurBlanc);
							
							Element joueurNoir = doc.createElement("joueurNoir");
							joueurNoir.appendChild(doc.createTextNode(par.getNumLicenceJoueurNoir()));
							partie.appendChild(joueurNoir);
							
							indicePartie++;
						}
					}
					
					for(Joueur jouabs : ron.getListeJoueurAbs()) {
						Element joueurAbs = doc.createElement("joueurAbs");
						joueurAbs.appendChild(doc.createTextNode(jouabs.getNumLicence()));
						ronde.appendChild(joueurAbs);
					}
					
					for(Joueur joufor : ron.getListeJoueurForfait()) {
						Element joueurFor = doc.createElement("joueurFor");
						joueurFor.appendChild(doc.createTextNode(joufor.getNumLicence()));
						ronde.appendChild(joueurFor);
					}
					
					indiceRonde++;
				}
			}
			
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
			boolean b3 = false;
			
			String nom = null;
			String lieu = null;
			LocalDate dateDeb = null;
			LocalDate dateFin = null;
			String arbitre = null;
			int nbRondes = 0;
			String cadenceJeu = null;
			ArrayList<String> listNomDepartage = new ArrayList<String>();
			ArrayList<Departage> listDepartage = new ArrayList<Departage>();
			ArrayList<Joueur> listNumJoueur = new ArrayList<Joueur>();
			ArrayList<Ronde> listRonde = new ArrayList<Ronde>();

			Element racine = doc.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {

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
					cadenceJeu = node.getTextContent();
				}
				if(node.getNodeName() == "departage"){
					listNomDepartage.add(node.getTextContent());
					b1 = true;
				}
				if(node.getNodeName() == "joueur"){
					NodeList joueursNoeuds = node.getChildNodes();
					int nbjoueursNoeuds = joueursNoeuds.getLength();
					System.out.println(nbjoueursNoeuds);
					for (int i1 = 0; i1 < nbjoueursNoeuds; i1++) {
						
						
						String numLicence= new String();
						String couleurJoueur= new String();
						Float scoreJoueur=(float) 0;
						
							
						Node sousNodeJoueur = joueursNoeuds.item(i1);
								
						if(sousNodeJoueur.getNodeName() == "numLicence"){
							numLicence = sousNodeJoueur.getTextContent();
						}
						
						if(sousNodeJoueur.getNodeName() == "couleurJoueur"){
							couleurJoueur = sousNodeJoueur.getTextContent();
						}
							
						if(sousNodeJoueur.getNodeName() == "scoreJoueur"){
							scoreJoueur = Float.parseFloat(sousNodeJoueur.getTextContent());
						}

						Joueur j = ModeleJoueur.rechercherJoueur(numLicence);
						System.out.println(numLicence);
						if(j!=null){
							j.setScore(scoreJoueur);
							if(couleurJoueur.equalsIgnoreCase(""))
								couleurJoueur=null;
							j.setCouleur(couleurJoueur);
							listNumJoueur.add(j);
						}
						b2 = true;
					}
				}
				if(node.getNodeName() == "rondes"){
					b3 = true;
					NodeList rondesNoeuds = node.getChildNodes();
					int nbRondesNoeuds = rondesNoeuds.getLength();
					
					for (int i2 = 0; i2 < nbRondesNoeuds; i2++) {
						
						Node nodeRonde = rondesNoeuds.item(i2);
						NodeList rondeNoeuds = nodeRonde.getChildNodes();
						int nbRondeNoeuds = rondeNoeuds.getLength();
						
						int numRonde = 0;
						boolean isapp = false;
						boolean isres = false;
						ArrayList<String> listJoueurAbs = new ArrayList<String>();
						ArrayList<String> listJoueurFor = new ArrayList<String>();
						ArrayList<Partie> listPartie = new ArrayList<Partie>();
						
						for (int i3 = 0; i3 < nbRondeNoeuds; i3++) {
							
							Node sousNodeRonde = rondeNoeuds.item(i3);
								
							if(sousNodeRonde.getNodeName() == "num"){
								numRonde = Integer.parseInt(sousNodeRonde.getTextContent());
							}
							
							if(sousNodeRonde.getNodeName() == "isapp"){
								isapp = Boolean.getBoolean(sousNodeRonde.getTextContent());
							}
							
							if(sousNodeRonde.getNodeName() == "isres"){
								isres = Boolean.getBoolean(sousNodeRonde.getTextContent());
							}
							
							if(sousNodeRonde.getNodeName() == "parties"){
								
								NodeList partiesNoeuds = sousNodeRonde.getChildNodes();
								int nbpartiesNoeuds = partiesNoeuds.getLength();
								
								for (int i4 = 0; i4 < nbpartiesNoeuds; i4++) {
									
									Node nodePartie = partiesNoeuds.item(i4);
									NodeList partieNoeuds = nodePartie.getChildNodes();
									int nbPartieNoeuds = partieNoeuds.getLength();
									
									String joueurBlanc = null;
									String joueurNoir = null;
									
									for (int i5 = 0; i5 < nbPartieNoeuds; i5++) {
										
										Node sousNodePartie = partieNoeuds.item(i5);
										
										if(sousNodePartie.getNodeName() == "joueurBlanc"){
											joueurBlanc = sousNodePartie.getTextContent();
										}
										
										if(sousNodePartie.getNodeName() == "joueurNoir"){
											joueurNoir = sousNodePartie.getTextContent();
										}
									}
									
									Partie partie = new Partie(ModeleJoueur.rechercherJoueur(joueurBlanc), ModeleJoueur.rechercherJoueur(joueurNoir));
									listPartie.add(partie);
								}
							}
							
							if(sousNodeRonde.getNodeName() == "joueurAbs"){
								listJoueurAbs.add(sousNodeRonde.getTextContent());
							}
							
							if(sousNodeRonde.getNodeName() == "joueurFor"){
								listJoueurFor.add(sousNodeRonde.getTextContent());
							}
						}
						
						Ronde r = new Ronde(numRonde);
						r.setApp(isapp);
						r.setSaisie(isres);
						
						for (String numJoueur : listJoueurAbs) {
							r.getListeJoueurAbs().add(ModeleJoueur.rechercherJoueur(numJoueur));
						}
						for (String numJoueur : listJoueurFor) {
							r.getListeJoueurForfait().add(ModeleJoueur.rechercherJoueur(numJoueur));
						}
						for(Partie partie : listPartie){
							r.getListePartie().add(partie);
						}
						
						listRonde.add(r);
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
				for (Joueur joueur : listNumJoueur) {
					returnTournoi.AddJoueur(joueur);
				}
			}
			if(b3){
				for (Ronde ronde1 : listRonde) { 
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setListePartie(ronde1.getListePartie());
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setListeJoueurAbs(ronde1.getListeJoueurAbs());
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setListeJoueurForfait(ronde1.getListeJoueurForfait());
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
