package vue.pdf;

import java.io.File;
import java.io.FileOutputStream;

import javafx.collections.ObservableList;
import metier.Joueur;
import modele.ModeleTournoi;
import vue.FenetreFileChooser;
import application.Affichage;
import application.Main;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfClassement extends Pdf{
	public static void creerPDF(ObservableList<Joueur> itemsJoueur) {
		try {
			int nbDepartages = ModeleTournoi.getTournoi().getListeDepartages().size();
			String str = "ClassementFinal" + "_"+ ModeleTournoi.getTournoi().getNom() + ".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(str));
			document.open();

			// polices
			Font titreFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);

			// en-tete
			Paragraph nomTournoi = new Paragraph(ModeleTournoi.getTournoi().getNom(), titreFont);
			nomTournoi.setAlignment(Element.ALIGN_CENTER);
			document.add(nomTournoi);

			Paragraph numRonde = new Paragraph("Classement apr�s la ronde "+ModeleTournoi.getTournoi().getNbRondes(), titreFont);
			numRonde.setAlignment(Element.ALIGN_CENTER);
			document.add(numRonde);

			Paragraph espace = new Paragraph(" ", titreFont);
			document.add(espace);

			//tables
			//gerer dynamiquement avec nbRonde
			PdfPTable table = new PdfPTable(12);

			PdfPCell c1 = new PdfPCell(new Phrase("PI"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			//titre
			c1 = new PdfPCell(new Phrase(" "));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Nom"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("ELO"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Cat."));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Fede"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Ligue"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Club"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Pts"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			for(int i=0;i<nbDepartages;i++)
			{
				c1 = new PdfPCell(new Phrase(Affichage.mapDepartages.get(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString())));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
			}

			table.setHeaderRows(1);

			int i=1;
			for (Joueur j : itemsJoueur) {
				//PI
				table.addCell(Integer.toString(i));
				i++;

				//Titre
				table.addCell(Affichage.mapTitre.get(j.getTitre()));

				//NomPrenom
				table.addCell(j.getNomJoueur()+" "+j.getPrenomJoueur());

				//ELO+Type
				table.addCell(Integer.toString(j.getElo()) + " " + Affichage.mapTypeElo.get(j.getTypeElo()));

				//Categorie+Sexe
				table.addCell(Affichage.mapCategorie.get(j.getCategorie()) + Affichage.mapSexe.get(j.getSexe()));

				//Federation
				table.addCell(j.getFederation());

				//Ligue
				table.addCell(j.getLigue());

				//Club
				table.addCell(j.getClub());

				//Pts
				table.addCell(Float.toString(j.getScore()));

				//Departages
				for(int k=0;k<nbDepartages;k++)
				{
					table.addCell(String.valueOf(j.getPointsDepartage(ModeleTournoi.getTournoi().getListeDepartages().get(k).toString())));
				}
			}

			float[] tailleColonne = new float[] {7f,4f,30f,19f,16f,15f,15f,25f,10f,10f,10f,19f};
			table.setWidths(tailleColonne);

			document.add(table);
			document.close();
			affiherPdf(str);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
