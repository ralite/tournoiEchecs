package modele;

import java.time.LocalDate;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Validation {


	public static boolean estEntierPos(TextField tx){
		int r;
		try{
			r=Integer.parseInt(tx.getText());
		}
		catch(Exception e){
			return false;
		}
		if(r>0){
			return true;
		}
		else{
			return false;
		}

	}

	public static boolean estVide(TextField tx) {
		if (tx.getText().trim().isEmpty()){
			tx.setStyle("-fx-control-inner-background : red; ");
			return true;
		}else {
			tx.setStyle("-fx-control-inner-background : white; ");
			return false;
		}
	}

	public static boolean estVide(DatePicker dp)
	{
		if (dp.getEditor().getText().isEmpty())
		{
			dp.setStyle("-fx-control-inner-background : red; ");
			return true;
		}else{
			dp.setStyle("-fx-control-inner-background : white; ");
			return false;
		}
	}

	public static boolean estChaine(TextField tx) {
		if(tx.getText().trim().matches("^[a-zA-Z]*$")){
			return true;
		}
		return false;
	}

	public static boolean verifDate(DatePicker d1, DatePicker d2) {
		if(d1.getValue()!=null && d2.getValue()!=null){
			LocalDate date1 = d1.getValue();
			LocalDate date2 = d2.getValue();
			if(date1.isAfter(date2)){
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	public static TextField verifLongueurTexte(TextField tf, int longeur){
		int longueurMax=longeur;
		if(tf.getText().length()>=longueurMax){
            tf.setText(tf.getText().substring(1, longueurMax));
		}
		return tf;
	}

	public static boolean verifNumLicence(String numLicence){
		if(numLicence.length()!=6)
			return false;
		if(!Character.isLetter(numLicence.charAt(0)))
			return false;
		try{
			Integer.parseInt(numLicence.substring(1, 5));
		}catch(NumberFormatException e)
		{
			return false;
		}

		return true;
	}


}