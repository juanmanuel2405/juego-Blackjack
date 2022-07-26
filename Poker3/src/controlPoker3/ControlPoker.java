/*
 * Programacion Interactiva
 * Archivo: ControlPoker.java
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
package controlPoker3;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import modeloPoker3.Baraja;
import modeloPoker3.Carta;
import pokerVista3.MesaJuego;
import pokerVista3.PanelJugadores;
import pokerVista3.VistaApostar;
import pokerVista3.VistaPoker;
import pokerVista3.VistaScore;
import modeloPoker3.JugadoresSimulados;


// TODO: Auto-generated Javadoc
/**
 * The Class ControlPoker.
 */
public class ControlPoker {

	/** The Constant TOTAL_JUGADORES. */
	public static final int TOTAL_JUGADORES=5;

	/** The Constant NOMBRE_JUGADORES. */
	public static final String[] NOMBRE_JUGADORES = {"Esteban","Geider","Juan","Daniel"};

	/** The Constant NUMERO_CARTAS. */
	public static final int NUMERO_CARTAS=5;

	/** The vista poker. */
	//Vista
	private VistaPoker vistaPoker;

	/** The mesa juego. */
	private MesaJuego mesaJuego;


	/** The baraja. */
	//variables del juego
	private Baraja baraja;

	/** The manos jugadores. */
	private List<List<Carta>> manosJugadores;


	/** The turno. */
	//viariable para el manejo de hilos
	private int turno; //variable de control de turno hilos

	/** The descarte. */
	private int[] descarte = new int[TOTAL_JUGADORES-1];

	/** The bloqueo. */
	private Lock bloqueo = new ReentrantLock(); //manejo de sincronizacion

	/** The esperar turno. */
	private Condition esperarTurno = bloqueo.newCondition(); //manejo de sincronizacion	

	/** The aleatorio. */
	private  Random aleatorio;

	/** The apuesta aleatoria. */
	private int selecionarJugador, apuestaAleatoria;

	/** The apuesta aleatoria 2. */
	public static int apuestaAleatoria2;

	/** The nombre jugador mano. */
	public static String nombreJugadorMano;

	/** The nombre ganador. */
	public static String nombreGanador;

	/** The estado apostar. */
	public static int  estadoApostar = 1;



	/**
	 * Instantiates a new control poker.
	 */
	public ControlPoker() {
		//generar manos iniciales de los jugadores, generar carta común, iniciar la Vista	
		manosJugadores = new ArrayList<List<Carta>>();
		iniciarJuego();
		vistaPoker = new VistaPoker(NOMBRE_JUGADORES, manosJugadores,this);
		aleatorio= new Random();
		dineroInsuficiente();
		jugadorMano();

	}

	/**
	 * Iniciar juego.
	 */
	private void iniciarJuego() {
		baraja = new Baraja();
		for(int i=0; i<TOTAL_JUGADORES;i++) {
			manosJugadores.add(seleccionarCartas());
		}

	}


	/**
	 * Jugador mano.
	 * 
	 * funcion que nos da aleatoriamente a un jugador mano para cada ronda 
	 */
	public void jugadorMano() {
		selecionarJugador = aleatorio.nextInt(5)+1;
		apuestaAleatoria = (aleatorio.nextInt(10)+1)*10;

		switch (selecionarJugador){
		case 1: nombreJugadorMano = "Esteban";
		JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano es "+ nombreJugadorMano + "...   Aposto " +apuestaAleatoria + "$   los demas jugadores han igualado la apuesta" );
		MesaJuego.apuestaJugadorMano(apuestaAleatoria);
		break;

		case 2: nombreJugadorMano = "Geider";
		JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano es "+ nombreJugadorMano +"...   Aposto  " +apuestaAleatoria +"$    los demas jugadores han igualado la apuesta" );
		MesaJuego.apuestaJugadorMano(apuestaAleatoria);
		break;

		case 3:  nombreJugadorMano = "Juan";
		JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano es "+ nombreJugadorMano+ "...   Aposto  " +apuestaAleatoria + "$   los demas jugadores han igualado la apuesta"  );
		MesaJuego.apuestaJugadorMano(apuestaAleatoria);
		break;

		case 4:  nombreJugadorMano = "Daniel";
		JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano es "+ nombreJugadorMano +"...   Aposto  " +apuestaAleatoria + "$   los demas jugadores han igualado la apuesta" );
		MesaJuego.apuestaJugadorMano(apuestaAleatoria);
		break;

		case 5:  nombreJugadorMano = "Eres Tu";
		JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano "+ nombreJugadorMano +" realiza tu apuesta, puedes apostar o no ");

		break;
		}

	}


	/**
	 * Segunda ronda mismo jugador mano.
	 */
	public void segundaRondaMismoJugadorMano() {

		if (nombreJugadorMano == "Eres Tu") {
			JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano "+ nombreJugadorMano +" Empiezas la Segunda Ronda de Apuesta, Tu apuesta aqui es obligatoria o debes retirarte si no tienes dinero  ");
		} else {

			apuestaAleatoria2 = (aleatorio.nextInt(20)+1)*10;

			JOptionPane.showMessageDialog(vistaPoker, "El jugador Mano es "+ nombreJugadorMano +"... Aposto   " +apuestaAleatoria2 + "$ "  );
		}

	}



	/**
	 * Descarte jugador humano.
	 *
	 * @param manoJugadorHumano the mano jugador humano
	 */
	public void descarteJugadorHumano(List<Carta> manoJugadorHumano) {
		//cambiar la mano del jugador humano 
		int	cartasFaltantes = 5 - manoJugadorHumano.size();
		System.out.println(cartasFaltantes);

		for (int i= 0; i < cartasFaltantes; i++) {
			manoJugadorHumano.add(baraja.getCarta());
		}


		manosJugadores.remove(4);
		manosJugadores.add(manoJugadorHumano);



		System.out.println("se descarto Jugadormano");
		//iniciarHilos
		iniciarJugadoresSimulados();

		VistaPoker.mensaje.setText("segunda Ronda de descartes: iguala la apuesta o retirate, y descarta las cartas que no necesites");

		estadoApostar = 2;
		dineroInsuficiente();
		segundaRondaMismoJugadorMano();
		mostrarGanador();

	}

	/**
	 * Iniciar jugadores simulados.
	 */
	private void iniciarJugadoresSimulados() {
		// TODO Auto-generated method stub
		turno=1;
		//crear los hilos e iniciarlos

		JugadoresSimulados jugador1 = new JugadoresSimulados(1, JugadoresSimulados.DESENTE,ControlPoker.NOMBRE_JUGADORES[0], this); 
		JugadoresSimulados jugador2 = new JugadoresSimulados(2, JugadoresSimulados.MESURADO,ControlPoker.NOMBRE_JUGADORES[1], this);
		JugadoresSimulados jugador3 = new JugadoresSimulados(3, JugadoresSimulados.SEMI,ControlPoker.NOMBRE_JUGADORES[2], this); 
		JugadoresSimulados jugador4 = new JugadoresSimulados(4, JugadoresSimulados.DESENTE,ControlPoker.NOMBRE_JUGADORES[3], this);

		ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
		ejecutorSubprocesos.execute(jugador1); 
		ejecutorSubprocesos.execute(jugador2);
		ejecutorSubprocesos.execute(jugador3); 
		ejecutorSubprocesos.execute(jugador4);

		ejecutorSubprocesos.shutdown();
	}

	/**
	 * Turnos.
	 *
	 * @param jugador the jugador
	 * @param cartasPedidas the cartas pedidas
	 * @param nombreJugador the nombre jugador
	 */
	//método a sincronizar - condition es el turno 
	public void turnos(int jugador, int cartasPedidas, String nombreJugador) {
		//bloquear la clase
		bloqueo.lock();
		try {
			//validar condición de ejecucion para el hilo
			while(jugador!=turno) { //turno= 1 le toca a jugador 1 y turno=2 le toca a jugador 2
				//dormir al jugador porque no es su turno
				System.out.println("Jugador "+nombreJugador+" intenta entrar y es mandado a esperar turno");
				esperarTurno.await();	  
			}

			//ejecutar tarea, variar condición de ejecucion, desbloquear el objeto
			descarte[turno-1]=cartasPedidas;
			System.out.println("Jugador "+nombreJugador+" pidió "+descarte[turno-1]+" cartas");
			turno++;
			esperarTurno.signalAll();		 

		}catch(InterruptedException e) {
			e.printStackTrace();
			System.out.println("aqui");
		}finally {
			bloqueo.unlock();
			if(turno==5) {
				darCartas();
			}
		} 
	}

	/**
	 * Dar cartas.
	 */
	private void darCartas() {	
		//cartas para jugadores simulados
		for(int i=0;i<manosJugadores.size()-1;i++) { 

			if(descarte[i]==4) { //dar dos cartas al jugador
				//elimina el descarte de la mano original
				manosJugadores.get(i).remove(descarte[i]);
				//asigna la nueva carta
				asignarCartas(manosJugadores.get(i)); 
			} else {//dar al jugador la carta pedida
				//elimina el descarte de la mano original
				manosJugadores.get(i).remove(descarte[i]);
				//asigna la nueva carta
				asignarCartas(manosJugadores.get(i));
			}

		}


		//cartas para jugador Humano 
		asignarCartas(manosJugadores.get(4));


		//int ganador = determinarGanador();

		VistaPoker.mensaje.setText("");
		VistaPoker.mensaje.setText("Segundo Descarte: apuesta o retirate si no tienes dinero, despues de apostar se abre mano ");
		vistaPoker.actualizarVistaPoker(manosJugadores);


	}

	/**
	 * Asignar cartas.
	 *
	 * @param manoJugador the mano jugador
	 */
	//calcula cuantas cartas dar a cada jugador, luego del descarte
	private void asignarCartas(List<Carta> manoJugador) {
		if(manoJugador.size()==0) {//asignar 5 cartas
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());

		}else if(manoJugador.size()==1) {//dar 4 carta
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());

		} else if (manoJugador.size()==2) {//asignar 3 cartas
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());

		}else if(manoJugador.size()==3) {//dar 2 carta
			manoJugador.add(baraja.getCarta());
			manoJugador.add(baraja.getCarta());

		} else if (manoJugador.size()==4) {//dar 1 carta
			manoJugador.add(baraja.getCarta());
		} else {
			System.out.println("el jugador no descarto ninguna carta");
		}
	}			


	/**
	 * Seleccionar cartas.
	 *
	 * @return the array list
	 */
	private ArrayList<Carta> seleccionarCartas() {
		// TODO Auto-generated method stub
		ArrayList<Carta> manoJugador = new ArrayList<Carta>();
		//se dan 2 cartas al jugador
		manoJugador.add(baraja.getCarta());
		manoJugador.add(baraja.getCarta());
		manoJugador.add(baraja.getCarta());
		manoJugador.add(baraja.getCarta());
		manoJugador.add(baraja.getCarta());
		return manoJugador;
	}

	/**
	 * Reiniciar juego.
	 *
	 * @return the list
	 */
	public List<List<Carta>> reiniciarJuego() {
		manosJugadores.clear();
		jugadorMano();
		dineroInsuficiente();
		iniciarJuego();
		return manosJugadores;

	}	


	/**
	 * Gets the valor carta.
	 *
	 * @param indexBaraja the index baraja
	 * @param indexCarta the index carta
	 * @return the valor carta
	 */
	public int getValorCarta(int indexBaraja, int indexCarta) {
		return manosJugadores.get(indexBaraja).get(indexCarta).getValorNumerico();
	}

	/**
	 * Gets the palo carta.
	 *
	 * @param indexBaraja the index baraja
	 * @param indexCarta the index carta
	 * @return the palo carta
	 */
	public String getPaloCarta(int indexBaraja, int indexCarta) {
		return manosJugadores.get(indexBaraja).get(indexCarta).getPalo();
	}


	/**
	 * Tipos iguales.
	 *
	 * @param indexBaraja the index baraja
	 * @return true, if successful
	 */
	//FUNCION QUE DETERMINA SI TODAS LAS CARTAS DE LA BARAJA SON DEL MISMO PALO, DE NO SER ASI RETORNA UN FALSE
	public boolean tiposIguales(int indexBaraja){
		boolean res = true;
		for(int i = 0; i < manosJugadores.get(indexBaraja).size();i++){
			if(getPaloCarta(indexBaraja,0) != getPaloCarta(indexBaraja,i)){
				res = false;
			}
		}
		return res;
	}


	/**
	 * Id pares.
	 *
	 * @param indexBaraja the index baraja
	 * @return the int
	 */
	//FUNCION PARA DETERMINAR LA CANTIDAD DE PAREJAS DE CARTAS IGUALES QUE HAY EN LA BARAJA DE UN JUGADOR (RETORNA UN 0, UN 1 O UN 2)
	public int idPares (int indexBaraja) {
		int cartasValidas = 0;
		int cantidadPares = 0;
		for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
			int cartaComparar = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
			for(int j = 0; j < manosJugadores.get(indexBaraja).size(); j++) {
				if(cartaComparar == manosJugadores.get(indexBaraja).get(j).getValorNumerico()) {
					cartasValidas++;
				}
			}
		}
		if(cartasValidas >= 6 && cartasValidas < 9) {
			cantidadPares = 1;
		}else {
			if(cartasValidas >= 9 && cartasValidas < 11) {
				cantidadPares = 2;
			}
		}
		return cantidadPares;
	}

	/**
	 * Id trios.
	 *
	 * @param indexBaraja the index baraja
	 * @return the int
	 */
	//FUNCION QUE DETERMINA LA CANTIDAD DE TRIOS QUE HAY EN LA BARAJA DE UN JUGADOR (RETORNA UN 0 O UN 1)
	public int idTrios (int indexBaraja) {
		int cartasValidas = 0;
		int cantidadTrios = 0;
		for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
			int cartaComparar = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
			for(int j = 0; j < manosJugadores.get(indexBaraja).size(); j++) {
				if(cartaComparar == manosJugadores.get(indexBaraja).get(j).getValorNumerico()) {
					cartasValidas++;
				}
			}
		}
		if(cartasValidas >= 11 && cartasValidas < 16) {
			cantidadTrios = 1;
		}
		return cantidadTrios;	
	}


	/**
	 * Gets the carta pequeña.
	 *
	 * @param indexBaraja the index baraja
	 * @return the carta pequeña
	 */
	//FUNCION QUE RETORNA LA CARTA CON MENOR VALOR NUMERICO QUE HAYA EN LA BARAJA DE UN JUGADOR
	public int getCartaPequeña(int indexBaraja) {
		int cartaPequeña = 0;
		int contador = 1;
		for(int i = 0; i < manosJugadores.get(indexBaraja).size()-1; i++) {
			int carta1 = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
			int carta2 = manosJugadores.get(indexBaraja).get(contador).getValorNumerico();
			if(carta1 < carta2) {
				cartaPequeña = carta1;
				contador++;
			}else {
				cartaPequeña = carta2;
				contador++;
			}   			
		}
		return cartaPequeña;   		
	}


	/**
	 * Gets the carta grande.
	 *
	 * @param indexBaraja the index baraja
	 * @return the carta grande
	 */
	//FUNCION QUE RETORNA LA CARTA CON MAYOR VALOR NUMERICO QUE HAYA EN LA BARAJA DE UN JUGADOR
	public int getCartaGrande(int indexBaraja) {
		int cartaGrande = 0;
		int contador = 1;
		for(int i = 0; i < manosJugadores.get(indexBaraja).size()-1; i++) {
			int carta1 = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
			int carta2 = manosJugadores.get(indexBaraja).get(contador).getValorNumerico();
			if(carta1 > carta2) {
				cartaGrande = carta1;
				contador++;
			}else {
				cartaGrande = carta2;
				contador++;
			}   			
		}
		return cartaGrande;   		
	}



	/**
	 * Gets the value.
	 *
	 * @param indexBaraja the index baraja
	 * @return the value
	 */
	//FUNCION QUE ANALIZA LA BARAJA DE UN JUGADOR Y LE ASIGNA UN VALOR NUMERICO, DEPENDIENDO DE LAS CONDICIONES DE VICTORIA, DESPUES SE RETORNA ESTE VALOR, PARA DETERMINAR EL GANADOR
	public int getValue(int indexBaraja) {
		int cartaPequeña = getCartaPequeña(indexBaraja);
		int cartaGrande = getCartaGrande(indexBaraja);
		int valorBaraja = 0;
		boolean tiposIguales = tiposIguales(indexBaraja);
		int pares = idPares(indexBaraja); //SOLO PUEDEN HABER 1 O 2 PAREJAS
		int trios = idTrios (indexBaraja); //SOLO PUEDE HABER MAXIMO 1 TRIO
		


		//1-CONDICION ESCALERA REAL (LAS 5 CARTAS DEL MISMO PALO Y CON VALORES NUMERICOS DEL 10 AL 14)
		if(tiposIguales){
			int cartasValidas = 0;
			for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
				if(manosJugadores.get(indexBaraja).get(i).getValorNumerico() > 9) {
					cartasValidas ++;
				}
			}
			if(cartasValidas == 5) {
				valorBaraja = 10;
				//System.out.println("el jugador "+indexBaraja+" tiene ESCALERA REAL");
				return valorBaraja;
			}else {
				valorBaraja = 0;
			}
		}        	

		//2-CONDICION POKER (4 CARTAS CON EL MISMO VALOR NUMERICO)
		if(valorBaraja == 0) {
			int cartasValidas = 0;
			for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
				int cartaComparar = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
				for(int j = 0; j < manosJugadores.get(indexBaraja).size(); j++) {
					if(cartaComparar == manosJugadores.get(indexBaraja).get(j).getValorNumerico()) {
						cartasValidas++;
					}
				}
			}
			if(cartasValidas >= 16) {
				valorBaraja = 9;
				//System.out.println("el jugador "+indexBaraja+" tiene POKER");
				return valorBaraja;
			}else {
				valorBaraja = 0; 
			}
		}

		//3-CONDICION ESCALERA COLOR (5 CARTAS SEGUIDAS DEL MISMO PALO)
		if(tiposIguales) {
			int cartasValidas = 0;
			for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
				int cartaComparar = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
				if(cartaComparar < (cartaPequeña+4)) {
					cartasValidas++;
				}
			}
			if(cartasValidas == 5) {
				valorBaraja = 8;
				//System.out.println("el jugador "+indexBaraja+" tiene ESCALERA COLOR");
				return valorBaraja;
			}else {
				valorBaraja = 0;
			}
		}

		//4-CONDICION FULL (3 CARTAS IGUALES MAS OTRAS 2 IGUALES)
		if(pares == 1 && trios == 1) {
			valorBaraja = 7;
			//System.out.println("el jugador "+indexBaraja+" tiene FULL");
			return valorBaraja;
		}else {
			valorBaraja = 0;
		}	


		//5-CONDICION COLOR (LAS 5 CARTAS DEL MISMO PALO)
		if(tiposIguales) {
			valorBaraja = 6;
			//System.out.println("el jugador "+indexBaraja+" tiene COLOR");
			return valorBaraja;
		}else {
			valorBaraja = -6;
		}

		//6-CONDICION ESCALERA (5 CARTAS CON VALOR NUMERICO CONSECUTIVO)
		if(valorBaraja == 0) {
			int cartasValidas = 0;
			for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
				int cartaComparar = manosJugadores.get(indexBaraja).get(i).getValorNumerico();
				if(cartaComparar < (cartaPequeña+4)) {
					cartasValidas++;
				}
			}
			if(cartasValidas == 5) {
				valorBaraja = 5;
				//System.out.println("el jugador "+indexBaraja+" tiene ESCALERA");
				return valorBaraja;
			}else {
				valorBaraja = 0;
			}
		}

		//7-CONDICION TRIO (3 CARTAS CON EL MISMO VALOR NUMERICO)
		if(trios == 1) {
			valorBaraja = 4;
			//System.out.println("el jugador "+indexBaraja+" tiene TRIO");
			return valorBaraja;
		}else {
			valorBaraja = 0;
		}

		//8-CONDICION DOBLE PAREJA (DOS PAREJAS DE CARTAS IGUALES)
		if(pares == 2) {
			valorBaraja = 3;
			//System.out.println("el jugador "+indexBaraja+" tiene DOBLE PAREJA");
			return valorBaraja; 
		}else{
			valorBaraja = 0;
		}

		//9-PAREJA (DOS CARTAS IGUALES)
		if(pares == 1) {
			valorBaraja = 2;
			//System.out.println("el jugador "+indexBaraja+" tiene PAREJA");
			return valorBaraja;
		}else {
			valorBaraja = 0;
		}

		//10-AS (CARTA CON MAYOR VALOR NUMERICO)
		if(cartaGrande == 14) {
			valorBaraja = 1;
			//System.out.println("el jugador "+indexBaraja+" tiene AS");
			return valorBaraja;
		}else {
			valorBaraja = 0;
		}
		return valorBaraja;
	}



	/**
	 * Gets the valor num baraja.
	 *
	 * @param indexBaraja the index baraja
	 * @return the valor num baraja
	 */
	//OBTENER LA SUMA DEL VALOR NUMERICO DE CADA CARTA DE LA BARAJA DEL JUGADOR QUE SE LE INDIQUE    
	public int getValorNumBaraja(int indexBaraja) {
		int valorNumBaraja = 0;
		for(int i = 0; i < manosJugadores.get(indexBaraja).size(); i++) {
			valorNumBaraja+=manosJugadores.get(indexBaraja).get(i).getValorNumerico();
		}
		return valorNumBaraja;
	}

	/**
	 * Determinar ganador.
	 */
	//FUNCION QUE COMPARA EL VALOR DE LA BARAJA DE CADA JUGADOR Y DETERMINA AL GANADOR (LA BARAJA CON EL MAYOR VALOR)
	public void determinarGanador() {
		//VALOR DE LA BARAJA DEL JUGADOR 1
		int P1 = getValue(0);
		//VALOR NUMERICO DE LA BARAJA DEL JUGADOR 1 (SOLO SE USARA EN CASO DE EMPATE)
		int VP1 = getValorNumBaraja(0);

		//VALOR DE LA BARAJA DEL JUGADOR 2
		int P2 = getValue(1);
		//VALOR NUMERICO DE LA BARAJA DEL JUGADOR 2 (SOLO SE USARA EN CASO DE EMPATE)
		int VP2 = getValorNumBaraja(1);

		//VALOR DE LA BVARAJA DEL JUGADOR 3
		int P3 = getValue(2);
		//VALOR NUMERICO DE LA BARAJA DEL JUGADOR 3 (SOLO SE USARA EN CASO DE EMPATE)
		int VP3 = getValorNumBaraja(2);

		//VALOR DE LA BARAJA DEL JUGADOR 4
		int P4 = getValue(3);
		//VALOR NUMERICO DE LA BARAJA DEL JUGADOR 4 (SOLO SE USARA EN CASO DE EMPATE)
		int VP4 = getValorNumBaraja(3);
		//VALOR DE LA BARAJA DEL JUGADOR 5

		int P5 = getValue(4);
		//VALOR NUMERICO DE LA BARAJA DEL JUGADOR 5 (SOLO SE USARA EN CASO DE EMPATE)
		int VP5 = getValorNumBaraja(4);

		int ganador = 0;

		//ARREGLO CON LOS VALORES DE LAS BARAJAS DE CADA JUGDAOR (DE ESTA MANERA ES MAS FACIL COMPARARLOS)
		int[] valoresBarajas = new int[] {P1,P2,P3,P4,P5};
		int[] valoresNumBarajas = new int[] {VP1, VP2, VP3, VP4, VP5};

		
		for(int i = 0; i < valoresBarajas.length-1; i++) {
			if(valoresBarajas[i] > valoresBarajas[i+1]) {
				ganador = valoresBarajas[i];	
			}else if(valoresBarajas[i] == valoresBarajas[i+1]) {
				if(valoresNumBarajas[i]>valoresNumBarajas[i+1]) {
					ganador = valoresBarajas[i];
				}else {
					ganador = valoresBarajas[i+1];
				}
			}else {
				ganador = valoresBarajas[i+1];
			}
		}
		if( ganador == P1 ) {
			nombreGanador= "Esteban";
		} else if ( ganador == P2 ) {
			nombreGanador="Geider";
		}else if ( ganador == P3 ) {
			nombreGanador= "Juan";
		}else if ( ganador == P4 ) {
			nombreGanador= "Daniel";
		}else if ( ganador == P5 ) {
			nombreGanador= "Eres el Ganador";
			PanelJugadores.recogerDineroGanar();

		}

	}


	/**
	 * Mostrar ganador.
	 */
	public void mostrarGanador() {
		if (estadoApostar==2) {
			determinarGanador();
		}
	}


	/**
	 * Dinero insuficiente.
	 */
	public void dineroInsuficiente() {
		
		//esta funcion comienza la vista Apostar 
		
		if (PanelJugadores.getDineroJugador() <= 0) {
			int confirmado = JOptionPane.showConfirmDialog(vistaPoker,"Ya no tienes dinero para seguir jugando, suerte para la Proxima!! , por favor reinicia el programa nuevamente si quieres volver a jugar!! \n ¿Quieres Cerrar el programa?");

			if (JOptionPane.OK_OPTION == confirmado) {
				System.exit(0);
			}	else {
				JOptionPane.showMessageDialog(vistaPoker, "Gracias Por Jugar, pero por favor cierra el juego y vuelve a comenzar");
			}
		}
		
		

	} 



}
