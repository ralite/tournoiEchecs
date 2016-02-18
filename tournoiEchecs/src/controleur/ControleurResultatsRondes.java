package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import vue.ItemResultatsRonde;
import vue.pdf.PdfResultatsRondes;
import metier.Joueur;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControleurResultatsRondes implements Initializable{

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
		lv_classement.setCellFactory(lv -> new ItemResultatsRonde());

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
	public void actionImprimer(){
		PdfResultatsRondes.creerPDF(numRonde);
	}

	@FXML
	public void actionQuitter(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}


}
