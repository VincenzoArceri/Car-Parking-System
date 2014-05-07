
public class Detector extends Node implements TxCommunication {
	private final static int maxCars = 500;
	private ProcessingUnit processingUnit;
	
	@Override
	public void send(Object... args) {
		System.out.println("Sto inviando un dato all'unità di processo");
		processingUnit.receive((boolean) args[0]);
		
	}

	@Override
	public void set(Object... args) {
		System.out.println("Sto preparando un dato");
		if ((args.length == 1) && (args[0] instanceof Boolean) && ((boolean) args[0]))
			this.send(true);
		else if ((args.length == 1) && (args[0] instanceof Boolean) && (!(boolean) args[0]))
			this.send(false);
	}
	
	public void enteringCar() {
		this.set(true);
	}
	
	public void exitingCar() {
		this.set(false);
	}

	@Override
	public void registerObserver(Object who) {
		System.out.println("ProcessingUnit registrata");
		if (who instanceof ProcessingUnit)
			processingUnit = (ProcessingUnit) who;		
	}

	@Override
	public void unregisterObserver(Object who) {
		System.out.println("ProcessingUnit deregistrata");
		if (who instanceof ProcessingUnit)
			processingUnit = null;	
	}
}
