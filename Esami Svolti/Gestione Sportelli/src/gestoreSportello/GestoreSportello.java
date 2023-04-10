package gestoreSportello;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GestoreSportello {
	public static void main(String[] args) {
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IGestoreSportello gestore = new GestoreSportelloImpl();
			rmiregistry.rebind("mygestore", gestore);
			
			System.out.println("[GESTORE] Gestore registrato");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
