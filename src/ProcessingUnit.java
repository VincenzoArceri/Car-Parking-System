
public class ProcessingUnit extends Node implements TxRxCommunication, Add, Sub, Average {
	
	private Monitor monitor;
	private int totalCars = 0;
	public int freeParkingPlaces = 500;
	
	@Override
	public void receive(Object... args) {
		System.out.println("Ho ricevuto un dato da Detector");
		Simulator.Calculate.setText("Calculating...");
			
		if ((args.length == 1) && (args[0] instanceof Boolean))
			this.read(args[0]);
	}
	
	@Override
	public void read(Object... args) {
		System.out.println("Sto preparando il dato da essere processato");
		this.calculate((Boolean) args[0] ? 1 : -1);		
	}

	@Override
	public void send(Object... args) {
		System.out.println("Sto inviando dei dati al monitor");
		monitor.receive(args);
	}

	@Override
	public void set(Object... args) {
		System.out.println("Sto preparando dei dati per essere inviati");
		
		if (args.length == 2)
			this.send(args);	
	}
	
	public void calculate(Object... args) {
		int op = (Integer) args[0];

		if (op == -1) {
			freeParkingPlaces++;
			this.send(freeParkingPlaces);
		} else {
			freeParkingPlaces--;
			totalCars++;
			Simulator.Cars.setText("" + totalCars);
			this.send(freeParkingPlaces, this.average(totalCars,13));
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double average(long sum, long hours) {
		return (double) sum / (double) hours;
	}

	@Override
	public long sub(long first, long second) {
		return first - second;
	}

	@Override
	public long add(long first, long second) {
		return first + second;
	}

	@Override
	public void registerObserver(Object who) {
		System.out.println("Monitor registrata");
		if (who instanceof Monitor)
			monitor = (Monitor) who;		
	}

	@Override
	public void unregisterObserver(Object who) {
		System.out.println("Monitor deregistrata");
		if (who instanceof ProcessingUnit)
			monitor = null;	
	}
}
