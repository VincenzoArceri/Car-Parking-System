/**
 * Interfaccia che estende a sua volta l'interfaccia NodeCommunication
 * e contiene i metodi per interfacciare i 
 * nodi adibiti sia alla ricezione di informazioni.
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 *
 */
public interface RxCommunication extends NodeCommunication {
	public void receive(Object... args);
	public void read(Object... args);
}
