/*
 * Programacion Interactiva
 * Archivo: VistaPortadaPoker.java
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
package pokerVista3;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlPoker3.ControlPoker;
import instruccionesVista.VistaInstrucciones1;


// TODO: Auto-generated Javadoc
/**
 * The Class VistaPortadaPoker.
 */
public class VistaPortadaPoker extends JFrame {
	// atributos 

	/** The fondo. */
	private JPanel portadaBotones, fondo;
	
	/** The portada texto. */
	private JLabel fondoImagen,portadaTexto ;
	
	/** The score. */
	private JButton play, salir , instrucciones, score;
	
	/** The escucha play. */
	private Escucha escuchaPlay;
	
	/** The vista portada. */
	private JFrame vistaPortada;
	
	/** The imagen. */
	private ImageIcon imagen;
	
	/** The vista instrucciones 1. */
	private VistaInstrucciones1 vistaInstrucciones1;
	
	/** The control poker. */
	private ControlPoker controlPoker;

	/** The vista poker. */
	private VistaPoker vistaPoker;
		
	
	
	//metodos 

	/**
	 * Instantiates a new vista portada poker.
	 */
	public VistaPortadaPoker() {
		initGUI();

		//configuracion de la ventana ; 

		this.setLocation(230,5);       				
		this.setUndecorated(true);					
		this.pack();								
		this.setVisible(true);						
		this.setResizable(false);;					
		this.setBackground(new Color(0,100,250,150));
	}


	/**
	 * Inits the GUI.
	 */
	private void initGUI() {

		// configuracion del container - layout 
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();


		//creacion de los objetos escucha , control y cualquier otro 

		escuchaPlay = new Escucha();
		vistaPortada = this;
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,60);


		// aqui se crea el mensaje de la portada
		portadaTexto = new JLabel();
		portadaTexto.setText("Poker");
		portadaTexto.setFont(font);
		portadaTexto.setBackground(new Color (250,0,0,200));
		portadaTexto.setForeground(Color.white);
		portadaTexto.setHorizontalAlignment(JLabel.HORIZONTAL);
		portadaTexto.setOpaque(true);


		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NORTH;

		add(portadaTexto, contraints);


		// esta funcion es la que añade el fondo 
		fondo =new JPanel();
		imagen = new ImageIcon("src/imagenesBotones/popular.jpg");
		fondoImagen = new JLabel();
		fondoImagen.setIcon(imagen);

		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;

		fondo.add(fondoImagen);
		add(fondo,contraints);



		//esta funcion añade los botones a la ventana 

		portadaBotones = new JPanel();
		//portadaBotones.setPreferredSize(new Dimension(960,120));
		//portadaBotones.setBackground(new Color(0,250,0,150));
		contraints.gridx=0;
		contraints.gridy=3;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.SOUTH;

		//boton play 
		imagen = new ImageIcon("src/imagenesBotones/start.png");
		play = new JButton(imagen);
		play.addActionListener(escuchaPlay);
		play.setCursor(new Cursor(Cursor.HAND_CURSOR));
		play.setPreferredSize(new Dimension (100,100));
		play.setBorder(null);
		play.setContentAreaFilled(false);
		portadaBotones.add(play);

		//boton de instrucciones 
		imagen = new ImageIcon("src/imagenesBotones/instrucciones.png");
		instrucciones = new JButton(imagen);
		instrucciones.addActionListener(escuchaPlay);
		instrucciones.setCursor(new Cursor(Cursor.HAND_CURSOR));
		instrucciones.setPreferredSize(new Dimension (100,100));
		instrucciones.setBorder(null);
		instrucciones.setContentAreaFilled(false);
		portadaBotones.add(instrucciones);


		//boton  de salir 
		imagen = new ImageIcon("src/imagenesBotones/exit.png");
		salir = new JButton(imagen);
		salir.addActionListener(escuchaPlay);
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		salir.setPreferredSize(new Dimension (100,100));
		salir.setBorder(null);
		salir.setContentAreaFilled(false);
		portadaBotones.add(salir);

		add(portadaBotones,contraints);


	}

 
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	//Hilo principal - main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 EventQueue.invokeLater(new Runnable() {
	        	public void run() {
	        		new VistaPortadaPoker();
	        	}
	        });
	}
	
	
	
	
	/**
	 * The Class Escucha.
	 */
	private class Escucha implements ActionListener{


		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub

			//esta funcion analiza los eventos de los botones 
			if(eventAction.getSource()== salir) {
				System.exit(0);
			}else if (eventAction.getSource()== play ) {

				//esta funcion comienza la vista y el control al precionar play
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						//generar manos iniciales de los jugadores, generar carta común, iniciar la Vista	
						
						vistaPortada.dispose();
						ControlPoker control = new ControlPoker();

					}
				});
			}
			else if (eventAction.getSource()== instrucciones ) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {

						vistaPortada.dispose();
						vistaInstrucciones1 = new VistaInstrucciones1 ();

					}
				});

			}

		}
	}


}
