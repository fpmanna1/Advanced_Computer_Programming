package subscriber;

public interface ISubscriber {
	void notifyAlert(int critically);

	int getComponentID();
}
