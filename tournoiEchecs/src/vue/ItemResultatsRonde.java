package vue;



import controleur.ControleurResultatsRondes;
import application.Affichage;
import metier.Partie;
import modele.ModeleTournoi;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

public class ItemResultatsRonde extends ListCell<Partie> {

	private final GridPane gridPane = new GridPane();

	private final Label ech = new Label();
	private final Label scoreBlanc = new Label();
    private final Label joueurBlanc = new Label();
    private final Label eloBlanc = new Label();
    private final Label resultat = new Label();
    private final Label joueurNoir = new Label();
    private final Label eloNoir = new Label();
    private final Label scoreNoir = new Label();

    private final AnchorPane content = new AnchorPane();

	public ItemResultatsRonde() {

		        GridPane.setConstraints(ech, 0, 0);
		        GridPane.setConstraints(scoreBlanc, 1, 0);
		        GridPane.setConstraints(joueurBlanc, 2, 0);
		        GridPane.setConstraints(eloBlanc, 3, 0);
		        GridPane.setConstraints(resultat, 4, 0);
		        GridPane.setConstraints(joueurNoir, 5, 0);
		        GridPane.setConstraints(eloNoir, 6, 0);
		        GridPane.setConstraints(scoreNoir, 7, 0);

		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(200, Region.USE_COMPUTED_SIZE, 200, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(50, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(100, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(200, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(50, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.RIGHT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.RIGHT, true));

		        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));

		        gridPane.setHgap(2);
		        gridPane.setVgap(8);
		        gridPane.getChildren().setAll(ech,joueurBlanc,joueurNoir, scoreBlanc, scoreNoir,resultat,eloBlanc,eloNoir);
		        AnchorPane.setTopAnchor(gridPane, 0d);
		        AnchorPane.setLeftAnchor(gridPane, 0d);
		        AnchorPane.setBottomAnchor(gridPane, 0d);
		        AnchorPane.setRightAnchor(gridPane, 0d);
		        content.getChildren().add(gridPane);

		        Affichage.chargerMapsGrilleAEtClassements();

	}

	@Override
    protected void updateItem(Partie item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {
        	//calcul du score precedent la partie du joueur blanc
        	float scorePrecBlanc=item.getScoreJoueurBlancPartie();
        	if(item.getResultat().equals("blancGagne")||item.getResultat().equals("noirForfait")){
        		scorePrecBlanc+=-1;
        	}
        	if(item.getResultat().equals("partieNulle")){
        		scorePrecBlanc+=-0.5;
        	}
        	ech.setText(String.valueOf(item.getClassement())+"\t");
        	scoreBlanc.setText(String.valueOf(scorePrecBlanc));
            joueurBlanc.setText(item.getNomPrenomJoueurBlanc());
            eloBlanc.setText(String.valueOf(item.getJoueurBlanc().getElo())+" "+Affichage.mapTypeElo.get(item.getJoueurBlanc().getTypeElo()));
            resultat.setText(Affichage.mapResultat.get(item.getResultat()));

	        if(item.getJoueurNoir()!=null){
	        	//calcul du score precedent la partie du joueur noir
	        	float scorePrecNoir=item.getScorejoueurNoirPartie();
	        	if(item.getResultat().equals("noirGagne")||item.getResultat().equals("blancForfait")){
	        		scorePrecNoir+=-1;
	        	}
	        	if(item.getResultat().equals("partieNulle")){
	        		scorePrecNoir+=-0.5;
	        	}
	            joueurNoir.setText(item.getNomPrenomJoueurNoir());
	            scoreNoir.setText(String.valueOf(scorePrecNoir));
	            eloNoir.setText(String.valueOf(item.getJoueurNoir().getElo())+" "+Affichage.mapTypeElo.get(item.getJoueurNoir().getTypeElo())+"\t");
            }
	        else{
	        	joueurNoir.setText(item.getResultat());
	        }
            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }




}
