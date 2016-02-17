package vue.pdf;

import java.io.FileOutputStream;

import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import application.Affichage;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfResultatsRondes extends Pdf{

	public static void creerPDF(int numRonde) {

		try {
			Affichage.chargerMapsGrilleAEtClassements();

			int nb = numRonde + 1;

			String str = "ResultatsRonde" + nb + "_" + ModeleTournoi.getTournoi().getNom() + ".pdf";
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
			affiherPdf(str);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
