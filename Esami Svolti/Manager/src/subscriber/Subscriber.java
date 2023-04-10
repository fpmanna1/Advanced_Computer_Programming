package subscriber;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import manager.IManager;

public class Subscriber {
	public static void main(String[] args) {
		int componentID = Integer.parseInt(args[0]);
		int port = Integer.parseInt(args[1]);
		String path = args[2];
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IManager manager = (IManager) rmiregistry.lookup("mymanager");
			manager.subscribe(componentID, port);
			ISubscriber sub = new SubscriberImpl(componentID, path);
			Skeleton sk = new Skeleton(sub, port);
			sk.runSkeleton();
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	
	}
}
