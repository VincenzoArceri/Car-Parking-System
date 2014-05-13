
/**
 * La classe AverageDisplay estende la classe astratta Display e
 * implementa il metodo updateMe per aggiornare il dato della media aritmetica
 * sul monitor.
 *  @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 */

public class AverageDisplay extends Display {

	/**
	 * Il metodo serve per aggiornare la media 
	 * ed &egrave chiamato dalla classe Processing Unit
	 * ogni volta che questa produce una nuova media.
	 * 
	 * @param toBeDisplaied: dato da visualizzare
	 */
	
	@Override
	public void updateMe(double toBeDisplaied) {
		System.out.println("AverageDisplay => Media aggiornata: " +  toBeDisplaied);
		Simulator.Average.setText("" + (double) Math.rint(toBeDisplaied*10000)/10000);
	}
}
