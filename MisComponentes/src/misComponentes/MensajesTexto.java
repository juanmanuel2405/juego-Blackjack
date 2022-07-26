package misComponentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MensajesTexto extends JLabel{
	
	public MensajesTexto(String mensaje, int tamano, Color colorFondo) {
		 
		this.setText(mensaje);
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,tamano);
		this.setFont(font);
		this.setBackground(colorFondo);
		this.setForeground(Color.white);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
	}
}
