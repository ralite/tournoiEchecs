package vue;

import java.io.IOException;

import metier.Joueur;
import metier.Partie;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Window;

public class ItemAppariementFactory extends ListCell<Partie>{
	
    private final GridPane gridPane = new GridPane(); 
 
    private final Label joueurBlanc = new Label(); 
    private final Label joueurNoir = new Label(); 
    private final Label eloJoueurBlanc = new Label(); 
    private final Label eloJoueurNoir = new Label(); 
    
    
    private final AnchorPane content = new AnchorPane(); 

	public ItemAppariementFactory() {

		 		joueurBlanc.setStyle("-fx-font-weight: bold;"); 
		 		joueurNoir.setStyle("-fx-font-weight: bold;"); 

		        GridPane.setConstraints(joueurNoir, 1, 0); 
		        GridPane.setConstraints(eloJoueurNoir, 1, 1); 
		        GridPane.setConstraints(joueurBlanc, 2, 0); 
		        GridPane.setConstraints(eloJoueurBlanc, 2, 1); 
       
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
		        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
		        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
		        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
		        gridPane.setHgap(2); 
		        gridPane.setVgap(2); 
		        gridPane.getChildren().setAll(/*carIcon,*/ joueurNoir, joueurBlanc, eloJoueurNoir, eloJoueurBlanc); 
		        AnchorPane.setTopAnchor(gridPane, 0d); 
		        AnchorPane.setLeftAnchor(gridPane, 0d); 
		        AnchorPane.setBottomAnchor(gridPane, 0d); 
		        AnchorPane.setRightAnchor(gridPane, 0d); 
		        content.getChildren().add(gridPane); 
	        
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
            eloJoueurBlanc.setText(String.valueOf(item.getElojoueurBlanc()));
            eloJoueurNoir.setText(String.valueOf(item.getElojoueurNoir()));
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
    } 
	
}
