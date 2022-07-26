/*
 * Programacion Interactiva
 * Archivo: PanelJugadores.java
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import controlPoker3.ControlPoker;
import modeloPoker3.Baraja;
import modeloPoker3.Carta;



// TODO: Auto-generated Javadoc
/**
 * The Class PanelJugadores.
 */
public class PanelJugadores extends JPanel {

	/** The Constant FONT_SIZE. */
	public static final int FONT_SIZE = 18;

	/** The Constant FONT_SIZE_2. */
	public static final int FONT_SIZE_2 = 14;

	/** The Constant FONT_TYPE. */
	public static final String FONT_TYPE = Font.DIALOG;

	/** The Constant FONT_STYLE. */
	public static final int FONT_STYLE = Font.BOLD;

	/** The Constant WIDTH. */
	private static final int WIDTH = Baraja.CARTA_WIDTH*6;

	/** The Constant HEIGHT. */
	private static final int HEIGHT = Baraja.CARTA_HEIGHT + FONT_SIZE*3;

	/** The dinero jugador. */
	private static int dineroJugador = 900 ;


	/** The mano. */
	private List<Carta> mano = new ArrayList<Carta>();

	/** The mensaje. */
	private JLabel nombre, mensaje; 

	/** The instrucciones. */
	private JPanel panelMano , instrucciones;

	/** The is human. */
	private boolean isHuman;

	/** The escucha. */
	private Escucha escucha;

	/** The caso. */
	private int caso;

	/** The control poker. */
	private ControlPoker controlPoker;

	/** The aleatorio. */
	private  Random aleatorio;



	/**
	 * Instantiates a new panel jugadores.
	 *
	 * @param nombreJugador the nombre jugador
	 * @param cartas the cartas
	 * @param isHuman the is human
	 */
	public  PanelJugadores(String nombreJugador, List<Carta> cartas, boolean isHuman) {
		aleatorio = new Random();
		setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();

		//creacion de las herramientas de diseño 
		Border raised = BorderFactory.createRaisedBevelBorder();
		Border lowered = BorderFactory.createLoweredBevelBorder();
		Font font3 = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,18);
		this.setBackground(new Color (0,200,50));


		nombre = new JLabel(nombreJugador);
		nombre.setFont(new Font(FONT_TYPE,FONT_STYLE,FONT_SIZE));

		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.NORTH;
		add(nombre,contraints);




		panelMano = new JPanel();
		panelMano.setLayout(new FlowLayout());
		panelMano.setBackground(new Color (0,200,50));
		panelMano.setPreferredSize(new Dimension(WIDTH,HEIGHT));


		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=0;
		contraints.gridheight=1;

		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(panelMano,contraints);


		this.isHuman = isHuman;



		for(Carta carta : cartas) {
			mano.add(carta);
			carta.setToolTipText("¿Quieres cambiar esta carta ?");
		}

		if(isHuman) {
			escucha = new Escucha();
			for(Carta carta : mano) {
				carta.addMouseListener(escucha);
			}
			VistaPoker.insertarTexto("Inicias: han apostado 100 $ como base, por favor apuesta, iguala o solo descarta las cartas que no necesites");

		}

		refrescarPanelMano();


	}

	/**
	 * Refrescar panel mano.
	 */
	private void refrescarPanelMano() {
		panelMano.removeAll();
		if(mano!=null) {
			for(Carta carta : mano) {
				panelMano.add(carta);
			}
		}
	}

	/**
	 * Recibir cartas.
	 *
	 * @param nuevasCartas the nuevas cartas
	 */
	public void recibirCartas(List<Carta> nuevasCartas) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mano=nuevasCartas;
				refrescarPanelMano();
				panelMano.revalidate();	
				panelMano.repaint();
			}

		});
	}

	/**
	 * Eliminar carta no human.
	 *
	 * @param indexes the indexes
	 */
	public void eliminarCartaNoHuman(ArrayList<Integer> indexes) {
		mano = Collections.synchronizedList(mano);
		synchronized (mano){
			for(Integer index : indexes) {
				mano.remove(index);
			}

		}   

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				refrescarPanelMano();
				panelMano.revalidate();	
				panelMano.repaint();

			}	   
		});
	}

	/**
	 * Eliminar carta.
	 *
	 * @param cartaEliminar the carta eliminar
	 */
	private void eliminarCarta(Carta cartaEliminar) { 
		mano = Collections.synchronizedList(mano);
		synchronized (mano){
			for(int i=0;i<mano.size();i++) {
				if(cartaEliminar.getValor()==mano.get(i).getValor() && cartaEliminar.getPalo()==mano.get(i).getPalo() ) {
					mano.remove(i);
				}   
			}

		}
	}

	/**
	 * Gets the mano.
	 *
	 * @return the mano
	 */
	public List<Carta> getMano() {
		return mano; 
	}

	/**
	 * Desactivar escuchas.
	 */
	public void desactivarEscuchas() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(Carta carta : mano) {
					carta.removeMouseListener(escucha);
				}
			}		   
		});
	}

	/**
	 * Activar escuchas.
	 */
	public void activarEscuchas() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(Carta carta : mano) {
					carta.addMouseListener(escucha);

				}
			}		   
		});   
	}

	/**
	 * Gets the dinero jugador.
	 *
	 * @return the dinero jugador
	 */
	public static int getDineroJugador() {
		return dineroJugador;
	}

	/**
	 * Disminuir dinero.
	 */
	public static void disminuirDinero() {
		dineroJugador = dineroJugador-VistaApostar.darApuesta();
		MesaJuego.dinero.setText("Dinero actual  $ " + PanelJugadores.getDineroJugador());
	}

	/**
	 * disminuir dinero al Reiniciar
	 */

	public static void disminuirDineroAlReinicio() {
		dineroJugador = dineroJugador-100;
	}


	/**
	 * Recoger dinero ganar.
	 */
	public static void recogerDineroGanar(){
		dineroJugador = dineroJugador + MesaJuego.apuestaGeneral;
		MesaJuego.apuestaGeneral= 500;
	}

	/**
	 * Refrescar todo.
	 */
	public void  refrescarTodo() {
		panelMano.revalidate();	
		panelMano.repaint();
	}


	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter{

		/**
		 * Mouse clicked.
		 *
		 * @param event the event
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub
			Carta cartaEliminar = (Carta) event.getSource();
			//eliminar carta del mazo y actualizar el panelMano 
			eliminarCarta(cartaEliminar);
			refrescarPanelMano();
			//caso = 3;
			panelMano.revalidate();	
			panelMano.repaint();
		} 
	}
}
