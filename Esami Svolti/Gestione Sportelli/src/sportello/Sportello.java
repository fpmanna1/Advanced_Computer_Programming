package sportello;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import gestoreSportello.IGestoreSportello;

public class Sportello {
	public static void main(String[] args) {
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IGestoreSportello gestore = (IGestoreSportello) rmiregistry.lookup("mygestore");
			
			
			ISportello sportello = new SportelloImpl();
			gestore.sottoscrivi(sportello);
			
			System.out.println("[SPORTELLO] Sportello sottoscritto");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
