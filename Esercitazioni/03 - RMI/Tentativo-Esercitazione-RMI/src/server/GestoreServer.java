package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;


public class GestoreServer {
	
	public static void main (String[] args) {
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestoreSportello gestoreSportello = new GestoreSportelloImpl();
			
			rmiRegistry.rebind("gestoreSportello", gestoreSportello);
			// rebind: in caso in cui l'oggetto remoto sia già sottoscritto, lo va a
			// risottoscrivere
			
			System.out.println("[SERVER] Gestore Sportello avviato");
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}

}
