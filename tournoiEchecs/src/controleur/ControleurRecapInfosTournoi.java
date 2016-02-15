package controleur;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import application.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import metier.Joueur;
import metier.departage.Departage;
import modele.ModeleTournoi;
import vue.AjouterJoueurTournoi;
import vue.AppariementJoueur;
import vue.ClassementFinal;
import vue.ClassementRonde;
import vue.CreationTournoi;
import vue.FenetreFileChooser;
import vue.GrilleAmericaine;
import vue.SaisieResultat;

public class ControleurRecapInfosTournoi implements Initializable {

	@FXML
	Label lb_recapWarning;

	@FXML
	Label label_recapNom;

	@FXML
	Label label_recapLieu;

	@FXML
	Label label_recapArbitre;

	@FXML
	Label label_recapDateDeb;

	@FXML
	Label label_recapDateFin;

	@FXML
	Label label_recapNbRondes;

	@FXML
	Button button_recapModifierTournoi;

	@FXML
	ListView<Joueur> lv_recapJoueursInscrits;

	@FXML
	ListView<Departage> lv_recapDepartagesChoisis;

	@FXML
	Label lb_recapCadenceDeJeu;

	private LocalDate dateActuelle =  LocalDate.now();

	public void recapGererJoueurs(Event e){

			AjouterJoueurTournoi ajt=new AjouterJoueurTournoi(Main.getPrimaryStage());
			ajt.show();
			((Node)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	public void fermerRecap(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	public void recapModifierTournoi(Event e){
		if(dateActuelle.isAfter(ModeleTournoi.getTournoi().getDateDeb())) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erreur");
			alert.setHeaderText(null);
			alert.setContentText("Impossible de modifier un tournoi en cours !");
			alert.showAndWait();
		}
		else {
			CreationTournoi ct = new CreationTournoi(Main.getPrimaryStage());
			ct.show();
			((Node)e.getSource()).getScene().getWindow().hide();
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		label_recapNom.setText(ModeleTournoi.getTournoi().getNom());
		label_recapLieu.setText(ModeleTournoi.getTournoi().getLieu());
		label_recapArbitre.setText(ModeleTournoi.getTournoi().getArbitre());
		label_recapDateDeb.setText(formatter.format(ModeleTournoi.getTournoi().getDateDeb()));
		label_recapDateFin.setText(formatter.format(ModeleTournoi.getTournoi().getDateFin()));
		label_recapNbRondes.setText(String.valueOf(ModeleTournoi.getTournoi().getNbRondes()));
		lb_recapCadenceDeJeu.setText(String.valueOf(ModeleTournoi.getTournoi().getCadenceJeu()));
		lv_recapJoueursInscrits.setItems(ModeleTournoi.getTournoi().getListeJoueurs());
		lv_recapDepartagesChoisis.setItems(ModeleTournoi.getTournoi().getListeDepartages());
	}

	@FXML
	public void apparierJoueurs(){
		if(ModeleTournoi.getTournoi().getListeJoueurs().size()<2){
			AfficherAlerte("Nombre de joueurs insuffisant");

		}
		else if(ModeleTournoi.getTournoi().getNumRondeActuelle()==-1){
			AfficherAlerte("Tournoi Fini !");

		}
		else{
			if(!ModeleTournoi.getTournoi().getRondeActuelle().isApp()){
				AfficherAlerte("Veuillez préalablement saisir les résultats !");
			}
			else{
				AppariementJoueur app = new AppariementJoueur(Main.getPrimaryStage());
				app.show();
			}
		}
	}

	@FXML
	public void saisirResultat(){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==-1 || !ModeleTournoi.getTournoi().getRondeActuelle().isSaisie()){
			AfficherAlerte("Veuillez préalablement apparier les joueurs !");
		}
		else{
			SaisieResultat app = new SaisieResultat(Main.getPrimaryStage());
			app.show();
		}
	}
	
	@FXML
	public void classementRondes(){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==0){
			AfficherAlerte("Aucune ronde terminée");
		}
		else{
			ClassementRonde cl = new ClassementRonde(Main.getPrimaryStage());
			cl.show();
		}
	}
	
	@FXML
	public void classementFinal(){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==0){
			AfficherAlerte("Aucune ronde terminée");
		}
		else{
			ClassementFinal cl = new ClassementFinal(Main.getPrimaryStage());
			cl.show();
		}
	}
	
	@FXML
	public void grilleAmericaine(){
		if(ModeleTournoi.getTournoi().getNumRondeActuelle()==0){
			AfficherAlerte("Aucune ronde terminée");
		}
		else{
			GrilleAmericaine ga = new GrilleAmericaine(Main.getPrimaryStage());
			ga.show();
		}
	}
	
	private void AfficherAlerte(String s) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Erreur");
		alert.setContentText(s);
		alert.showAndWait();
	}

	@FXML
	public void actionImprimer(Event e){
		File file = FenetreFileChooser.EnregistrerDir(Main.getPrimaryStage());
		if (file != null) {
			try {
				String str = file.getAbsolutePath() + "/ListeJoueur_" + ModeleTournoi.getTournoi().getNom() + ".pdf";
				Document document = new Document();
		      	PdfWriter.getInstance(document, new FileOutputStream(str));
		      	document.open();
		      	// polices
		      	Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
		      	
		      	// lignes
		      	Paragraph titre1 = new Paragraph(ModeleTournoi.getTournoi().getNom(), catFont);
		      	titre1.setAlignment(Element.ALIGN_CENTER);
		      	document.add(titre1);
		      	
		      	Paragraph titre2 = new Paragraph("Liste des participants", catFont);
		      	titre2.setAlignment(Element.ALIGN_CENTER);
		      	document.add(titre2);
		        
		        
		        document.newPage();
		      	document.close();
		    }catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		}
	}
	
}
