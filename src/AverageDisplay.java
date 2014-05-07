
public class AverageDisplay extends Display {

	@Override
	public void updateMe(double toBeDisplaied) {
		System.out.println("Media aggiornata: " +  toBeDisplaied);
		Simulator.Average.setText("" + (double) Math.rint(toBeDisplaied*10000)/10000);
	}
}
