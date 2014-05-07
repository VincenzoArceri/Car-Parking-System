
public interface TxCommunication extends NodeCommunication {
	public void send(Object... args);
	public void set(Object... args);
	public void registerObserver(Object who);
	public void unregisterObserver(Object who);
}
