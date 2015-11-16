package modele;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import metier.Departage;
import metier.Tournoi;

public class StockageXML {

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

			Element datedebut = doc.createElement("datedebut");
			datedebut.appendChild(doc.createTextNode(tournoi.getDateDeb().toString()));
			rootElement.appendChild(datedebut);

			Element datefin = doc.createElement("datefin");
			datefin.appendChild(doc.createTextNode(tournoi.getDateFin().toString()));
			rootElement.appendChild(datefin);

			Element arbitre = doc.createElement("arbitre");
			arbitre.appendChild(doc.createTextNode(tournoi.getArbitre()));
			rootElement.appendChild(arbitre);

			Element nbrondes = doc.createElement("nbrondes");
			Integer i = (Integer)tournoi.getNbRondes();
			nbrondes.appendChild(doc.createTextNode(i.toString()));
			rootElement.appendChild(nbrondes);

			/*Element departage = doc.createElement("departage");
			rootElement.appendChild(departage);

			for(Departage dep : tournoi.getListeDepartages()) {

			}*/

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(savePath + "/tournoi_" + tournoi.getLieu() + "_" + tournoi.getDateDeb().toString() + ".xml"));

			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static Tournoi readXMLTournoi(String filePath){
		try {
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			String Nom = doc.getElementsByTagName("nom").item(0).getTextContent();
			String Lieu = doc.getElementsByTagName("lieu").item(0).getTextContent();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate DateDeb = LocalDate.parse(doc.getElementsByTagName("datedebut").item(0).getTextContent(), formatter);
			LocalDate DateFin = LocalDate.parse(doc.getElementsByTagName("datefin").item(0).getTextContent(), formatter);
			String Arbitre = doc.getElementsByTagName("arbitre").item(0).getTextContent();
			int NbRondes = (Integer.parseInt(doc.getElementsByTagName("nbrondes").item(0).getTextContent()));

			/*NodeList departageList = doc.getElementsByTagName("departage");
			for (int i = 0; i < departageList.getLength(); i++) {

				Node node = departageList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;
				}
			}*/

			Tournoi returnTournoi = new Tournoi(Nom, Lieu, DateDeb, DateFin, Arbitre, NbRondes);

			return returnTournoi;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
