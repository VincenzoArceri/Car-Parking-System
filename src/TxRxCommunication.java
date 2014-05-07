
public interface TxRxCommunication extends NodeCommunication {
	public void receive(Object... args);
	public void read(Object...args);
	public void send(Object... args);
	public void set(Object... args);
	public void registerObserver(Object who);
	public void unregisterObserver(Object who);
}
