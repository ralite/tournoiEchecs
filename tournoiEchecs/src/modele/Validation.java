package modele;

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
			return true;
		}else {
			return false;	
		}
	}
	
	public static boolean estChaine(TextField tx) {
		if(tx.getText().trim().matches("^[a-zA-Z]*$")){
			return true;
		}
		return false;
	}
	
}