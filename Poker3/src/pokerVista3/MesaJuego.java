/*
 * Programacion Interactiva
 * Archivo: MesaJuego.java
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

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import controlPoker3.ControlPoker;
import modeloPoker3.Baraja;
import modeloPoker3.Carta;


// TODO: Auto-generated Javadoc
/**
 * The Class MesaJuego.
 */
public class MesaJuego extends JPanel {

	/** The Constant CARTA_TAPADA_FILE. */
	public static final String CARTA_TAPADA_FILE="/resources/cardBack.png";

	/** The jugador 5. */
	private PanelJugadores jugador1, jugador2, jugador3, jugador4, jugador5;

	/** The apuesta mesa. */
	private JLabel labelMazo, cartasTotales, apuestaMesa;

	/** The numero cartas. */
	private JLabel numeroCartas;

	/** The dinero. */
	static JTextField dinero;

	/** The apuesta. */
	private static JTextField apuesta;

	/** The imagen mazo. */
	private ImageIcon imagenMazo;

	/** The jugador simulados. */
	private JPanel comunes, jugadorSimulados;

	/** The zona numero cartas. */
	private JPanel zonaApuestaMesa, zonaCartasMesa,zonaNumeroCartas;

	/** The panel J 5. */
	private JPanel panelJ1, panelJ2 ,panelJ3, panelJ4, zonaApuesta, panelJ5;

	/** The jugador principal. */
	private JPanel jugadorPrincipal;

	/** The imagen apostar. */
	private ImageIcon imagenApostar;

	/** The apostar 2. */
	private static JButton apostar, apostar2;

	/** The escucha. */
	private Escucha escucha ;

	/** The apuesta general. */
	static int apuestaGeneral = 500;	

	/** The numero ronda apuesta. */
	static public int numeroRondaApuesta = 0;

	/** The vista poker. */
	private VistaPoker vistaPoker;

	/** The vista portada. */
	private VistaPortadaPoker vistaPortada;

	/** The control poker. */
	private ControlPoker controlPoker;

	/**
	 * Instantiates a new mesa juego.
	 *
	 * @param jugadorHumano the jugador humano
	 * @param jugadoresSimulados the jugadores simulados
	 * @param manosJugadores the manos jugadores
	 */
	public MesaJuego(String jugadorHumano, String[] jugadoresSimulados, List<List<Carta>>manosJugadores) {

		this.setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();

		//creacion de las herramientas de ayuda para la ventana 
		this.setBackground(new Color (0,200,50,0));
		Border raised = BorderFactory.createRaisedBevelBorder();
		Border lowered = BorderFactory.createLoweredBevelBorder();
		escucha = new Escucha();

		//creacion de las fuentes para las letras 
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,25);
		Font font2 = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,30);

		//llamado a los jugadores 
		jugador1 = new PanelJugadores(jugadoresSimulados[0],manosJugadores.get(0),false);
		jugador2 = new PanelJugadores(jugadoresSimulados[1],manosJugadores.get(1),false);
		jugador3 = new PanelJugadores(jugadoresSimulados[2],manosJugadores.get(2),false);
		jugador4 = new PanelJugadores(jugadoresSimulados[3],manosJugadores.get(3),false);

		//creacion de los paneles para los jugadores 
		jugadorSimulados = new JPanel();
		jugadorSimulados.setLayout(new FlowLayout());
		jugadorSimulados.setBackground(new Color (0,200,50,0));

		//panel jugador 1
		panelJ1 = new JPanel();
		panelJ1.setLayout(new FlowLayout());
		jugadorSimulados.add(panelJ1);
		panelJ1.add(jugador1);

		//panel jugador 2
		panelJ2 = new JPanel();
		panelJ2.setLayout(new FlowLayout());
		jugadorSimulados.add(panelJ2);
		panelJ2.add(jugador2);

		//panel jugador 3
		panelJ3 = new JPanel();
		panelJ3.setLayout(new FlowLayout());
		jugadorSimulados.add(panelJ3);
		panelJ3.add(jugador3);

		//panel jugador 4
		panelJ4 = new JPanel();
		panelJ4.setLayout(new FlowLayout());
		jugadorSimulados.add(panelJ4);
		panelJ4.add(jugador4);

		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=2;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(jugadorSimulados,contraints); 


		//creacion espacios en la mesa , apuesta y mazo 
		comunes = new JPanel();
		comunes.setBackground(new Color (0,200,50,0));
		comunes.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );


		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(comunes,contraints); 


		//creacion de las cartas del mazo en la meza
		zonaCartasMesa = new JPanel();
		zonaCartasMesa.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );

		//cantidad de cartas en el mazo 
		zonaNumeroCartas = new JPanel();
		zonaNumeroCartas.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );
		zonaNumeroCartas.setBackground(Color.lightGray);
		cartasTotales = new JLabel ("Cartas Disponibles");
		cartasTotales.setFont(font2);

		numeroCartas = new JLabel (""+Baraja.tamañoMazo()+"");
		numeroCartas.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );
		numeroCartas.setFont(font2);

		zonaNumeroCartas.add(cartasTotales);
		zonaNumeroCartas.add(numeroCartas);

		imagenMazo = new ImageIcon("src/imagenesBotones/pMazo.png");
		labelMazo = new JLabel(imagenMazo);

		zonaCartasMesa.add(zonaNumeroCartas);
		zonaCartasMesa.add(labelMazo);

		apuestaMesa = new JLabel("La Apuesta actual es   ");
		apuestaMesa.setFont(font2);

		apuesta = new JTextField();
		apuesta.setEditable(false);
		apuesta.setText(apuestaGeneral+"");
		apuesta.setHorizontalAlignment(SwingConstants.CENTER); 
		apuesta.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );
		apuesta.setPreferredSize(new Dimension(100,50));
		apuesta.setFont(font2);

		zonaApuestaMesa  = new JPanel();

		zonaApuestaMesa.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );


		//creacion zona dinero ,apostar  

		zonaApuesta = new JPanel ();
		zonaApuesta.setLayout(new BorderLayout());
		zonaApuesta.setPreferredSize(new Dimension(300,120));


		dinero = new JTextField();
		dinero.setEditable(false);
		dinero.setHorizontalAlignment(SwingConstants.CENTER); 
		dinero.setText("Dinero actual  $ " + PanelJugadores.getDineroJugador());
		dinero.setFont(font);
		dinero.setBorder(BorderFactory.createCompoundBorder(raised, lowered) );

		//boton apostar
		imagenApostar = new ImageIcon("src/imagenesBotones/apostar.png");
		apostar = new JButton (imagenApostar);
		apostar.setToolTipText("Pulsalo para ingresar tu apuesta");
		apostar.setBorder(null);
		apostar.setContentAreaFilled(false);
		apostar.setEnabled(true);
		apostar.addActionListener(escucha);



		zonaApuesta.add(dinero,BorderLayout.NORTH);
		zonaApuesta.add(apostar,BorderLayout.CENTER);



		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.CENTER;

		add(zonaApuesta, contraints);

		//panel del jugador principal

		panelJ5 = new JPanel();
		panelJ5.setPreferredSize(new Dimension (500,120 ));
		jugador5 = new PanelJugadores(jugadorHumano,manosJugadores.get(4),true);
		jugador5.setPreferredSize(new Dimension (490,110));

		panelJ5.add(jugador5);

		contraints.gridx=1;
		contraints.gridy=2;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.NONE;
		add(panelJ5,contraints); 


		comunes.add(zonaCartasMesa);
		comunes.add(zonaApuestaMesa);
		zonaApuestaMesa.add(apuestaMesa);
		zonaApuestaMesa.add(apuesta);



	}


	/**
	 * Gets the mano humano.
	 *
	 * @return the mano humano
	 */
	//devuelve la mano del jugador humano
	public List<Carta> getManoHumano(){
		return jugador5.getMano();
	}

	/**
	 * Gets the apuesta mesa.
	 *
	 * @return the apuesta mesa
	 */
	public int getApuestaMesa() {
		return apuestaGeneral;
	}


	/**
	 * Aumentar apuesta.
	 */
	public static  void aumentarApuesta() {
		apuestaGeneral = apuestaGeneral+ VistaApostar.darApuesta();
		apuesta.setText(apuestaGeneral+"");
	}

	/**
	 * Igualar apuesta.
	 */
	public static void igualarApuesta() {
		apuestaGeneral = apuestaGeneral+VistaApostar.darApuesta()*5;
		apuesta.setText(apuestaGeneral+"");
	}

	/**
	 * Apuesta jugador mano.
	 *
	 * @param recibirApuesta the recibir apuesta
	 */
	public static void apuestaJugadorMano(int recibirApuesta) {
		apuestaGeneral = apuestaGeneral+recibirApuesta*4;
		apuesta.setText(apuestaGeneral+"");
	}

	/**
	 * Apuesta segunda mano.
	 *
	 * @param recibirApuesta the recibir apuesta
	 */
	public static void apuestaSegundaMano(int recibirApuesta) {
		apuestaGeneral = apuestaGeneral+recibirApuesta;
		apuesta.setText(apuestaGeneral+"");
	}


	/**
	 * Mesa actualizar.
	 *
	 * @param manosJugadores the manos jugadores
	 */
	//actualiza la GUI con el estado del juego
	public void mesaActualizar(List<List<Carta>> manosJugadores) {
		//determinarResultado(manosJugadores);

		jugador1.recibirCartas(manosJugadores.get(0) );
		jugador2.recibirCartas(manosJugadores.get(1));
		jugador3.recibirCartas(manosJugadores.get(2));
		jugador4.recibirCartas(manosJugadores.get(3));
		jugador5.recibirCartas(manosJugadores.get(4));


	}

	/**
	 * Reiniciar juego.
	 *
	 * @param manosJugadores the manos jugadores
	 */
	public void reiniciarJuego(List<List<Carta>> manosJugadores) {
		jugador1.recibirCartas(manosJugadores.get(0));
		jugador2.recibirCartas(manosJugadores.get(1));
		jugador3.recibirCartas(manosJugadores.get(2));
		jugador4.recibirCartas(manosJugadores.get(3));
		jugador5.recibirCartas(manosJugadores.get(4));
		jugador5.activarEscuchas();


		PanelJugadores.disminuirDineroAlReinicio();

		apuestaGeneral = 500;
		apuesta.setText(apuestaGeneral+"");
		apuesta.revalidate();
		apuesta.repaint();

		dinero.setText("Dinero actual  $ " + PanelJugadores.getDineroJugador());
		dinero.revalidate();
		dinero.repaint();

		VistaPoker.mensaje.setText("Inicias: han apostado 100 $ como base, por favor apuesta, iguala o solo descarta las cartas que no necesites");

		apostar.setEnabled(true);

	}


	/**
	 * Desactivar escuchas.
	 */
	public void desactivarEscuchas() {
		jugador5.desactivarEscuchas();
	}

	/**
	 * Habilitar boton apostar.
	 */
	public static void habilitarBotonApostar() {
		apostar.setEnabled(true);
	}

	/**
	 * Habilitar boton apostar 2.
	 */
	public static void deshabilitarBotonApostar() {
		apostar.setEnabled(false);
	}

	/**
	 * Refrescar centro mesa.
	 */
	public void refrescarCentroMesa() {
		zonaNumeroCartas.revalidate();
		zonaNumeroCartas.repaint();
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
		@Override
		public void actionPerformed(ActionEvent eventAction) {


			//responde a los eventos de los botones  salir y apostar 
			if (eventAction.getSource()== apostar ) {

				
				//esta funcion comienza la vista Apostar 
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						VistaApostar myVista = new VistaApostar();


					}
				});

			} 
		}

		//mouse evente on click ---

		/**
		 * Mouse clicked.
		 *
		 * @param eventMouse the event mouse
		 */
		public void mouseClicked(MouseEvent eventMouse) {
			// debe funcionar cuando haya que descartar las cartas 

		}


	}


}
