/*
 * Programacion Interactiva
 * Archivo: VistaIntrucciones3.java
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
package instruccionesVista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import misComponentes.Titulos;
import pokerVista3.VistaPortadaPoker;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaInstrucciones3.
 */
public class VistaInstrucciones3 extends JFrame {

	/** The zona botones. */
	//atributos
	private JPanel todo, zonaBotones;
	
	/** The atras. */
	private	JButton volver, atras ;
	
	/** The zona imagen 10. */
	private JLabel instrucciones,zonaImagen6,zonaImagen7,zonaImagen8,zonaImagen9,zonaImagen10;
	
	/** The mensajes imagen 10. */
	private JTextArea  mensajesImagen6, mensajesImagen7, mensajesImagen8, mensajesImagen9, mensajesImagen10;
	
	/** The escucha. */
	private Escucha escucha; 
	
	/** The imagen reglas. */
	private ImageIcon imagen, imagenReglas;
	
	/** The instrucciones 2. */
	private VistaInstrucciones2 instrucciones2;
	
	/** The portada. */
	private VistaPortadaPoker portada;
	
	/** The vista instrucciones. */
	private JFrame vistaInstrucciones;
	
	/** The titulo. */
	private Titulos titulo;


	/**
	 * Instantiates a new vista instrucciones 3.
	 */
	//metodos 
	public VistaInstrucciones3() {
		initGUI();

		//configuracion de la ventana ; 

		this.setLocation(50,50);;			
		this.setUndecorated(true);			
		this.pack();						
		this.setVisible(true);				
		this.setResizable(false);;			
	}

	/**
	 * Inits the GUI.
	 */
	private void initGUI() {

		// configuracion del container - layout 
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();

		//creacion de los objetos escucha , control y cualquier otro

		vistaInstrucciones=this;
		escucha = new Escucha();
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,20);


		// aqui se crea el titulo de la ventana
		titulo = new Titulos("TIPO DE MANOS EN POKER ",30,new Color(0,0,0));
		titulo.setForeground(Color.magenta);
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);

		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=2;
		contraints.fill=GridBagConstraints.HORIZONTAL;

		add(titulo,contraints);

		// Aqui se crean los mensajes de la ventana  y las imagenes que se van a mostrar 

		//mensaje para la imagen 6
		mensajesImagen6 = new JTextArea(1,40);
		mensajesImagen6.setOpaque(true);
		mensajesImagen6.setEditable(false);
		mensajesImagen6.setText( "6.Escalera: Igual que la escalera de color, pero no es necesario que las 5 cartas sean del mismo palo.");
		mensajesImagen6.setFont(font);
		mensajesImagen6.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.EAST;

		add(mensajesImagen6,contraints);

		//imagen 6
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas6.jpg");
		zonaImagen6 = new JLabel (imagenReglas);


		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen6,contraints);


		//mensaje para la imagen 7
		mensajesImagen7 = new JTextArea(1,40);
		mensajesImagen7.setOpaque(true);
		mensajesImagen7.setEditable(false);
		mensajesImagen7.setText( "7.Trío: 3 cartas iguales.");
		mensajesImagen7.setFont(font);
		mensajesImagen7.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=3;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen7,contraints);


		//iamgen 7
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas7.jpg");
		zonaImagen7 = new JLabel (imagenReglas);


		contraints.gridx=0;
		contraints.gridy=4;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen7,contraints);


		//mensaje para la imagen 8
		mensajesImagen8 = new JTextArea(1,40);
		mensajesImagen8.setOpaque(true);
		mensajesImagen8.setEditable(false);
		mensajesImagen8.setText( "8.Doble Pareja: Dos parejas de cartas iguales. A la jugada formada por 8(picas) 8(trebol) A(picas) A(trabol) se le llama “la mano del hombre muerto”.");
		mensajesImagen8.setFont(font);
		mensajesImagen8.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=5;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen8,contraints);


		//imagen 8
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas8.jpg");
		zonaImagen8 = new JLabel (imagenReglas);


		contraints.gridx=0;
		contraints.gridy=6;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen8,contraints);

		//mensaje para la imagen 9
		mensajesImagen9 = new JTextArea(1,40);
		mensajesImagen9.setOpaque(true);
		mensajesImagen9.setEditable(false);
		mensajesImagen9.setText( "9.Pareja: Una pareja de cartas iguales");
		mensajesImagen9.setFont(font);
		mensajesImagen9.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=7;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen9,contraints);


		//imagen 9
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas9.jpg");
		zonaImagen9 = new JLabel (imagenReglas);


		contraints.gridx=0;
		contraints.gridy=8;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen9,contraints);

		//mensaje para la imagen 10
		mensajesImagen10 = new JTextArea(1,40);
		mensajesImagen10.setOpaque(true);
		mensajesImagen10.setEditable(false);
		mensajesImagen10.setText( "10.Carta más alta: El (As) es la carta más alta.");
		mensajesImagen10.setFont(font);
		mensajesImagen10.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=9;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen10,contraints);

		//imagen 10 
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas10.jpg");
		zonaImagen10 = new JLabel (imagenReglas);


		contraints.gridx=0;
		contraints.gridy=10;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen10,contraints);


		//este funcion es la que crea el boton para regresar al menu principal

		zonaBotones= new JPanel();


		imagen = new ImageIcon("src/imagenesBotones/flechaAtras.png");
		atras = new JButton(imagen);
		atras.addActionListener(escucha);
		atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atras.setPreferredSize(new Dimension (100,100));


		zonaBotones.add(atras);


		contraints.gridx=0;
		contraints.gridy=11;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.HORIZONTAL;	

		add(zonaBotones,contraints);


	}

	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter implements ActionListener {


		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub

			//esta funcion analiza los eventos de los botones 

			//devuelve a la pagina 2 de las instrucciones 
			if (eventAction.getSource()== atras) {
				vistaInstrucciones.dispose();
				instrucciones2 = new VistaInstrucciones2();
			}
		}
	}

}
