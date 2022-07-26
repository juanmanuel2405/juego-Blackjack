package misComponentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class IconosTarjetas {
		
	
		public void init() {
			JLabel boton = new JLabel();
			
			for (int imagen= 1; imagen<=12; imagen++){
				boton.setIcon(new ImageIcon("src/imagenesTarjetas/"+imagen+".jpg"));
				
				  }
			
		}
}


