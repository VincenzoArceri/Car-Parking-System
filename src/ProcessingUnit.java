
/**
 * ProcessingUnit estende la classe astratta node e implementa l'interfaccia TxRxComunication per 
 * ricevere e inviare dati, le interfacce Add, Sub, Averege per effettuare i calcoli.
 * La classe riceve i dati da Detector, effettua i calcoli e li invia al Monitor.
 * 
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 */

import javax.swing.border.TitledBorder;

public class ProcessingUnit extends Node implements TxRxCommunication, Add, Sub, Average {
	
	private Monitor monitor;
	
	/**
	 * Totale macchine entrate da quando il sistema è attivo
	 */
	private int totalCars = 0;
	
	/**
	 * Totale dei posti liberi, inizialmente sono 500
	 */
	public int freeParkingPlaces = 500;
	
	/**
	 * Il costruttore della classe ProcessingUnit.
	 * @param name: nome identificativo dell'oggetto ProcessingUnit 
	 */
	public ProcessingUnit(String name) {
		this.nodeName = name;
	}
	
	/**
	 * Metodo pubblico chiamato da Detector ogni volta che deve
	 * inviare un dato alla ProcessingUnit, quest'ultimo lo riceve e chiama
	 * read per leggerlo.
	 */
	@Override
	public void receive(Object... args) {
		System.out.println("Ho ricevuto un dato da Detector");
		Simulator.Calculate.setBorder(new TitledBorder(""));
		Simulator.Calculate.setText("Calculating...");
			
		if ((args.length == 1) && (args[0] instanceof Boolean))
			this.read(args[0]);
	}
	
	/**
	 * Metodo usato per leggere il dato ricevuto da Detector che chiama
	 * il metodo calculate per eseguire i calcoli necessari.
	 * 
	 * @param args: lista di oggetti ricevuti
	 */
	@Override
	public void read(Object... args) {
		System.out.println("Sto preparando il dato da essere processato");
		this.calculate((Boolean) args[0] ? 1 : -1);		
	}

	/**
	 * Metodo usato per inviare il dato a Monitor chiamando 
	 * il metodo pubblico di Monitor, receive().
	 * 
	 * @param args: lista di oggeti da inviare al Monitor
	 */
	@Override
	public void send(Object... args) {
		System.out.println("Sto inviando dei dati al monitor");
		monitor.receive(args);
	}

	/**
	 * Prepara i dati che devono essere inviati al monitor
	 * e chiama send().
	 * 
	 * @param args: lista di oggetti da prepare per essere inviati
	 */
	@Override
	public void set(Object... args) {
		System.out.println("Sto preparando dei dati per essere inviati");
		
		if (args.length == 2)
			this.send(args);	
	}
	
	/**
	 * Produce il calcolo per i posti disponibili e la media.
	 * La media è intesa come il numero di macchine
	 * entrate in un arco di tempo.
	 * 
	 * @param args: lista di oggetti da elaborare per produrre la media e il numero di posti liberi
	 */
	public void calculate(Object... args) {
		int op = (Integer) args[0];

		if (op == -1) {
			freeParkingPlaces++;
			this.send(freeParkingPlaces);
		} else {
			freeParkingPlaces--;
			totalCars++;
			Simulator.Cars.setText("" + totalCars);
			long hoursElapsed = 3 + ((System.currentTimeMillis() - Simulator.initialTime) / 1000) / 60;
			this.send(freeParkingPlaces, this.average(totalCars, hoursElapsed));
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Il metodo Average calcola la media aritmetica prendendo in input due long
	 * e restituendo la media.
	 * 
	 * @param sum: somma su cui effettuare la media
	 * @param hours: totale ore su cui effettuare la media
	 */
	@Override
	public double average(long sum, long hours) {
		return (double) sum / (double) hours;
	}

	/**
	 * Il metodo sub produce una sottrazione prendendo in input due long.
	 */
	@Override
	public long sub(long first, long second) {
		return first - second;
	}
	
	/**
	 * Il metodo add produce un'addizione prendendo in input due long.
	 */
	@Override
	public long add(long first, long second) {
		return first + second;
	}

	/**
	 * Registra il monitor come observer della processing unit.
	 * 
	 * @param who: oggetto che deve essere registrato come Observer
	 */
	@Override
	public void registerObserver(Object who) {
		System.out.println("Monitor registrata");
		if (who instanceof Monitor)
			monitor = (Monitor) who;		
	}

	/**
	 * Deregistra il monitor.
	 * 
	 * @param who: oggetto da deregistrare
	 */
	@Override
	public void unregisterObserver(Object who) {
		System.out.println("Monitor deregistrata");
		if (who instanceof ProcessingUnit)
			monitor = null;	
	}
}
