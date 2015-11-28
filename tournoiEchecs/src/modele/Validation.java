package modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import metier.departage.Departage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Validation {

	private static LocalDate dateActuelle = LocalDate.now();
	
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
		if (dp.getEditor().getText().trim().isEmpty())
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
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}
		else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
		
	}
	
	public static boolean estDate(DatePicker dp) {
		SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY");
		     try {
		          format.parse(dp.getValue().toString());
		          return true;
		     }
		     catch(Exception e){
		          return false;
		     }
	}

	public static boolean nomcomposé(TextField tx) {
		if(tx.getText().trim().matches("^[a-zA-Z]*[ ]?[a-zA-Z]*$")){
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}
		else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
		
	}

	
	public static boolean verifDate(DatePicker d1, DatePicker d2) {	
		if(d1==null || d2 ==null || !estDate(d1) || !estDate(d2)){
			d1.setStyle("-fx-control-inner-background : red; ");
			d2.setStyle("-fx-control-inner-background : red; ");
			return false;
		}else {
			if(!d1.getValue().isAfter(d2.getValue()) && (d1.getValue().isEqual(dateActuelle) || d1.getValue().isAfter(dateActuelle)) ){
				d1.setStyle("-fx-control-inner-background : white; ");
				d2.setStyle("-fx-control-inner-background : white; ");
				return true;				
			}else {
				d1.setStyle("-fx-control-inner-background : red; ");
				d2.setStyle("-fx-control-inner-background : red; ");
				return false;
			}
		}
		
		
	}


	public static void verifLongueurTexte(TextField tf, int longeur){
		int longueurMax=longeur;
		if(tf.getText().length()>=longueurMax){
            tf.setText(tf.getText().substring(1, longueurMax));
		}
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

	public static boolean estVide(ListView<Departage> lv) {
		if(lv.getItems().isEmpty()){
			lv.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
		else {
			lv.setStyle("-fx-control-inner-background : white; ");
			return false;
		}
	}

	public static boolean estChiffre(TextField tx) {
		if(tx.getText().trim().matches("^[0-9]*$")){
			return true;
		}
		else{
			return false;
		}
	}


}