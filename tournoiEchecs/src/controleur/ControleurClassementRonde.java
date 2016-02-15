package controleur;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import application.Affichage;
import application.Main;
import vue.FenetreFileChooser;
import vue.ItemClassementRonde;
import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControleurClassementRonde implements Initializable{

	@FXML
	ListView<Partie> lv_classement;

	ObservableList<Partie> itemsPartie;

	@FXML
	Label lb_titre;

	@FXML
	Button bt_suiv;

	@FXML
	Button bt_prec;

	private int numRonde;
	private int numRondeMax;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bt_suiv.setDisable(true);
		itemsPartie=FXCollections.observableArrayList();

		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==-1){
			numRonde=ModeleTournoi.getTournoi().getNbRondes()-1;
		}
		else if(ModeleTournoi.getTournoi().getRondeActuelle().isApp()||(!ModeleTournoi.getTournoi().getRondeActuelle().isApp()&& !ModeleTournoi.getTournoi().getRonde(ModeleTournoi.getTournoi().getNumRondeActuelle()-1).isSaisie()))
				numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle()-1;
			else
				numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		numRondeMax=numRonde;
		if(numRonde==0)
			bt_prec.setDisable(true);
		chargeItems();
		lv_classement.setItems(itemsPartie);
		lv_classement.setCellFactory(lv -> new ItemClassementRonde());

	}

	private void chargeItems(){
		itemsPartie.clear();
		lb_titre.setText("Résultats de la ronde "+String.valueOf(numRonde+1));
		itemsPartie.addAll(ModeleTournoi.getTournoi().getPartieRonde(numRonde));
	/*	FXCollections.sort(itemsPartie, new Comparator<Partie>() {

			@Override
			public int compare(Partie p1, Partie p2) {

				return p2.compareTo(p1);
			}
		});*/
		for (Joueur j : ModeleTournoi.getTournoi().getListeJoueurs()) {
			if(j.getCouleurRonde(numRonde).equals("X")){
				Partie p = new Partie(j,null);
				p.setResultat("EXEMPT");
				p.setScorejoueurBlancPartie(j.getScore());
				itemsPartie.add(p);
			}
		}
		for(int i=0;i<itemsPartie.size();i++){
			itemsPartie.get(i).setClassement(i+1);
		}
	}

	@FXML
	public void rondePrecedante(){
		bt_suiv.setDisable(false);
		numRonde--;
		chargeItems();
		if(numRonde==0)
			bt_prec.setDisable(true);

	}

	@FXML
	public void rondeSuivante(){
		bt_prec.setDisable(false);
		numRonde++;
		chargeItems();
		if(numRonde==numRondeMax)
			bt_suiv.setDisable(true);
	}

	@FXML
	public void actionImprimer(Event e){
		File file = FenetreFileChooser.EnregistrerDir(Main.getPrimaryStage());
		if (file != null) {
			try {
				Affichage.chargerMapsGrilleAEtClassements();

				int nb = numRonde + 1;

				String str = file.getAbsolutePath() + "/ResultatsRonde" + nb + "_" + ModeleTournoi.getTournoi().getNom() + ".pdf";
				Document document = new Document();
		      	PdfWriter.getInstance(document, new FileOutputStream(str));
		      	document.open();
		      	// polices
		      	Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);

		      	// lignes
		      	Paragraph titre1 = new Paragraph(ModeleTournoi.getTournoi().getNom(), catFont);
		      	titre1.setAlignment(Element.ALIGN_CENTER);
		      	document.add(titre1);

		      	Paragraph titre2 = new Paragraph("Résultats de la ronde " + nb, catFont);
		      	titre2.setAlignment(Element.ALIGN_CENTER);
		      	document.add(titre2);

		      	document.add(new Paragraph(" "));
		      	document.add(new Paragraph(" "));

		      	//table
		      	PdfPTable table = new PdfPTable(8);

		      	float[] columnWidths = new float[] {13f, 10f, 55f, 20f, 13f, 55f, 20f, 10f};
	            table.setWidths(columnWidths);

		      	PdfPCell c1 = new PdfPCell(new Phrase("Ech"));
		        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c1);

		        PdfPCell c2 = new PdfPCell(new Phrase("Pts"));
		        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c2);

		        PdfPCell c3 = new PdfPCell(new Phrase("Blanc"));
		        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c3);

		        PdfPCell c4 = new PdfPCell(new Phrase(""));
		        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c4);

		        PdfPCell c5 = new PdfPCell(new Phrase("Res"));
		        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c5);

		        PdfPCell c6 = new PdfPCell(new Phrase("Noir"));
		        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c6);

		        PdfPCell c7 = new PdfPCell(new Phrase(""));
		        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c7);

		        PdfPCell c8 = new PdfPCell(new Phrase("Pts"));
		        c8.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c8);

		        table.setHeaderRows(1);

		        int i = 1;
		        for (Partie p : ModeleTournoi.getTournoi().getRonde(numRonde).getListePartie()) {
					table.addCell(Integer.toString(i));

					//Joueur Blanc
					float scorePrecBlanc=p.getScoreJoueurBlancPartie();
		        	if(p.getResultat().equals("blancGagne")){
		        		scorePrecBlanc+=-1;
		        	}
		        	if(p.getResultat().equals("partieNulle")){
		        		scorePrecBlanc+=-0.5;
		        	}
		        	table.addCell(Float.toString(scorePrecBlanc));
		        	table.addCell(p.getNomPrenomJoueurBlanc());
		        	table.addCell(Integer.toString(p.getJoueurBlanc().getElo()) + " " + Affichage.mapTypeElo.get(p.getJoueurBlanc().getTypeElo()));

		        	//Resultat
		        	table.addCell(Affichage.mapResultat.get(p.getResultat()));

		        	//Joueur Noir
		        	table.addCell(p.getNomPrenomJoueurNoir());
		        	table.addCell(Integer.toString(p.getJoueurNoir().getElo()) + " " + Affichage.mapTypeElo.get(p.getJoueurNoir().getTypeElo()));
		        	float scorePrecNoir=p.getScorejoueurNoirPartie();
		        	if(p.getResultat().equals("noirGagne")){
		        		scorePrecNoir+=-1;
		        	}
		        	if(p.getResultat().equals("partieNulle")){
		        		scorePrecNoir+=-0.5;
		        	}
		        	table.addCell(Float.toString(scorePrecNoir));

					i++;
				}

		        //Affichage exempt
		        for(Joueur j : ModeleTournoi.getTournoi().getListeJoueurs())
		      	{
		      		if(j.getCouleurRonde(numRonde).equals("X"))
		      		{
		      			table.addCell(String.valueOf(i));
		      			float scorePrec=j.getScore() - 1;
			        	table.addCell(Float.toString(scorePrec));
		      			table.addCell(j.getNomJoueur()+" "+j.getPrenomJoueur());
		      			table.addCell(j.getElo() + " " + Affichage.mapTypeElo.get(j.getTypeElo()));
		      			table.addCell("1-F");
		      			table.addCell("EXEMPT");
		      			table.addCell("");
		      			table.addCell("");
		      		}
		      	}

		        document.add(table);

		      	document.close();
		    }catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		}
	}
}
