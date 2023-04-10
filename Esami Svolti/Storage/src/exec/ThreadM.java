package exec;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import storage.IStorage;

public class ThreadM extends Thread{
	
	private int result;
	
	public ThreadM(int result) {
		this.result = result;
	}
	
	public void run() {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IStorage storage = (IStorage) rmiregistry.lookup("mystorage");
			
			System.out.println("[THREADM] Risultato dell'elaborazione "+result);
			
			
			storage.store("math", result);
			
			System.out.println("[THREADM] Richiesta store di "+result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
