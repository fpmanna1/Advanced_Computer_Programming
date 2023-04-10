package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import alert.AlertNotification;
import subscriber.ISubscriber;

public class ManagerImpl extends UnicastRemoteObject implements IManager{

	private static final long serialVersionUID = -4435269994603455139L;

	private Vector<ISubscriber> subscribers;
	
	protected ManagerImpl() throws RemoteException {
		super();
		subscribers = new Vector<ISubscriber>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void sendNotification(AlertNotification alert) throws RemoteException {
		// TODO Auto-generated method stub
		int componentID = alert.getComponentID();
		int critically = alert.getCritically();
		
		for(int i=0; i<subscribers.size(); i++)
		{
			if(subscribers.get(i).getComponentID() == componentID) {
				subscribers.get(i).notifyAlert(critically);
				System.out.println("[MANAGER] Invio notifyAlert");
			}
		}
	}

	@Override
	public void subscribe(int componentID, int portaSub) throws RemoteException {
		// TODO Auto-generated method stub
		ISubscriber proxy = new Proxy(componentID, portaSub);
		// portaSub = porta che utilizzerà il manager per contattare i subscribers
		subscribers.add(proxy);
		System.out.println("[MANAGER] Sottoscritto subscriber");
		
	}
	
	

}
