package vue;

import java.util.HashMap;

import javax.rmi.CORBA.Tie;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
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
    private final Label eloJoueurBlanc = new Label();
    private final Label eloJoueurNoir = new Label();
    private RadioButton blancGagne = new RadioButton();
    private final Label lb_blancGagne = new Label();
    private RadioButton noirGagne = new RadioButton();
    private final Label lb_noirGagne = new Label();
    private RadioButton blancForfait = new RadioButton();
    private final Label lb_blancForfait = new Label();
    private RadioButton noirForfait = new RadioButton();
    private final Label lb_noirForfait = new Label();
    private RadioButton partieNull = new RadioButton();
    private final Label lb_partieNull = new Label();
    private RadioButton doubleForfait = new RadioButton();
    private final Label lb_doubleForfait = new Label();

    private final AnchorPane content = new AnchorPane();

	public ItemSaisieResultat() {

				lb_blancForfait.setText("Blanc Forfait");
				lb_blancGagne.setText("Blanc Gagne");
				lb_doubleForfait.setText("Double Forfait");
				lb_noirForfait.setText("Noir Forfait");
				lb_noirGagne.setText("Noir Gagne");
				lb_partieNull.setText("Partie Null");

		 		joueurBlanc.setStyle("-fx-font-weight: bold;");
		 		joueurNoir.setStyle("-fx-font-weight: bold;");

		        GridPane.setConstraints(joueurBlanc, 1, 0);
		        GridPane.setConstraints(eloJoueurBlanc, 1, 1);
		        GridPane.setConstraints(joueurNoir, 2, 0);
		        GridPane.setConstraints(eloJoueurNoir, 2, 1);
		        GridPane.setConstraints(blancGagne, 4, 0);
		        GridPane.setConstraints(lb_blancGagne, 3, 0);
		        GridPane.setConstraints(noirGagne, 4, 1);
		        GridPane.setConstraints(lb_noirGagne, 3, 1);
		        GridPane.setConstraints(blancForfait, 6, 0);
		        GridPane.setConstraints(lb_blancForfait, 5, 0);
		        GridPane.setConstraints(noirForfait, 6, 1);
		        GridPane.setConstraints(lb_noirForfait, 5, 1);
		        GridPane.setConstraints(partieNull, 8, 0);
		        GridPane.setConstraints(lb_partieNull, 7, 0);
		        GridPane.setConstraints(doubleForfait, 8, 1);
		        GridPane.setConstraints(lb_doubleForfait, 7, 1);

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
		        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
		        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
		        gridPane.setHgap(3);
		        gridPane.setVgap(8);
		        gridPane.getChildren().setAll(joueurBlanc,joueurNoir, eloJoueurBlanc, eloJoueurNoir,blancGagne,noirGagne,blancForfait,noirForfait,partieNull,doubleForfait
		        		,lb_blancForfait,lb_blancGagne,lb_doubleForfait,lb_noirForfait,lb_noirGagne,lb_partieNull);
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

        listenerRadioButton(blancForfait,item);
 		listenerRadioButton(blancGagne,item);
 		listenerRadioButton(noirForfait,item);
 		listenerRadioButton(noirGagne,item);
 		listenerRadioButton(partieNull,item);
 		listenerRadioButton(doubleForfait,item);

        if (!empty && item != null) {
            joueurBlanc.setText(item.getNomPrenomJoueurBlanc());
            joueurNoir.setText(item.getNomPrenomJoueurNoir());
            if(item.getScoreJoueurBlanc()!=-1){
            	eloJoueurBlanc.setText(String.valueOf(item.getScoreJoueurBlanc()));
            }
            else{
            	eloJoueurBlanc.setText("inconnu");
            }
            if(item.getScoreJoueurNoir()!=-1){
            eloJoueurNoir.setText(String.valueOf(item.getScoreJoueurNoir()));
            }
            else{
            	eloJoueurNoir.setText("inconnu");
            }

            InitialisationRadioButton(item);


	        setGraphic(content);
	        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

    }

	private void InitialisationRadioButton(Partie item) {
		String gagnant=item.getResultat();
		if(gagnant!=null){
			switch (gagnant) {
			case "blancGagne":
				blancGagne.setSelected(true);
				break;
			case "noirGagne":
				noirGagne.setSelected(true);
				break;
			case "blancForfait":
				blancForfait.setSelected(true);
				break;
			case "noirForfait":
				noirForfait.setSelected(true);
				break;
			case "partieNulle":
				partieNull.setSelected(true);
				break;
			case "doubleForfait":
				doubleForfait.setSelected(true);
				break;

			}

		}

	}

	private void choixDuJoueurGagnant(Partie item) {
		if(item!=null){
			if(blancForfait.isSelected()){
				item.joueurBlancForfait();
			}else if(noirGagne.isSelected()){
				item.joueurNoirGagne();
			}else if(noirForfait.isSelected()){
				item.joueurNoirForfait();
			}else if(blancGagne.isSelected()){
				item.joueurBlancGagne();
			}else if(partieNull.isSelected()){
				item.partieNulle();
			}else if(doubleForfait.isSelected()){
				item.doubleForfait();
			}else{
				item.resutatNonSaisi();
			}
		}
	}

	private void deselectionnerToutRadioButton(RadioButton rb) {
		if(rb!=blancForfait){
			blancForfait.setSelected(false);
		}
		if(rb!=blancGagne){
			blancGagne.setSelected(false);
		}
		if(rb!=noirForfait){
			noirForfait.setSelected(false);
		}
		if(rb!=noirGagne){
			noirGagne.setSelected(false);
		}
		if(rb!=partieNull){
			partieNull.setSelected(false);
		}
		if(rb!=doubleForfait){
			doubleForfait.setSelected(false);
		}
	}

	private void listenerRadioButton(RadioButton rb,Partie partie) {
		rb.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if (isNowSelected)
		        	deselectionnerToutRadioButton(rb);
	        	choixDuJoueurGagnant(partie);
		    }
		});
	}


}
