package storage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Storage {

	public static void main(String[] args) {
		
		try {
			IStorage storage = new StorageImpl();
			Registry rmiregistry = LocateRegistry.getRegistry();
			rmiregistry.rebind("mystorage", storage);
			
			System.out.println("[STORAGE] Storage registrato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
