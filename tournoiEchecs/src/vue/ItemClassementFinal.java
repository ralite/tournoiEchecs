package vue;

import metier.Joueur;
import application.Affichage;
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

public class ItemClassementFinal extends ListCell<Joueur>{

	private final GridPane gridPane = new GridPane();

	private final Label PI = new Label();
	private final Label titre = new Label();
    private final Label joueur = new Label();
    private final Label elo = new Label();
    private final Label categorie = new Label();
    private final Label federation = new Label();
    private final Label ligue = new Label();
    private final Label club = new Label();
    private final Label score = new Label();
    private final Label cumulatif = new Label();
    private final Label trs = new Label();
    private final Label perfs = new Label();

    private final AnchorPane content = new AnchorPane();


	public ItemClassementFinal(){

	        GridPane.setConstraints(PI, 0, 0);
	        GridPane.setConstraints(titre, 1, 0);
	        GridPane.setConstraints(joueur, 2, 0);
	        GridPane.setConstraints(elo, 3, 0);
	        GridPane.setConstraints(categorie, 4, 0);
	        GridPane.setConstraints(federation, 5, 0);
	        GridPane.setConstraints(ligue, 6, 0);
	        GridPane.setConstraints(club, 7, 0);
	        GridPane.setConstraints(score, 8, 0);
	        GridPane.setConstraints(cumulatif, 9, 0);
	        GridPane.setConstraints(trs, 10, 0);
	        GridPane.setConstraints(perfs, 11, 0);

	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(5, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(200, Region.USE_COMPUTED_SIZE, 200, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(50, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(100, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(100, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(50, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(200, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.RIGHT, true));


	        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));

	        gridPane.setHgap(2);
	        gridPane.setVgap(8);
	        gridPane.getChildren().setAll(PI,titre,joueur, elo, categorie,federation,ligue,club,score,cumulatif,trs,perfs);
	        AnchorPane.setTopAnchor(gridPane, 0d);
	        AnchorPane.setLeftAnchor(gridPane, 0d);
	        AnchorPane.setBottomAnchor(gridPane, 0d);
	        AnchorPane.setRightAnchor(gridPane, 0d);
	        content.getChildren().add(gridPane);

	        Affichage.chargerMapsGrilleAEtClassements();
	}

	@Override
    protected void updateItem(Joueur item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {
        	PI.setText(String.valueOf(item.getClassement()));
        	titre.setText(Affichage.mapTitre.get(item.getTitre()));
        	joueur.setText(item.getNomJoueur()+" "+item.getPrenomJoueur());
        	elo.setText(String.valueOf(item.getElo())+Affichage.mapTypeElo.get(item.getTypeElo()));
        	categorie.setText(Affichage.mapCategorie.get(item.getCategorie())+Affichage.mapSexe.get(item.getSexe()));
        	federation.setText(item.getFederation());
        	ligue.setText(item.getLigue());
        	club.setText(item.getClub());
        	score.setText(String.valueOf(item.getScore()));
        	cumulatif.setText(String.valueOf(item.getPointsDepartage("Cumulatif")));
        	trs.setText(String.valueOf(item.getPointsDepartage("Buchholz")));
        	perfs.setText(String.valueOf((int)item.getPointsDepartage("PerfElo")));

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }



}
