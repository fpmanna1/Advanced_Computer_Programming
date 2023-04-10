package exec;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import storage.IStorage;


public class ThreadT extends Thread{
	
	private int result;
	
	public ThreadT(int result) {
		this.result = result;
	}
	
	public void run() {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IStorage storage = (IStorage) rmiregistry.lookup("mystorage");
			
			System.out.println("[THREADT] Risultato elaborazione "+result);
			storage.store("text", result);
			
			System.out.println("[THREADT] Richiesta store di"+result);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
