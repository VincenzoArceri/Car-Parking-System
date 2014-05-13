
/**
 * Classe che estende la classe astratta Display,
 * contiene un metodo updateMe chiamato per aggiornare il numero di
 * parcheggi liberi del Display.
 * 
 * @author Vincenzo Arceri, Matteo Calabria, Pietro Musoni, Carlo Tacchella
 */
public class FreeParksDisplay extends Display {

	/**
	 * Il metodo serve per aggiornare il numero di posti disponibili nel parcheggio,
	 * cambiato di conseguenza all'entrata o all'uscita di un'automobile.
	 * 
	 * @param toBeDisplaied: dato da visualizzare
	 */
	@Override
	public void updateMe(double toBeDisplaied) {
		System.out.println("FreeParksDisplay => Parcheggi liberi aggiornati: " +  (int) toBeDisplaied);
		Simulator.FreePark.setText("" + (int) toBeDisplaied);
	}
}
