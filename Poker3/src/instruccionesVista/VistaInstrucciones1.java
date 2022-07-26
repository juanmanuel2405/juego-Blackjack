/*
 * Programacion Interactiva
 * Archivo: VistaIntrucciones1.java
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
 * The Class VistaInstrucciones1.
 */
public class VistaInstrucciones1 extends JFrame {
	
	//atributos
	
	/** The zona botones. */
	private JPanel zonaTexto, zonaBotones;
	
	/** The siguiente. */
	private	JButton volver, siguiente ;
	
	/** The instrucciones. */
	private JLabel instrucciones;
	
	/** The mensajes. */
	private JTextArea mensajes;
	
	/** The escucha. */
	private Escucha escucha; 
	
	/** The imagen. */
	private ImageIcon imagen;
	
	/** The portada. */
	private VistaPortadaPoker portada;
	
	/** The vista instrucciones 2. */
	private VistaInstrucciones2 vistaInstrucciones2;
	
	/** The vista instrucciones. */
	private JFrame vistaInstrucciones;
	
	/** The titulo. */
	private Titulos titulo;

	
	//metodos 
	
	
	/**
	 * Instantiates a new vista instrucciones 1.
	 */
	public VistaInstrucciones1() {
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

		//creacion de los objetos escucha , controles y cualquier otro

		vistaInstrucciones=this;
		escucha = new Escucha();
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,20);

	
		// aqui se crea el titulo de la ventana
		titulo = new Titulos("INSTRUCCIONES POKER ",30,new Color(0,0,0));
		titulo.setForeground(Color.magenta);
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);
		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=2;
		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(titulo,contraints);



		// Aqui se crean los mensajes de la ventana 
		mensajes = new JTextArea(2,40);
		mensajes.setOpaque(true);
		mensajes.setEditable(false);
		mensajes.setText( "INSTRUCCIONES "
				+ "\n\nEl objetivo principal del juego del poker es apostar. "
				+ "\nLas dos ocasiones para apostar se efectúan tras el reparto y  despues del descarte. "
				+ "\nCuando boton de apostar se habilite podras hacerlo, ademas las letras Azules te informaran las acciones que debes tomar en cada ronda "
				+ "\n\nGana la partida el jugador que tiene la  mano de mayor valor."
				+ "\nEn caso de empate, gana la jugada con las cartas más altas (siendo el As la carta de mayor valor)."
				+ "\nEn caso de tener exactamente las mismas cartas, gana el más próximo al jugador mano (el que ha empezado la ronda)."
				+ "\nTambién es posible ganar si el resto de contrincantes se retira. "
				+ "\n\nEn el momento de la segunda apueta tienes 2 opciones en caso de que se aumente la apuesta:"
				+ "\nPuedes igualar la apuesta"
				+ "\nRetirarte si no tienes mas dinero para jugar"
				);
		
		mensajes.setFont(font);
		mensajes.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.CENTER;

		add(mensajes,contraints);

		//creacion de los botones 

		zonaBotones= new JPanel();
		
		//boton volver
		imagen = new ImageIcon("src/imagenesBotones/volverAtras.png");
		volver = new JButton(imagen);
		volver.addActionListener(escucha);
		volver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		volver.setPreferredSize(new Dimension (100,100));

		//boton siguiente
		imagen = new ImageIcon("src/imagenesBotones/flecha.png");
		siguiente = new JButton(imagen);
		siguiente.addActionListener(escucha);
		siguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		siguiente.setPreferredSize(new Dimension (100,100));


		zonaBotones.add(volver);
		zonaBotones.add(siguiente);


		contraints.gridx=0;
		contraints.gridy=2;
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
			if(eventAction.getSource()== volver ) {
				//esta funcion nos devuelve a la portada y desactiva la ventana de instrucciones
				vistaInstrucciones.dispose();
				portada = new VistaPortadaPoker ();

			} else if (eventAction.getSource()== siguiente) {
				// esta funcion cambia a la ventana de instrucciones 2 
				vistaInstrucciones.dispose();
				vistaInstrucciones2 = new VistaInstrucciones2();

			}
		}
	}
}
