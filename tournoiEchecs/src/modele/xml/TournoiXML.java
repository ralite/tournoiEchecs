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

			Element rondeActuelle = doc.createElement("rondeActuelle");
			rondeActuelle.appendChild(doc.createTextNode(String.valueOf(tournoi.getNumRondeActuelle())));
			rootElement.appendChild(rondeActuelle);

			for(Departage dep : tournoi.getListeDepartages()) {
				Element departage = doc.createElement("departage");
				departage.appendChild(doc.createTextNode(dep.toString()));
				rootElement.appendChild(departage);
			}

			if(!tournoi.getListeJoueurs().isEmpty()){

				Element joueurs = doc.createElement("joueurs");
				rootElement.appendChild(joueurs);

				int indiceJoueur = 0;

				for(Joueur jou : tournoi.getListeJoueurs()) {
					Element joueur = doc.createElement("joueur" + indiceJoueur);
					joueurs.appendChild(joueur);

					Element numLicence = doc.createElement("numLicence");
					numLicence.appendChild(doc.createTextNode(jou.getNumLicence()));
					joueur.appendChild(numLicence);

					if(!jou.getCouleur().isEmpty()){
						Element couleurJoueur = doc.createElement("couleurJoueur");
						couleurJoueur.appendChild(doc.createTextNode(jou.getCouleur()));
						joueur.appendChild(couleurJoueur);
					}

					Element scoreJoueur = doc.createElement("scoreJoueur");
					scoreJoueur.appendChild(doc.createTextNode(String.valueOf(jou.getScore())));
					joueur.appendChild(scoreJoueur);

					indiceJoueur++;
				}
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

					if(ron.getJoueurExempt()!=null){
						Element joueurExempt = doc.createElement("joueurExempt");
						joueurExempt.appendChild(doc.createTextNode(String.valueOf(ron.getNumJoueurExempt())));
						ronde.appendChild(joueurExempt);
						
						Element scoreJoueurExempt = doc.createElement("scoreJoueurExempt");
						scoreJoueurExempt.appendChild(doc.createTextNode(String.valueOf(ron.getScoreJoueurExemptRonde())));
						ronde.appendChild(scoreJoueurExempt);
					}

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

							if(!par.getResultat().isEmpty()){
								Element resultat = doc.createElement("resultat");
								resultat.appendChild(doc.createTextNode(par.getResultat()));
								partie.appendChild(resultat);
							}

							Element scoreBlanc = doc.createElement("scoreBlanc");
							scoreBlanc.appendChild(doc.createTextNode(String.valueOf(par.getScoreJoueurBlancPartie())));
							partie.appendChild(scoreBlanc);

							Element scoreNoir = doc.createElement("scoreNoir");
							scoreNoir.appendChild(doc.createTextNode(String.valueOf(par.getScorejoueurNoirPartie())));
							partie.appendChild(scoreNoir);

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
			StreamResult result = new StreamResult(new File(savePath));
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
			int rondeActuelle=0;
			ArrayList<String> listNomDepartage = new ArrayList<String>();
			ArrayList<Departage> listDepartage = new ArrayList<Departage>();
			ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
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
				if(node.getNodeName() == "rondeActuelle"){
					rondeActuelle = Integer.parseInt(node.getTextContent());
				}
				if(node.getNodeName() == "departage"){
					listNomDepartage.add(node.getTextContent());
					b1 = true;
				}
				if(node.getNodeName() == "joueurs"){
					b2 = true;
					NodeList joueursNoeuds = node.getChildNodes();
					int nbjoueursNoeuds = joueursNoeuds.getLength();

					for (int i1 = 0; i1 < nbjoueursNoeuds; i1++) {

						Node nodeJoueur = joueursNoeuds.item(i1);
						NodeList joueurNoeuds = nodeJoueur.getChildNodes();
						int nbJoueurNoeuds = joueurNoeuds.getLength();

						String numLicence = "";
						String couleurJoueur = "";
						float scoreJoueur = 0.0f;

						for (int i11 = 0; i11 < nbJoueurNoeuds; i11++) {

							Node sousNodeJoueur = joueurNoeuds.item(i11);

							if(sousNodeJoueur.getNodeName() == "numLicence"){
								numLicence = sousNodeJoueur.getTextContent();
							}

							if(sousNodeJoueur.getNodeName() == "couleurJoueur"){
								couleurJoueur = sousNodeJoueur.getTextContent();
							}

							if(sousNodeJoueur.getNodeName() == "scoreJoueur"){
								scoreJoueur = Float.parseFloat(sousNodeJoueur.getTextContent());
							}
						}
						Joueur j = ModeleJoueur.rechercherJoueur(numLicence);
						if(j!=null){
							j.setScore(scoreJoueur);
							j.setCouleur(couleurJoueur);
							listJoueur.add(j);
						}
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
						String numJoueurExempt=null;
						float scoreJoueurExemptRonde=0.0f;
						ArrayList<String> listJoueurAbs = new ArrayList<String>();
						ArrayList<String> listJoueurFor = new ArrayList<String>();
						ArrayList<Partie> listPartie = new ArrayList<Partie>();

						for (int i3 = 0; i3 < nbRondeNoeuds; i3++) {

							Node sousNodeRonde = rondeNoeuds.item(i3);

							if(sousNodeRonde.getNodeName() == "num"){
								numRonde = Integer.parseInt(sousNodeRonde.getTextContent());
							}

							if(sousNodeRonde.getNodeName() == "isapp"){
								isapp = Boolean.parseBoolean(sousNodeRonde.getTextContent());
							}

							if(sousNodeRonde.getNodeName() == "isres"){
								isres = Boolean.parseBoolean(sousNodeRonde.getTextContent());
							}

							if(sousNodeRonde.getNodeName() == "joueurExempt"){
								numJoueurExempt = String.valueOf(sousNodeRonde.getTextContent());
							}

							if(sousNodeRonde.getNodeName() == "scoreJoueurExempt"){
								scoreJoueurExemptRonde = Float.parseFloat(sousNodeRonde.getTextContent());
							}

							if(sousNodeRonde.getNodeName() == "parties"){

								NodeList partiesNoeuds = sousNodeRonde.getChildNodes();
								int nbpartiesNoeuds = partiesNoeuds.getLength();

								for (int i4 = 0; i4 < nbpartiesNoeuds; i4++) {

									Node nodePartie = partiesNoeuds.item(i4);
									NodeList partieNoeuds = nodePartie.getChildNodes();
									int nbPartieNoeuds = partieNoeuds.getLength();

									String joueurBlanc = "";
									String joueurNoir = "";
									String resultat = "";
									float scoreBlanc=0.0f;;
									float scoreNoir=0.0f;;

									for (int i5 = 0; i5 < nbPartieNoeuds; i5++) {

										Node sousNodePartie = partieNoeuds.item(i5);

										if(sousNodePartie.getNodeName() == "joueurBlanc"){
											joueurBlanc = sousNodePartie.getTextContent();
										}

										if(sousNodePartie.getNodeName() == "joueurNoir"){
											joueurNoir = sousNodePartie.getTextContent();
										}

										if(sousNodePartie.getNodeName() == "resultat"){
											resultat = sousNodePartie.getTextContent();
											if(resultat.isEmpty()){
												resultat="";
											}
										}

										if(sousNodePartie.getNodeName() == "scoreBlanc"){
											scoreBlanc = Float.parseFloat(sousNodePartie.getTextContent());
										}

										if(sousNodePartie.getNodeName() == "scoreNoir"){
											scoreNoir = Float.parseFloat(sousNodePartie.getTextContent());
										}
									}

									Partie partie = new Partie(ModeleJoueur.rechercherJoueur(joueurBlanc), ModeleJoueur.rechercherJoueur(joueurNoir));
									partie.setResultat(resultat);
									partie.setScorejoueurBlancPartie(scoreBlanc);
									partie.setScorejoueurNoirPartie(scoreNoir);
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

						Joueur j = ModeleJoueur.rechercherJoueur(numJoueurExempt);
						if(j!=null){
							r.setJoueurExempt(j);
							r.setScoreJoueurExemptRonde(scoreJoueurExemptRonde);
						}
						

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
			returnTournoi.setRondeActuelle(rondeActuelle);
			if(b1){
				listDepartage = ModeleDepartage.factoryDepartage(listNomDepartage);
				for (Departage departage : listDepartage) {
					returnTournoi.AddDepartages(departage);
				}
			}
			if(b2){
				for (Joueur joueur : listJoueur) {
					returnTournoi.AddJoueur(joueur);
				}
			}
			if(b3){
				for (Ronde ronde1 : listRonde) { 
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setListePartie(ronde1.getListePartie());
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setListeJoueurAbs(ronde1.getListeJoueurAbs());
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setListeJoueurForfait(ronde1.getListeJoueurForfait());
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setApp(ronde1.isApp());		
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setSaisie(ronde1.isSaisie());				
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setJoueurExempt(ronde1.getJoueurExempt());
					returnTournoi.getListeRondes().get(ronde1.getNumeroRonde()-1).setScoreJoueurExemptRonde(ronde1.getScoreJoueurExemptRonde());
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
