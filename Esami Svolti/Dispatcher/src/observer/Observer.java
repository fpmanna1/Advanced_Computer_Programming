package observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dispatcher.IDispatcher;

public class Observer {
	public static void main(String[] args) {
		String tipo = new String(args[0]);
		String path = new String(args[1]);
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IDispatcher dispatcher = (IDispatcher) rmiregistry.lookup("mydispatcher");
			
			IObserver obs = new ObserverImpl(dispatcher, path);
			
			System.out.println("[OBSERVER] Aggiunta al dispatcher");
			
			dispatcher.attachObserver(obs, tipo);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
