/*
 * Programacion Interactiva
 * Archivo: VistaPoker.java
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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import controlPoker3.ControlPoker;
import misComponentes.Titulos;
import misComponentes.Titulos2;
import modeloPoker3.Carta;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaPoker.
 */
public class VistaPoker extends JFrame {


	//atributos 

	/** The volver. */
	private JButton volver;

	/** The imagen boton. */
	private ImageIcon imagenBoton;

	/** The titulo. */
	private Titulos titulo;

	/** The mesa juego. */
	private MesaJuego mesaJuego;

	/** The panel botones. */
	private JPanel panelBotones;

	/** The otra ronda. */
	private JButton pedirCartas,otraRonda;

	/** The vista poker. */
	private JFrame vistaPoker;

	/** The control poker. */
	private ControlPoker controlPoker;

	/** The escucha. */
	private Escucha2 escucha;

	/** The vista portada. */
	private VistaPortadaPoker vistaPortada;

	/** The panel jugadores. */
	private PanelJugadores panelJugadores;

	/** The instrucciones. */
	private JPanel instrucciones;

	/** The mensajes. */
	private static String mensajes;

	/** The mensaje. */
	public static JTextField mensaje;




	/**
	 * Instantiates a new vista poker.
	 *
	 * @param jugadoresSimulados the jugadores simulados
	 * @param manosJugadores the manos jugadores
	 * @param controlPoker the control poker
	 */
	public VistaPoker(String[] jugadoresSimulados, List<List<Carta>> manosJugadores, ControlPoker controlPoker) {
		this.controlPoker = controlPoker;

		initGUI(jugadoresSimulados, manosJugadores);
		this.setUndecorated(true);								
		this.pack();													
		this.setLocationRelativeTo(null);						
		this.setResizable(false);								
		this.setVisible(true);					 				 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	
		this.setBackground(new Color (0,200,50,100)); 			 
	}

	/**
	 * Inits the GUI.
	 *
	 * @param jugadoresSimulados the jugadores simulados
	 * @param manosJugadores the manos jugadores
	 */
	private void initGUI(String[] jugadoresSimulados, List<List<Carta>> manosJugadores) {
		// TODO Auto-generated method stub
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();


		Border raised = BorderFactory.createRaisedBevelBorder();
		Border lowered = BorderFactory.createLoweredBevelBorder();
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,18);
		String jugadorHumano = "Mi mano";
		vistaPoker = this;
		escucha = new Escucha2();



		titulo = new Titulos("Poker ",30,new Color(0,0,0)); // usa la funcion clase Titulos 

		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(titulo,contraints); // funcion que añade el titulo a la ventana 


		mesaJuego = new MesaJuego(jugadorHumano, jugadoresSimulados, manosJugadores);
		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(mesaJuego,contraints); 

		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());


		//boton salir o volver

		imagenBoton = new ImageIcon("src/imagenesBotones/volver.png");
		volver = new JButton (imagenBoton);
		volver.addActionListener(escucha);
		volver.setToolTipText("Pulsalo para volver a la portada");
		volver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		volver.setBorder(null);
		volver.setContentAreaFilled(false);


		panelBotones.add(volver);


		//boton para pedir carta

		imagenBoton = new ImageIcon("src/imagenesBotones/pedirCarta.png");

		pedirCartas = new JButton(imagenBoton);
		pedirCartas.setToolTipText("¿Ya clickeaste las cartas a eliminar?");
		pedirCartas.addActionListener(escucha);
		pedirCartas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pedirCartas.setBorder(null);
		pedirCartas.setContentAreaFilled(false);

		panelBotones.add(pedirCartas);
		panelBotones.setPreferredSize(new Dimension(300,80));


		//boton para jugar otra ronda o reiniciar la partida 

		imagenBoton = new ImageIcon("src/imagenesBotones/reiniciar.jpg");
		otraRonda = new JButton(imagenBoton);
		otraRonda.addActionListener(escucha);
		otraRonda.setToolTipText("¿Quieres Reiniciar el juego o jugar otra ronda?");
		otraRonda.setVisible(true);
		otraRonda.setCursor(new Cursor(Cursor.HAND_CURSOR));
		otraRonda.setBorder(null);
		otraRonda.setContentAreaFilled(false);



		panelBotones.add(otraRonda);

		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.WEST;
		contraints.anchor=GridBagConstraints.WEST;

		add(panelBotones, contraints);


		instrucciones = new JPanel();
		instrucciones.setBackground(new Color(0,250,0,60));
		mensaje = new JTextField();
		mensaje.setEditable(false);
		mensaje.setText(mensajes+"");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER); 
		mensaje.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );
		mensaje.setPreferredSize(new Dimension(830,70));
		mensaje.setFont(font);
		mensaje.setForeground(new Color(0,0,250));		

		instrucciones.add(mensaje);

		contraints.gridx=1;
		contraints.gridy=2;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(instrucciones,contraints);


	}


	/**
	 * Insertar texto.
	 *
	 * @param texto the texto
	 */
	public static  void insertarTexto(String texto) {
		mensajes = texto;
	}


	/**
	 * Actualizar vista poker.
	 *
	 * @param manosJugadores the manos jugadores
	 */
	//se debe llamar para actualizar la GUI una vez se tenga el resultado
	public void actualizarVistaPoker(List<List<Carta>> manosJugadores) {
		//debe llamarse cuanto el control tenga las nuevas manos y el resultado

		mesaJuego.mesaActualizar(manosJugadores);
		//pedirCartas.setEnabled(true);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				otraRonda.setVisible(true);
			}

		});

	}

	/**
	 * Cerrar vista poker.
	 */
	public void cerrarVistaPoker() {
		vistaPoker.dispose();
	}




	/**
	 * The Class Escucha2.
	 */
	public class Escucha2 extends MouseAdapter implements ActionListener {

		/**
		 * Mouse clicked.
		 *
		 * @param e the e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub


		}

		/**
		 * Action performed.
		 *
		 * @param arg0 the arg 0
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if ( arg0.getSource()==volver) {

				vistaPoker.dispose();
				VistaPortadaPoker myvista = new VistaPortadaPoker();

			} else if (arg0.getSource()==pedirCartas) {

				//deben jugar los jugadores simulados, recibir nueva cartas y mostrar el resultado
				controlPoker.descarteJugadorHumano(mesaJuego.getManoHumano());	
				mesaJuego.refrescarCentroMesa();
				mesaJuego.desactivarEscuchas();
				MesaJuego.habilitarBotonApostar();
				pedirCartas.setEnabled(false);
			} else {
				mesaJuego.reiniciarJuego(controlPoker.reiniciarJuego());
				pedirCartas.setEnabled(true);
			}
		}		
	}

	/**
	 * Action performed.
	 *
	 * @param eventAction the event action
	 */
	public void actionPerformed(ActionEvent eventAction) {

	}
}

