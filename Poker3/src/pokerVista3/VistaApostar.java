/*
 * Programacion Interactiva
 * Archivo: VistaApostar.java
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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlPoker3.ControlPoker;
import misComponentes.Titulos;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaApostar.
 */
public class VistaApostar extends JFrame {

	/** The cantidad apuesta. */
	public static int cantidadApuesta;


	/** The zona boton. */
	//atributos 
	private JPanel zonaTexto, zonaBoton;


	/** The boton. */
	private JLabel zonaApuesta,boton;


	/** The escucha. */
	private Escucha escucha ;


	/** The apuesta. */
	private JTextField apuesta; 


	/** The instrucciones. */
	private JTextArea instrucciones;


	/** The titulo. */
	private Titulos titulo;


	/** The vista. */
	private JFrame vista;

	/** The mesa juego. */
	private MesaJuego mesaJuego;

	/** The control poker. */
	private ControlPoker controlPoker;


	/** The condicion apuesta. */
	private int condicionApuesta = 0; 

	/** The condicion apuesta 2. */
	private int condicionApuesta2 = 0;


	/**
	 * Instantiates a new vista apostar.
	 */
	//metodos 
	public VistaApostar () {

		//configuracion de la ventana
		initGUI();


		this.setLocation(250,200);         	 
		this.setUndecorated(true);        	    
		pack();      						 
		this.setVisible(true);            	 
		this.setResizable(false);            

	}


	/**
	 * Inits the GUI.
	 */
	private void initGUI() {

		//setup del layout 
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();

		//creacion de escuchas y de mas 
		escucha = new Escucha();
		vista = this;



		//creacion del titulo de la ventan
		titulo = new Titulos("REALIZA AQUI TU APUESTA!",30,new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.fill=GridBagConstraints.HORIZONTAL;
		add(titulo,contraints);



		//creacion de las primeras instrucciones del juego
		instrucciones= new JTextArea(2,40);
		instrucciones.setText("INGRESA TU APUESTA EN NUMEROS ENTEROS POR EJEMPLO : 10$ , 20 $ ");
		instrucciones.setEditable(false);
		Font font = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,20);
		instrucciones.setFont(font);
		instrucciones.setForeground(new Color(0,0,0));

		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.fill=GridBagConstraints.NONE;


		add(instrucciones,contraints);


		//aqui es donde se crean y se ponen la apuesta 
		zonaTexto= new JPanel();
		zonaTexto.setLayout(new FlowLayout());
		zonaTexto.setBackground(Color.WHITE);
		Font font2 = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,25);

		zonaApuesta = new JLabel ("Escribe Tu Apuesta");
		zonaApuesta.setFont(font2);
		zonaApuesta.setForeground(new Color(250,0,250));
		apuesta = new JTextField(3);


		zonaTexto.add(zonaApuesta);
		zonaTexto.add(apuesta);


		contraints.gridx=0;
		contraints.gridy=3;
		contraints.gridwidth=1;
		contraints.gridheight=2;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.CENTER;

		add(zonaTexto, contraints);

		//creacion de la zona de los botones  con su respectivo boton 
		zonaBoton = new JPanel();
		zonaBoton.addMouseListener(escucha);
		zonaBoton.setBackground(new java.awt.Color(200,200,100));
		zonaBoton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		zonaBoton.setPreferredSize(new Dimension (800,50));

		boton= new JLabel("Apostar");
		Font font3 = new Font(Font.SERIF, Font.BOLD+Font.ITALIC,20);
		boton.setFont(font3);

		zonaBoton.add(boton);

		contraints.gridx=0;
		contraints.gridy=5;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.CENTER;
		add(zonaBoton,contraints);
	}

	/**
	 * Dar apuesta.
	 *
	 * @return the int
	 */
	public static  int  darApuesta() {
		return cantidadApuesta;
	}

	/**
	 * Mostrar ganador en pantalla.
	 */
	public void mostrarGanadorEnPantalla () {

		Icon icon;
		icon = new ImageIcon("src/resources/resultado1.png");
		JOptionPane.showMessageDialog(rootPane, "El Ganador de la Ronda es  "+ControlPoker.nombreGanador + "  juega tantas rondas como quieras , oprimiendo el boton de reiciar juego", "Resultado Ronda", JOptionPane.DEFAULT_OPTION , icon);



		System.out.println(PanelJugadores.getDineroJugador());
	}

	/**
	 * Restringir apuesta.
	 *
	 * @param apuestaSimulada the apuesta simulada
	 * @return the int
	 */
	public int restringirApuesta(int apuestaSimulada) {


		if (ControlPoker.nombreJugadorMano == "Eres Tu") {
			return condicionApuesta = 2;
		} else {

			if (apuestaSimulada != cantidadApuesta) {
				JOptionPane.showMessageDialog(rootPane, "Por favor solo iguala la apuesta para continuar el juego !! ");
				return condicionApuesta=1;
			} else if (apuestaSimulada == cantidadApuesta){
				return condicionApuesta=2;
			}

		}
		return condicionApuesta;
	}

	/**
	 * Dinero insuficiente para apostar.
	 *
	 * @return the int
	 */
	public int dineroInsuficienteParaApostar() {
		if ( cantidadApuesta > PanelJugadores.getDineroJugador()) {
			JOptionPane.showMessageDialog(rootPane, "No tienes sufiente dinero para realizar una apuesta por este monto, por favor ingresa una apuesta deacuerdo a tu dinero disponible !!");
			return condicionApuesta2 = 1;
		} else {
			return condicionApuesta2 = 2;
		}
	}


	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter {

		/**
		 * Mouse clicked.
		 *
		 * @param eventMouse the event mouse
		 */


		public void mouseClicked(MouseEvent eventMouse) {

			if (ControlPoker.estadoApostar==1) {

				// esta funcion transforma el texto ingresado en un string
				String respuesta1 = apuesta.getText();



				// esta funcion analiza si lo que entro es un numero o no 
				try {
					//  Block of code to try

					cantidadApuesta = Integer.parseInt(respuesta1); 
					int i=Integer.parseInt(respuesta1); 
					if (dineroInsuficienteParaApostar() ==2) {

						//lanzara un mensaje de que el barco fue puesto 
						JOptionPane.showMessageDialog(rootPane, "APUESTA REALIZADA !!    los demas jugadores han igualado la apuesta");
						System.out.println(respuesta1);

						//aqui debe entrar la funcion añade la apuesta hecha a la apuesta total 
						MesaJuego.igualarApuesta();
						PanelJugadores.disminuirDinero();
						MesaJuego.deshabilitarBotonApostar();


						//cerrara la ventana de apuestas
						vista.dispose();

					}

				}
				catch(Exception e) {
					//  Block of code to handle errors
					JOptionPane.showMessageDialog(rootPane, "INGRESA SOLO NUMEROS ENTEROS, EJEMPLO 10, 20... ");

				}

			} else {

				// esta funcion transforma el texto ingresado en un string
				String respuesta1 = apuesta.getText();


				// esta funcion analiza si lo que entro es un numero o no 
				try {
					//  Block of code to try
					cantidadApuesta = Integer.parseInt(respuesta1); 
					int i=Integer.parseInt(respuesta1); 


					if (dineroInsuficienteParaApostar()==2) {

						restringirApuesta ( ControlPoker.apuestaAleatoria2);

						if (condicionApuesta==2){
							//lanzara un mensaje de que el barco fue puesto 
							JOptionPane.showMessageDialog(rootPane, "APUESTA REALIZADA !!    los demas jugadores han igualado la apuesta");
							System.out.println(respuesta1);

							//aqui debe entrar la funcion añade la apuesta hecha a la apuesta total 
							MesaJuego.igualarApuesta();
							PanelJugadores.disminuirDinero();
							MesaJuego.deshabilitarBotonApostar();


							//cerrara la ventana de apuestas
							vista.dispose();

							mostrarGanadorEnPantalla();
						}
					}


				}
				catch(NumberFormatException num) {
					//  Block of code to handle errors
					JOptionPane.showMessageDialog(rootPane, "INGRESA SOLO NUMEROS ENTEROS, EJEMPLO 10, 20... ");

				}

				catch(Exception e) {
					//  Block of code to handle errors
					JOptionPane.showMessageDialog(rootPane, "Por favor solo iguala la apuesta!! ");

				}
			}


		}
	}


}

