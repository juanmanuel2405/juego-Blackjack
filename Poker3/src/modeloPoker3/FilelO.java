/*
 * Programacion Interactiva
 * Archivo: FileIO.java
 * Caso 3: Juego De Poker
  Autores:
  
    -Juan Manuel Cuellar R - juan.manuel.cuellar@correounivalle.edu.co
    -Codigo 1927968
    -Daniel Felipe Rosero -  daniel.zemanate@correounivalle.edu.co
    -Codigo 1924347
    
  Fecha creación: 2020-10-13
  Fecha última modificación: 2020-11-23
  Licencia: MIT 
 */
package modeloPoker3;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class FilelO.
 */
public class FilelO {
		
		/**
		 * Read image file.
		 *
		 * @param requestor the requestor
		 * @param fileName the file name
		 * @return the buffered image
		 */
		public static BufferedImage readImageFile(Object requestor, String fileName) {
			BufferedImage image = null;
			try {
				InputStream input = requestor.getClass().getResourceAsStream(fileName);
				image = ImageIO.read(input);					
			}catch(Exception e) {
				String message = "El archivo " + fileName + " No se pudo abrir.";
			    JOptionPane.showMessageDialog(null, message); 
			}
	       return image;
		}
}

