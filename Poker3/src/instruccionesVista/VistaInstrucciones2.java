/*
 * Programacion Interactiva
 * Archivo: VistaIntrucciones2.java
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
 * The Class VistaInstrucciones2.
 */
public class VistaInstrucciones2 extends JFrame {

	/** The zona botones. */
	//atributos
	private JPanel zonaBotones;
	
	/** The siguiente. */
	private	JButton volver, atras, siguiente ;

	/** The zona imagen 5. */
	private JLabel zonaImagen1,zonaImagen2,zonaImagen3,zonaImagen4,zonaImagen5;
	
	/** The mensajes imagen 5. */
	private JTextArea mensajes, mensajesImagen1, mensajesImagen2, mensajesImagen3, mensajesImagen4, mensajesImagen5;
	
	/** The escucha. */
	private Escucha escucha; 
	
	/** The imagen reglas. */
	private ImageIcon imagen, imagenReglas;
	
	/** The instrucciones 1. */
	private VistaInstrucciones1 instrucciones1;
	
	/** The vista instrucciones 3. */
	private VistaInstrucciones3 vistaInstrucciones3;
	
	/** The portada. */
	private VistaPortadaPoker portada;
	
	/** The vista instrucciones. */
	private JFrame vistaInstrucciones;	
	
	/** The titulo. */
	private Titulos titulo;

	
	/**
	 * Instantiates a new vista instrucciones 2.
	 */
	//metodos 
	public VistaInstrucciones2() {
		initGUI();

		//configuracion de la ventana ; 

		this.setLocation(120,50);;			
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
		mensajes = new JTextArea(2,40);
		mensajes.setOpaque(true);
		mensajes.setEditable(false);
		mensajes.setText( "A Continuacion el valor que tiene cada mano de mayor a menor");
		mensajes.setFont(font);
		mensajes.setForeground(Color.red);
		

		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajes,contraints);
		
		//mensaje para la imagen 1 
		mensajesImagen1 = new JTextArea(1,40);
		mensajesImagen1.setOpaque(true);
		mensajesImagen1.setEditable(false);
		mensajesImagen1.setText( "1.Escalera Real:5 cartas seguidas del mismo palo desde el 10 al As.");
		mensajesImagen1.setFont(font);
		mensajesImagen1.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen1,contraints);
		
		//imagen 1 
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas1.jpg");
		zonaImagen1 = new JLabel (imagenReglas);

		
		contraints.gridx=0;
		contraints.gridy=3;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen1,contraints);
		
		//mensaje para la imagen 2
		mensajesImagen2 = new JTextArea(1,40);
		mensajesImagen2.setOpaque(true);
		mensajesImagen2.setEditable(false);
		mensajesImagen2.setText( "2.Póker: 4 cartas iguales");
		mensajesImagen2.setFont(font);
		mensajesImagen2.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=4;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen2,contraints);
		
		
		//imagen 2 
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas2.jpg");
		zonaImagen2 = new JLabel (imagenReglas);
		
		
		contraints.gridx=0;
		contraints.gridy=5;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen2,contraints);
		
		//mensaje para la imagen 3
		mensajesImagen3 = new JTextArea(1,40);
		mensajesImagen3.setOpaque(true);
		mensajesImagen3.setEditable(false);
		mensajesImagen3.setText( "3.Escalera color: 5 cartas seguidas del mismo palo. El As puede formar escalera con el Rey y con el 2 (la carta de menor valor)");
		mensajesImagen3.setFont(font);
		mensajesImagen3.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=6;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen3,contraints);
		
		//imagen 3
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas3.jpg");
		zonaImagen3 = new JLabel (imagenReglas);
		
		
		contraints.gridx=0;
		contraints.gridy=7;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen3,contraints);

		//mensaje para la imagen 4
		mensajesImagen4 = new JTextArea(1,40);
		mensajesImagen4.setOpaque(true);
		mensajesImagen4.setEditable(false);
		mensajesImagen4.setText( "4.Full: 3 cartas iguales más otros dos iguales. Es decir, un trio y una pareja. En caso de empate, gana el que tiene el trio más alto");
		mensajesImagen4.setFont(font);
		mensajesImagen4.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=8;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen4,contraints);
		
		//imagen 4
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas4.jpg");
		zonaImagen4 = new JLabel (imagenReglas);
		
		
		contraints.gridx=0;
		contraints.gridy=9;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen4,contraints);
		
		//mensaje para la imagen 5
		mensajesImagen5 = new JTextArea(1,40);
		mensajesImagen5.setOpaque(true);
		mensajesImagen5.setEditable(false);
		mensajesImagen5.setText( "5.Color: Las 5 cartas del mismo palo.");
		mensajesImagen5.setFont(font);
		mensajesImagen5.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=10;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajesImagen5,contraints);
		
		//imagen 5
		imagenReglas = new ImageIcon ("src/imagenesReglas/reglas5.jpg");
		zonaImagen5 = new JLabel (imagenReglas);
	
		contraints.gridx=0;
		contraints.gridy=11;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(zonaImagen5,contraints);
		
	

		//este funcion es la que crea el boton para regresar al menu principal

		zonaBotones= new JPanel();
	
		//boton atras
		imagen = new ImageIcon("src/imagenesBotones/flechaAtras.png");
		atras = new JButton(imagen);
		atras.addActionListener(escucha);
		atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atras.setPreferredSize(new Dimension (100,100));
		
		//boton siguiente
		imagen = new ImageIcon("src/imagenesBotones/flecha.png");
		siguiente = new JButton(imagen);
		siguiente.addActionListener(escucha);
		siguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		siguiente.setPreferredSize(new Dimension (100,100));
		
		
		zonaBotones.add(atras);
		zonaBotones.add(siguiente);

		contraints.gridx=0;
		contraints.gridy=12;
		contraints.gridwidth=2;
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
			
			//vuelve a la pagina anterior de las instrucciones 
			 if (eventAction.getSource()== atras) {
				vistaInstrucciones.dispose();
				instrucciones1 = new VistaInstrucciones1();

			}
			 // pasa a la siguiente pagina de las instrucciones 
			 else if (eventAction.getSource()== siguiente) {
				vistaInstrucciones.dispose();
				vistaInstrucciones3 = new VistaInstrucciones3();

			}
		}
	}

}
