/*
 * Programacion Interactiva
 * Archivo: Carta.java
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

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class Carta.
 */
public class Carta extends JLabel {
	
	/** The valor. */
	private String valor;
	
	/** The palo. */
	private String palo;
	
	/** The imagen. */
	private BufferedImage imagen;
	
	/** The valor numerico. */
	private int valorNumerico;

	/**
	 * Instantiates a new carta.
	 *
	 * @param valor the valor
	 * @param palo the palo
	 */
	public Carta(String valor, String palo) {
		this.valor = valor;
		this.palo = palo;

		switch(valor) {
		case "J": valorNumerico=11;break;
		case "Q": valorNumerico=12;break;
		case "K": valorNumerico=13;break;
		case "As": valorNumerico=14;break;
		default: valorNumerico = Integer.parseInt(valor);break;
		} 
	}

	/**
	 * Gets the valor numerico.
	 *
	 * @return the valor numerico
	 */
	public int getValorNumerico() {
		return valorNumerico;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Gets the palo.
	 *
	 * @return the palo
	 */
	public String getPalo() {
		return palo;
	}

	/**
	 * Sets the palo.
	 *
	 * @param palo the new palo
	 */
	public void setPalo(String palo) {
		this.palo = palo;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return valor+palo;
	}

	/**
	 * Sets the imagen.
	 *
	 * @param imagen the new imagen
	 */
	public void setImagen(BufferedImage imagen) {
		this.imagen=imagen;
		setIcon(new ImageIcon(imagen));
	}

	/**
	 * Gets the imagen.
	 *
	 * @return the imagen
	 */
	public BufferedImage getImagen() {
		return imagen;
	}
}
