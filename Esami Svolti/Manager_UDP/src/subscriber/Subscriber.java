package subscriber;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import manager.IManager;

public class Subscriber {
	public static void main(String[] args) {
		
		int componentID = Integer.parseInt(args[0]);
		int portSub = Integer.parseInt(args[1]);
		String path = new String(args[2]);
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IManager manager = (IManager) rmiregistry.lookup("mymanager");
			
			SubscriberImpl subscriber = new SubscriberImpl(portSub, path);
			
			manager.subscribe(componentID, portSub);
			
			subscriber.runSkeleton();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
