/*
 * Programacion Interactiva
 * Archivo: VistaScore.java
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controlPoker3.ControlPoker;
import misComponentes.Titulos;


// TODO: Auto-generated Javadoc
/**
 * The Class VistaScore.
 */
public class VistaScore extends JFrame {
	/** The escucha. */
	//atributos 
	private Escucha escucha;

	/** The zona botones. */
	private JPanel zonaBotones;

	/** The nombres. */
	private JLabel nombres [];

	/** The score. */
	private JTextArea score;

	/** The resultado 5. */
	private JTextField resultado1,resultado2,resultad3,resultado4,resultado5 ;

	/** The titulo. */
	private Titulos titulo ;

	/** The volver. */
	private JButton salir, volver;

	/** The imagen. */
	private ImageIcon imagen;

	/** The vista score. */
	private JFrame vistaScore;

	/** The control poker. */
	private ControlPoker controlPoker;

	/** The imagen boton. */
	private ImageIcon imagenBoton;

	/** The otra ronda. */
	private JButton otraRonda;

	/** The mesa juego. */
	private MesaJuego mesaJuego;


	/**
	 * Instantiates a new vista score.
	 */
	public VistaScore() {
		initGUI();

		//configuracion de la ventana ; 

		this.setUndecorated(true);			
		this.pack();						
		this.setLocationRelativeTo(null);	
		this.setResizable(false); 			
		this.setVisible(true); 				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 

	}

	/**
	 * Inits the GUI.
	 */
	private void initGUI() {

		// configuracion del container - layout 
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();


		//creacion de los objetos escucha, control y bordes 

		escucha = new Escucha();
		Border raised = BorderFactory.createRaisedBevelBorder();
		Border lowered = BorderFactory.createLoweredBevelBorder();
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,18);
		Font font2 = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,30);
		vistaScore = this;

		//creacion del titulo 

		titulo = new Titulos("Score",30,new Color(0,0,0)); // usa la funcion clase Titulos 
		titulo.setForeground(new Color (250,200,0));
		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(titulo,contraints); // funcion que añade el titulo a la ventana 

		//creacion zona de texto y sus mensajes 
		score= new JTextArea(10,30);
		score.setText("Resultado de la PARTIDA.\n"+
				"\n el Jugador Tal es el ganador");


		score.setFont(font);
		score.setForeground(new Color ( 0,250,100));
		score.setEditable(false);
		JScrollPane scroll = new JScrollPane(score);
		score.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );

		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.VERTICAL;
		contraints.anchor=GridBagConstraints.CENTER;

		add(scroll, contraints);


		//creacion de la zona de los botones , con su respectivo boton 

		zonaBotones = new JPanel ();

		//boton volver 

		imagenBoton = new ImageIcon("src/imagenesBotones/reiniciar.jpg");
		otraRonda = new JButton(imagenBoton);
		otraRonda.addMouseListener(escucha);
		otraRonda.setToolTipText("¿Quieres Reiniciar el juego o jugar otra ronda?");
		otraRonda.setVisible(true);
		otraRonda.setCursor(new Cursor(Cursor.HAND_CURSOR));
		otraRonda.setBorder(null);
		otraRonda.setContentAreaFilled(false);


		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.VERTICAL;
		contraints.anchor=GridBagConstraints.CENTER;

		add(otraRonda, contraints);

	}



	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			//responde a los eventos del botone volver

			if (e.getSource()== otraRonda ) {
				//otra ronda
				mesaJuego.reiniciarJuego(controlPoker.reiniciarJuego());

				vistaScore.dispose();
			}
		}

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
