package controleur;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Comparator;
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
import vue.ItemClassementFinal;
import vue.pdf.PdfClassement;
import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class ControleurClassementFinal implements Initializable {

	@FXML
	ListView<Joueur> lv_classement;

	@FXML
	Label lb_titre;

	@FXML
	Label lb_classement;

	ObservableList<Joueur> itemsJoueur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1)
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		lb_titre.setText(ModeleTournoi.getTournoi().getNom());
		lb_classement.setText("Classement après la ronde "+numRonde);
		itemsJoueur=FXCollections.observableArrayList();
		itemsJoueur.addAll(ModeleTournoi.getTournoi().getListeJoueurs());
		lv_classement.setItems(itemsJoueur);
		lv_classement.setCellFactory(lv->new ItemClassementFinal());
		ModeleTournoi.getTournoi().calculerDepartagesJoueurs();
		FXCollections.sort(itemsJoueur, new Comparator<Joueur>() {

			@Override
			public int compare(Joueur j1, Joueur j2) {
				int res=0;
				if(j2.getScore()!=j1.getScore()){
					return Float.compare(j2.getScore(),j1.getScore());
				}
				else {
					for (int i = 0; i < ModeleTournoi.getTournoi().getListeDepartages().size(); i++) {
						res=Float.compare(j2.getPointsDepartage(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString()),j1.getPointsDepartage(ModeleTournoi.getTournoi().getListeDepartages().get(i).toString()));
						if(res==0){
							i++;
						}
						else return res;
					}
				}
				return res;
			}
		});
		for(int i=0;i<itemsJoueur.size();i++){
			itemsJoueur.get(i).setClassement(i+1);
		}

	}

	@FXML
	public void actionImprimer(Event e)
	{
		PdfClassement.creerPDF(itemsJoueur);
	}

}
