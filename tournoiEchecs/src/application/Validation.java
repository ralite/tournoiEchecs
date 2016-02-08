package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import metier.departage.Departage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Validation {

	public static boolean estEntierPos(TextField tx){
		int resultat;
		try{
			resultat=Integer.parseInt(tx.getText());
		}catch(Exception e){
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
		if(resultat>0){
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
	}

	public static boolean estVide(TextField tx) {
		if (tx.getText().trim().isEmpty()){
			tx.setStyle("-fx-control-inner-background : red; ");
			return true;
		}else{
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
		if(tx.getText().trim().matches("^[^0-9]*$")){
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}

	}

	public static boolean estChaineChiffree(TextField tx) {
		if(tx.getText().trim().matches("^[a-zA-Z]+\\s*[0-9]*$")){
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}

	}

	public static boolean estDate(DatePicker dp) {
		SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY");
		try {
			format.parse(dp.getValue().toString());
			dp.setStyle("-fx-control-inner-background : white; ");
			return true;
		}catch(Exception e){
			dp.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
	}

	public static boolean estNomCompose(TextField tx) {
		if(tx.getText().trim().matches("^[^0-9]*[-]?[^0-9]*$")){
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}

	}


	public static boolean verifDate(DatePicker d1, DatePicker d2) {
		if(d1==null || d2 ==null || !estDate(d1) || !estDate(d2)){
			d1.setStyle("-fx-control-inner-background : red; ");
			d2.setStyle("-fx-control-inner-background : red; ");
			return false;
		}else{
			if(!d1.getValue().isAfter(d2.getValue())){
				d1.setStyle("-fx-control-inner-background : white; ");
				d2.setStyle("-fx-control-inner-background : white; ");
				return true;
			}else{
				d1.setStyle("-fx-control-inner-background : red; ");
				d2.setStyle("-fx-control-inner-background : red; ");
				return false;
			}
		}


	}


	public static void verifLongueurTexte(TextField tf, int longeur){
		int longueurMax=longeur;
		if(tf.getText().length()>longueurMax){
            tf.deleteText(longueurMax, tf.getText().length());
		}
	}

	public static boolean verifNumLicence(TextField tf){
		if(tf.getText().length()!=6)
		{
			tf.setStyle("-fx-control-inner-background : red; ");
			return false;
		}else if(!Character.isLetter(tf.getText().charAt(0))){
			tf.setStyle("-fx-control-inner-background : red; ");
			return false;
		}

		try{
			Integer.parseInt(tf.getText().substring(1, 5));
		}catch(NumberFormatException e){
			tf.setStyle("-fx-control-inner-background : red; ");
			return false;
		}

		tf.setStyle("-fx-control-inner-background : white; ");
		return true;
	}

	public static boolean estVide(ListView<Departage> lv) {
		if(lv.getItems().isEmpty()){
			lv.setStyle("-fx-control-inner-background : red; ");
			return false;
		}else {
			lv.setStyle("-fx-control-inner-background : white; ");
			return false;
		}
	}

	public static boolean estChiffre(TextField tx) {
		if(tx.getText().trim().matches("^[0-9]*$")){
			tx.setStyle("-fx-control-inner-background : white; ");
			return true;
		}else{
			tx.setStyle("-fx-control-inner-background : red; ");
			return false;
		}
	}

	public static boolean recupValeursDate(DatePicker dp){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try{
			LocalDate d = LocalDate.parse(dp.getEditor().getText().toString(),formatter);
			dp.setValue(d);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
