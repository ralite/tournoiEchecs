package controleur;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import vue.ItemGrilleAmericaine;
import vue.ItemHeaderGA;
import vue.pdf.PdfGrilleAmericaine;
import metier.Joueur;
import modele.ModeleTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControleurGrilleAmericaine implements Initializable {

	@FXML
	ListView<String> lv_header;
	
	@FXML
	ListView<Joueur> lv_classement;

	@FXML
	Label lb_titre;

	@FXML
	Label lb_classement;

	ObservableList<Joueur> itemsJoueur;
	ObservableList<String> itemsHeader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1)
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		lb_titre.setText(ModeleTournoi.getTournoi().getNom());
		lb_classement.setText("Grille américaine après la ronde "+numRonde);
		
		itemsHeader=FXCollections.observableArrayList();
		itemsHeader.add("header");
		lv_header.setStyle("-fx-control-inner-background : grey; ");
		lv_header.setItems(itemsHeader);
		lv_header.setCellFactory(lvheader->new ItemHeaderGA());
		
		itemsJoueur=FXCollections.observableArrayList();
		itemsJoueur.addAll(ModeleTournoi.getTournoi().getListeJoueurs());
		lv_classement.setItems(itemsJoueur);
		lv_classement.setCellFactory(lv->new ItemGrilleAmericaine());
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
		PdfGrilleAmericaine.creerPDF(itemsJoueur);
	}
	
	@FXML
	public void actionQuitter(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

}
