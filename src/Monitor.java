/**
 * La classe Monitor estende la classe astratta node e implementa l'interfaccia
 * RxCommunication per la sola ricezione di dati.
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 *
 */
public class Monitor extends Node implements RxCommunication {
	
	AverageDisplay averageDisplay;
	FreeParksDisplay freeParksDisplay;
	/**
	 * il costruttore della classe.
	 * @param name il nome identificativo del nodo.
	 */
	public Monitor(String name) {
		this.nodeName = name;
		this.averageDisplay = new AverageDisplay();
		this.freeParksDisplay = new FreeParksDisplay();
	}
	/**
	 * il metodo public chiamato da Processing Unit per inviare i dati delle
	 * Computazioni al monitor, inoltre chiama il metodo read
	 * per leggere il dato.
	 */
	@Override
	public void receive(Object... args) {
		System.out.println("Ho ricevuto il dato");
		this.read(args);
	}
	/**
	 * Il metodo read chiama updateMe per stampare sul display
	 * i dati ricevuti dalla processing unit. Per gli averageDisplay 
	 * si stamper&agrave la media mentre per i freeParksDisplay
	 *  il numero di posti disponibili.
	 */
	@Override
	public void read(Object... args) {
		if (args.length == 2) {
			averageDisplay.updateMe(((Number)args[1]).doubleValue());
			freeParksDisplay.updateMe(((Number)args[0]).doubleValue());
		} else
			freeParksDisplay.updateMe(((Number)args[0]).doubleValue());
	}
}
