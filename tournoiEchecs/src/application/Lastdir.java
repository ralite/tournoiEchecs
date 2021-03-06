package application;

import java.io.File;
import java.io.IOException;

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

public class Lastdir {

	public static File lastdir=null;
	public static String lastDirFilePath = "src\\ressource\\config.xml";
	
	public static void saveLastdir(String savePath){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("configurations");
			doc.appendChild(rootElement);

			if(lastdir!= null) {
				Element lastDir = doc.createElement("lastdir");
				lastDir.appendChild(doc.createTextNode(lastdir.getAbsolutePath()));
				rootElement.appendChild(lastDir);
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

	public static void readLastdir(String filePath){
		try {
			File XMLFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();

			Element racine = doc.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();
			String path = null;

			for (int i = 0; i < nbRacineNoeuds; i++) {

				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Node node = racineNoeuds.item(i);

					if(node.getNodeName() == "lastdir"){
						path = node.getTextContent();
					}
				}
			}

			if(path!=null){
				File file = new File(path);
				lastdir = file;
			}

		} catch (NullPointerException e) {
			//e.printStackTrace();
		} catch (SAXException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ParserConfigurationException e) {
			//e.printStackTrace();
		}
	}
	
}
