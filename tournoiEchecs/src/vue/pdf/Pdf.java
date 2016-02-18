package vue.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public abstract class Pdf {

	public static void afficherPdf(String str){
      	Desktop desk = Desktop.getDesktop();
      	try {
      		File f = new File(str);
			desk.open(f);
			f.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
