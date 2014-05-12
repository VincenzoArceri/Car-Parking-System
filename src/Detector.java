
/**
 * La classe Detector estende la classe astratta Node e implementa
 * l'interfaccia TxCommunication. 
 * Il detector segnala l'entrata e l'uscita di auto alla processing unit.
 * 
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 */

public class Detector extends Node implements TxCommunication {
	
	/**
	 * Il limite massimo di macchine per il parcheggio
	 */
	public final static int maxCars = 500;
	private ProcessingUnit processingUnit;
	public boolean isWireless = false;
	
	/**
	 * Il costruttore della classe
	 * @param name: il nome identificativo del nodo
	 */
	public Detector(String name, boolean isWireless) {
		this.nodeName = name;
		this.isWireless = isWireless;
	}
	
	/**
	 * Metodo che chiama il metodo pubblico receive di ProcessingUnit, per l'invio
	 * di dati all'unit&agrave di calcolo.
	 * 
	 * @param args: il dato da inviare all'unit&agrave di processo.
	 */
	
	@Override
	public void send(Object... args) {
		System.out.println("Sto inviando un dato all'unità di processo");
		
		if ((processingUnit.isWireless) && (this.isWireless))
			processingUnit.receive((Boolean) args[0]);
	}

	/**
	 * Metodo per preparazione del dato da inviare con send(Object.. args). Se l'agomento in ingresso &egrave true, 
	 * invia true (segnala l'entrata di un'auto), se &egrave false, invia false (segnala l'uscita di un'auto).
	 * 
	 * @param args: dato da inviare alla processing unit
	 */
	
	@Override
	public void set(Object... args) {
		System.out.println("Sto preparando un dato");
		if ((args.length == 1) && (args[0] instanceof Boolean) && ((Boolean) args[0]))
			this.send(true);
		else if ((args.length == 1) && (args[0] instanceof Boolean) && (!(Boolean) args[0]))
			this.send(false);
	}
	
	/**
	 * Metodo per segnalare l'entrata di una macchina, se i posti auto disponibili
	 * sono maggiori di zero invia true al metodo set
	 */
	public void enteringCar() {
		if (processingUnit.freeParkingPlaces > 0)
			this.set(true);
	}
	
	/**
	 * Metodo per segnalare l'uscita di una macchina, 
	 * se i posti auto disponibili sono minori della capacit&agrave del parcheggio
	 * invia false al metodo set.
	 */
	public void exitingCar() {	
		if (processingUnit.freeParkingPlaces < maxCars)
			this.set(false);
	}

	/**
	 * Registra la processing unit come observer di detector.
	 */
	@Override
	public void registerObserver(Object who) {
		System.out.println("ProcessingUnit registrata");
		if (who instanceof ProcessingUnit)
			processingUnit = (ProcessingUnit) who;		
	}

	/**
	 * Deregistra la processing unit.
	 */
	@Override
	public void unregisterObserver(Object who) {
		System.out.println("ProcessingUnit deregistrata");
		if (who instanceof ProcessingUnit)
			processingUnit = null;	
	}
}
