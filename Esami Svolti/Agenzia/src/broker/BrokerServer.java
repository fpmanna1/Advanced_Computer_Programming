package broker;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BrokerServer {
	public static void main(String[] args) {
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IBroker broker = new BrokerImpl();
			rmiregistry.rebind("mybroker", broker);
			
			System.out.println("[BROKER] Broker registrato sul registry");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
