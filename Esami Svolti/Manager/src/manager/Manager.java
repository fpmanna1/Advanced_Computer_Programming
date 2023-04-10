package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Manager {
	public static void main(String[] args) {
		// sottoscrizione all'RMI registry
		
		try {
			IManager manager = new ManagerImpl();
			Registry rmiregistry = LocateRegistry.getRegistry();
			rmiregistry.rebind("mymanager", manager);
			
			System.out.println("[MANAGER] Manager aggiunto");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
