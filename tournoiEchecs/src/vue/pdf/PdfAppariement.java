package vue.pdf;

import java.io.FileOutputStream;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfAppariement extends Pdf{
	public static void creerPDF(){
		try {
			int nbRondeActuelle = ModeleTournoi.getTournoi().getNumRondeActuelle()+1;
			String str = "AppariementRonde" + nbRondeActuelle	+"_"+
					ModeleTournoi.getTournoi().getNom() + ".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(str));
			document.open();

			// polices
			Font titreFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);

			// en-tete
			Paragraph nomTournoi = new Paragraph(ModeleTournoi.getTournoi().getNom(), titreFont);
			nomTournoi.setAlignment(Element.ALIGN_CENTER);
			document.add(nomTournoi);

			Paragraph numRonde = new Paragraph("Ronde numéro "+nbRondeActuelle, titreFont);
			numRonde.setAlignment(Element.ALIGN_CENTER);
			document.add(numRonde);

			Paragraph espace = new Paragraph(" ", titreFont);
			document.add(espace);

			//tables
			PdfPTable table = new PdfPTable(3);

			PdfPCell c1 = new PdfPCell(new Phrase("Table"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Joueur Blancs"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Joueur Noirs"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			table.setHeaderRows(1);

			//Affichage des appariements
			int i=1;
			for(Partie partie : ModeleTournoi.getTournoi().getPartieRondeActuelle())
			{
				table.addCell(String.valueOf(i));
				table.addCell(partie.getNomPrenomJoueurBlanc());
				table.addCell(partie.getNomPrenomJoueurNoir());

				i++;
			}

			//Affichage exempt
			for(Joueur j : ModeleTournoi.getTournoi().getListeJoueurs())
			{
				if(j.getCouleurRonde(ModeleTournoi.getTournoi().getNumRondeActuelle()).equals("X"))
				{
					table.addCell(String.valueOf(i));
					table.addCell(j.getNomJoueur()+" "+j.getPrenomJoueur());
					table.addCell("EXEMPT");
				}
			}

			float[] tailleColonne = new float[] {7f, 30f, 30f,};
			table.setWidths(tailleColonne);

			document.add(table);
			document.close();
			afficherPdf(str);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
