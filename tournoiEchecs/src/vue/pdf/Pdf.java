package vue.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.itextpdf.text.Document;

public abstract class Pdf {
	
	public static void affiherPdf(String str){
      	Desktop desk = Desktop.getDesktop();
      	try {
			desk.open(new File(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
