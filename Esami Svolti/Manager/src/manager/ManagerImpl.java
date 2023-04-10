package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import alert.AlertNotification;
import subscriber.ISubscriber;

public class ManagerImpl extends UnicastRemoteObject implements IManager{

	private static final long serialVersionUID = 3532662943681791639L;
	
	Vector<ISubscriber> subscriptions;
	
	protected ManagerImpl() throws RemoteException {
		super();
		subscriptions = new Vector<ISubscriber>();
	}

	@Override
	public void subscribe(int componentID, int port) throws RemoteException {
		// TODO Auto-generated method stub
		ISubscriber proxy = new Proxy(componentID, port);
		subscriptions.add(proxy);
		
		System.out.println("[MANAGER] Added new subscriptions: componentID: "+componentID+" port: "+port);
	}

	@Override
	public void sendNotification(AlertNotification alert) throws RemoteException {
		// TODO Auto-generated method stub
		int componentID = alert.getComponentID();
		int critically = alert.getCritically();
		synchronized(this) {
			for(int i=0; i<subscriptions.size(); i++) {
				if(subscriptions.get(i).getComponentID() == componentID) {
					System.out.println("[MANAGER] Invio alert in corso ai sub con id :"+componentID);
					subscriptions.get(i).notifyAlert(critically);
				}	
			}
		}
	}

}
