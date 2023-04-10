package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.*;

public class SportelloServer {
	
	public static void main (String[] args) {
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			IGestoreSportello gestore = (IGestoreSportello) rmiRegistry.lookup("gestoreSportello");
			ISportello sportello = new SportelloImpl();
			
			gestore.sottoscrivi(sportello);
			// passo l'oggetto remoto sportello a gestore
			
			System.out.println("[SERVER SPORTELLO] Sottoscritto al gestore");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
