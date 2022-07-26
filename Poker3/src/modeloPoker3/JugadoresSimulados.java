/*
 * Programacion Interactiva
 * Archivo: JugadoresSimulados.java
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

import java.util.Random;

import controlPoker3.ControlPoker;



// TODO: Auto-generated Javadoc
/**
 * The Class JugadoresSimulados.
 */
public class JugadoresSimulados implements Runnable {

	/** The Constant EXTREMO. */
	public static final int EXTREMO = 5;

	/** The Constant ARRIESGADO. */
	public static final int ARRIESGADO = 4;

	/** The Constant SEMI. */
	public static final int SEMI = 3;

	/** The Constant DESENTE. */
	public static final int DESENTE = 2;

	/** The Constant MESURADO. */
	public static final int MESURADO = 1;

	/** The nombre jugador. */
	private String nombreJugador;

	/** The turno. */
	private int turno;

	/** The tipo. */
	private int tipo;

	/** The descarte. */
	private int descarte;

	/** The random. */
	private Random random;

	/** The control poker. */
	private ControlPoker controlPoker; 

	/**
	 * Instantiates a new jugadores simulados.
	 *
	 * @param turno the turno
	 * @param tipo the tipo
	 * @param nombreJugador the nombre jugador
	 * @param controlPoker the control poker
	 */
	public JugadoresSimulados(int turno, int tipo, String nombreJugador, ControlPoker controlPoker) {
		this.turno = turno;
		this.tipo = tipo;
		this.nombreJugador = nombreJugador;
		this.controlPoker = controlPoker;
		random = new Random();
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(tipo==MESURADO) {
			descarte = random.nextInt(ControlPoker.NUMERO_CARTAS); //como es moderado indica el subindex de la carta que cambia
		}else if (tipo==DESENTE){
			descarte=2;		//si es arriesgado indica que cambia las 2 cartas
		}else if (tipo==SEMI){
			descarte=3;		//si es arriesgado indica que cambia las 3 cartas
		}else if (tipo==ARRIESGADO){
			descarte=4;		//si es arriesgado indica que cambia las 4 cartas
		}else if (tipo==EXTREMO){
			descarte=5;		//si es arriesgado indica que cambia las 5 cartas
		}
		controlPoker.turnos(turno,descarte,nombreJugador);
		System.out.println("Hilo "+nombreJugador+" termina "+descarte);
	}


}
