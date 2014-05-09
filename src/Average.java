

/**
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 * Interfaccia che estende NodeComputation.
 * All'interno di Average vi &egrave solamente il metodo average(long sum, long units) 
 * per il calcolo della media aritmetica. 
 */

public interface Average extends NodeComputation {
	public double average(long sum, long units);
}
