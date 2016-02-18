package vue.pdf;

import java.io.FileOutputStream;

import metier.Joueur;
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

public class PdfListeParticipant extends Pdf{

	public static void creerPDF() {
		try {
		String str = "ListeJoueur_" + ModeleTournoi.getTournoi().getNom() + ".pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(str));
		document.open();
		// polices
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);

		// lignes
		Paragraph titre1 = new Paragraph(ModeleTournoi.getTournoi().getNom(), catFont);
		titre1.setAlignment(Element.ALIGN_CENTER);
		document.add(titre1);

		Paragraph titre2 = new Paragraph("Liste des participants", catFont);
		titre2.setAlignment(Element.ALIGN_CENTER);
		document.add(titre2);

		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));

		//table
		PdfPTable table = new PdfPTable(7);

		float[] columnWidths = new float[] {5f, 40f, 10f, 10f, 15f, 10f, 25f};
		table.setWidths(columnWidths);

		PdfPCell c1 = new PdfPCell(new Phrase("Nr"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("Nom"));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

		PdfPCell c3 = new PdfPCell(new Phrase("Elo"));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);

		PdfPCell c4 = new PdfPCell(new Phrase("Cat."));
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c4);

		PdfPCell c5 = new PdfPCell(new Phrase("Fede"));
		c5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c5);

		PdfPCell c6 = new PdfPCell(new Phrase("Ligue"));
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c6);

		PdfPCell c7 = new PdfPCell(new Phrase("Club"));
		c7.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c7);

		table.setHeaderRows(1);

		int i = 1;
		for (Joueur j : ModeleTournoi.getTournoi().getListeJoueurs()) {
			table.addCell(Integer.toString(i));
			table.addCell(Affichage.mapTitre.get(j.getTitre()) + " " + j.getNomJoueur() + " " + j.getPrenomJoueur());
			table.addCell(Integer.toString(j.getElo()) + " " + Affichage.mapTypeElo.get(j.getTypeElo()));
			table.addCell(Affichage.mapCategorie.get(j.getCategorie())+Affichage.mapSexe.get(j.getSexe()));
			table.addCell(j.getFederation());
			table.addCell(j.getLigue());
			table.addCell(j.getClub());
			i++;
		}

		document.add(table);

		document.close();
		afficherPdf(str);
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}
}
