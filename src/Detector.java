
public class Detector extends Node implements TxCommunication {
	private final static int maxCars = 500;
	private ProcessingUnit processingUnit;
	
	@Override
	public void send(Object... args) {
		System.out.println("Sto inviando un dato all'unità di processo");
		processingUnit.receive((Boolean) args[0]);
		
	}

	@Override
	public void set(Object... args) {
		System.out.println("Sto preparando un dato");
		if ((args.length == 1) && (args[0] instanceof Boolean) && ((Boolean) args[0]))
			this.send(true);
		else if ((args.length == 1) && (args[0] instanceof Boolean) && (!(Boolean) args[0]))
			this.send(false);
	}
	
	public void enteringCar() {
		if (processingUnit.freeParkingPlaces > 0)
			this.set(true);
	}
	
	public void exitingCar() {	
		if (processingUnit.freeParkingPlaces < maxCars)
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
