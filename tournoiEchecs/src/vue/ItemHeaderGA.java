package vue;

import java.util.ArrayList;

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
import modele.ModeleTournoi;

public class ItemHeaderGA extends ListCell<String>{
	
	private final GridPane gridPane = new GridPane();

	private final Label PI = new Label();
	private final Label titre = new Label();
    private final Label joueur = new Label();
    private final Label elo = new Label();
    private final Label categorie = new Label();
    private final Label federation = new Label();
    private final Label ligue = new Label();
    private final ArrayList<Label> rondes = new ArrayList<>();
    private final Label score = new Label();
    private final Label dep1 = new Label();
    private final Label dep2 = new Label();
    private final Label dep3 = new Label();

    private final AnchorPane content = new AnchorPane();
    
    private int numRonde;

    public ItemHeaderGA(){
		numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
		if(numRonde==-1){
			numRonde=ModeleTournoi.getTournoi().getNbRondes();
		}

        GridPane.setConstraints(PI, 0, 0);
        GridPane.setConstraints(titre, 1, 0);
        GridPane.setConstraints(joueur, 2, 0);
        GridPane.setConstraints(elo, 3, 0);
        GridPane.setConstraints(categorie, 4, 0);
        GridPane.setConstraints(federation, 5, 0);
        GridPane.setConstraints(ligue, 6, 0);
        int i=7;
        for (;i<ModeleTournoi.getTournoi().getNbRondes()+7;i++) {
        	rondes.add(new Label());
        	GridPane.setConstraints(rondes.get(i-7), i, 0);
		}
        GridPane.setConstraints(score, i, 0);
        GridPane.setConstraints(dep1, i+1, 0);
        GridPane.setConstraints(dep2, i+2, 0);
        GridPane.setConstraints(dep3,i+3, 0);

        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(5, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200, Region.USE_COMPUTED_SIZE, 200, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(40, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(40, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(40, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        for (int j=0;j<ModeleTournoi.getTournoi().getNbRondes();j++) {
        	gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		}
        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.RIGHT, true));


        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));

        gridPane.setHgap(2);
        gridPane.setVgap(8);
        gridPane.getChildren().setAll(PI,titre,joueur, elo, categorie,federation,ligue,score,dep1,dep2,dep3);
        for (int j =0;j<numRonde;j++) {
        	gridPane.getChildren().add(rondes.get(j));
		}
        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);
        content.getChildren().add(gridPane);

        Affichage.chargerMapsGrilleAEtClassements();
    }
    
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        
        PI.setText("PI");
        titre.setText(" ");
        joueur.setText("Nom");
        elo.setText("ELO");
        categorie.setText("Cat.");
        federation.setText("Fede");
        ligue.setText("Ligue");
        for (int i=0;i<numRonde;i++) {
        	rondes.get(i).setText("R" + String.valueOf((i+1)));
		}
        score.setText("Pts");
        dep1.setText(Affichage.mapDepartages.get(ModeleTournoi.getTournoi().getListeDepartages().get(0).toString()));
        dep2.setText(Affichage.mapDepartages.get(ModeleTournoi.getTournoi().getListeDepartages().get(1).toString()));
        dep3.setText(Affichage.mapDepartages.get(ModeleTournoi.getTournoi().getListeDepartages().get(2).toString()));
        
        setText(null);
        setGraphic(content);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
}
