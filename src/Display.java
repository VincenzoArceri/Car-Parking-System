/**
 * Classe astratta che contiene il metodo updateMe, 
 * implementato concretamente dai dai diversi display, che nel nostro caso visualizzano
 * la media oraria di automobili entrate e il numero di posti auto disponibili.
 * 
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 */
public abstract class Display {
	public abstract void updateMe(double toBeDisplaied);
}
