/**
 * Interfaccia che estende a sua volta NodeCommunication
 * e contiene i metodi per i 
 * nodi adibiti solo all'invio di informazioni.
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 *
 */
public interface TxCommunication extends NodeCommunication {
	public void send(Object... args);
	public void set(Object... args);
	public void registerObserver(Object who);
	public void unregisterObserver(Object who);
}
