package modele;

import java.io.File;
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

			Element departage = doc.createElement("departage");
			rootElement.appendChild(departage);

			/*for(Departage dep : tournoi.getListeDepartages()) {

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

	public static void readXMLTournoi(Tournoi tournoi){

	}
}
