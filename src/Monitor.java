/**
 * La classe Monitor estende la classe astratta Node e implementa l'interfaccia
 * RxCommunication per la sola ricezione di dati.
 * 
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 */
public class Monitor extends Node implements RxCommunication {
	
	private AverageDisplay averageDisplay;
	private FreeParksDisplay freeParksDisplay;
	
	/**
	 * Variabile booleana utilizzata per indicare la tipologia del canale di comunicazione:
	 * true indica una connessione di tipo wireless
	 * false indica una connessione cablata
	 */
	public boolean isWireless;
	
	/**
	 * Il costruttore della classe.
	 * @param isWireless: indica se il nodo comunica wireless
	 * @param name: il nome identificativo del nodo.
	 */
	public Monitor(String name, boolean isWireless) {
		this.nodeName = name;
		this.isWireless = isWireless;
		this.averageDisplay = new AverageDisplay();
		this.freeParksDisplay = new FreeParksDisplay();
	}
	
	/**
	 * Il metodo publico chiamato da ProcessingUnit per inviare i dati delle
	 * computazioni al monitor, inoltre chiama il metodo read
	 * per leggere il dato.
	 */
	@Override
	public void receive(Object... args) {
		System.out.println("Ho ricevuto il dato");
		this.read(args);
	}
	
	/**
	 * Il metodo read chiama updateMe per stampare sul display
	 * i dati ricevuti dalla ProcessingUnit. Per gli averageDisplay 
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
