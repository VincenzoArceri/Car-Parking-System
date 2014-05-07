
public class Monitor extends Node implements RxCommunication {
	AverageDisplay averageDisplay;
	FreeParksDisplay freeParksDisplay;
	
	public Monitor() {
		this.averageDisplay = new AverageDisplay();
		this.freeParksDisplay = new FreeParksDisplay();
	}
	
	@Override
	public void receive(Object... args) {
		System.out.println("Ho ricevuto il dato");
		this.read(args);
	}

	@Override
	public void read(Object... args) {
		if (args.length == 2) {
			averageDisplay.updateMe(((Number)args[1]).doubleValue());
			freeParksDisplay.updateMe(((Number)args[0]).doubleValue());
		} else
			freeParksDisplay.updateMe(((Number)args[0]).doubleValue());
	}
}
