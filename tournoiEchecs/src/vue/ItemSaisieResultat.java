package vue;

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
import metier.Partie;

public class ItemSaisieResultat extends ListCell<Partie> {
    private final GridPane gridPane = new GridPane();

    private final Label joueurBlanc = new Label();
    private final Label joueurNoir = new Label();
    private final Label lb_resultat = new Label();

    private final AnchorPane content = new AnchorPane();

	public ItemSaisieResultat() {

		 		joueurBlanc.setStyle("-fx-font-weight: bold;");
		 		joueurNoir.setStyle("-fx-font-weight: bold;");

		        GridPane.setConstraints(joueurBlanc, 1, 0);
		        GridPane.setConstraints(joueurNoir, 3, 0);
		        GridPane.setConstraints(lb_resultat, 2, 0);
		       

		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints());
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints());
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getColumnConstraints().add(new ColumnConstraints());
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
		        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
		       
		        gridPane.setHgap(3);
		        gridPane.setVgap(8);
		        gridPane.getChildren().setAll(joueurBlanc,joueurNoir,lb_resultat);
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
            joueurBlanc.setText(item.getNomPrenomJoueurBlanc());
            joueurNoir.setText(item.getNomPrenomJoueurNoir());
            lb_resultat.setText(Affichage.mapResultat.get(item.getResultat()));
	        setGraphic(content);
	        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
