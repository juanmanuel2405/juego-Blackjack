package misComponentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Titulos2 extends JLabel {

	
	public Titulos2(String texto, int tamano, Color colorLetra, Color colorFondo) {

			this.setText(texto);
			Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,tamano);
			this.setFont(font);
			this.setForeground(colorLetra);
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setOpaque(true);
			this.setBackground(colorFondo);
		
	}
}
	
