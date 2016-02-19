package vue.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import application.Affichage;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;

public class PdfGrilleAmericaine extends Pdf {

	public static void creerPDF(ObservableList<Joueur> itemsJoueur) {
		try {
			int i;
			int nbRonde = ModeleTournoi.getTournoi().getNbRondes();
			int numRondeActuelle=ModeleTournoi.getTournoi().getNumRondeActuelle();
			if(numRondeActuelle==-1)
				numRondeActuelle=ModeleTournoi.getTournoi().getNbRondes();
			String str = "GrilleAmericaineRonde" + numRondeActuelle + "_" + ModeleTournoi.getTournoi().getNom() + ".pdf";
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(str));
			document.open();

			// polices
			Font titreFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);

			// en-tete
			Paragraph nomTournoi = new Paragraph(ModeleTournoi.getTournoi().getNom(), titreFont);
			nomTournoi.setAlignment(Element.ALIGN_CENTER);
			document.add(nomTournoi);

			Paragraph numRonde = new Paragraph("Grille américaine après la ronde "+numRondeActuelle, titreFont);
			numRonde.setAlignment(Element.ALIGN_CENTER);
			document.add(numRonde);

			Paragraph espace = new Paragraph(" ", titreFont);
			document.add(espace);

			//tables
			//gerer dynamiquement avec nbRonde
			PdfPTable table = new PdfPTable(11+nbRonde);

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

			c1 = new PdfPCell(new Phrase("Cat"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Fede"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Ligue"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			for(i=0;i<ModeleTournoi.getTournoi().getNbRondes();i++)
			{
				c1 = new PdfPCell(new Phrase("R"+String.valueOf(i+1)));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
			}

			c1 = new PdfPCell(new Phrase("Pts"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			for(i=0;i<3;i++)
			{
				c1 = new PdfPCell(new Phrase(Affichage.mapDepartages.get(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString())));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
			}

			table.setHeaderRows(1);

			int pi=1;
			for (Joueur j : itemsJoueur) {
				//PI
				table.addCell(Integer.toString(pi));
				pi++;

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

				//Rondes
				for(i=0;i<nbRonde;i++)
				{
					for(Partie partie :ModeleTournoi.getTournoi().getRonde(i).getListePartie()){
		        		if(partie.joueurEstDansPartie(j)){
		        			table.addCell(partie.getAffichageGa(j,i));
		        		}else{//joueurs absent ou forfait ou exempt
		        			if(j.getCouleurRonde(i).equals("X"))
		        				table.addCell("EXE");
		        			else
		        				table.addCell(" ");
		        		}
		        	}
				}

				//Pts
				table.addCell(Float.toString(j.getScore()));

				//Departages
				for(i=0;i<3;i++)
				{
					//afficher le réel du departage si c'est la perfELO
					if(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString().equalsIgnoreCase("perfElo"))
						table.addCell(String.valueOf((int)j.getPointsDepartage(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString())));
					else
						table.addCell(String.valueOf(j.getPointsDepartage(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString())));
				}
			}

			float[] tailleColonne = new float[11+nbRonde] ;
			tailleColonne[0]=7f;
			tailleColonne[1]=4f;
			tailleColonne[2]=30f;
			tailleColonne[3]=19f;
			tailleColonne[4]=16f;
			tailleColonne[5]=20f;
			tailleColonne[6]=15f;//valeurs fixes
			for(i=7;i<7+nbRonde;i++)
			{
				tailleColonne[i] = 10f;//rondes
			}
			tailleColonne[i]=10f;//pts
			i++;
			int fin = i+3;
			for(;i<fin;i++)
			{
				tailleColonne[i] = 10f;//departages
			}
			table.setWidths(tailleColonne);

			document.add(table);
			document.close();
			afficherPdf(str);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
