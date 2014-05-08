
public class FreeParksDisplay extends Display {

	@Override
	public void updateMe(double toBeDisplaied) {
		System.out.println("Parcheggi liberi aggiornati: " +  (int) toBeDisplaied);
		Simulator.FreePark.setText("" + (int) toBeDisplaied);
	}
}
