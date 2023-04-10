package agenzia;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import broker.IBroker;

public class Agenzia {
	public static void main(String[] args) {
		IBroker broker;
		int port = 8000;
		try {
			
			Registry rmiregistry = LocateRegistry.getRegistry();
			broker = (IBroker)rmiregistry.lookup("mybroker");
			broker.sottoscrivi(port);
			
			AgenziaImpl agenzia = new AgenziaImpl();
			Skeleton sk = new Skeleton(agenzia, port);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
