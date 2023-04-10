package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Dispatcher {
	public static void main(String[] args) {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IDispatcher dispatcher = new DispatcherImpl();
			rmiregistry.rebind("mydispatcher", dispatcher);
			
			System.out.println("[DISPATCHER] Dispatcher registrato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
