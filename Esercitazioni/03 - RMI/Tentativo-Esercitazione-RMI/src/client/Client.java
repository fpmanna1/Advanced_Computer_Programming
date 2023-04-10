package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;

/*
 * 3. Client: genera T thread, ognuno dei quali, 
 * allo scadere di un tempo di t secondi (con t scelto a caso tra 1 e 3), 
 * effettua una richiesta di prenotazione. Ogni client thread genera R richieste. Si imposti 
 * T pari a 10; R pari a 10. L’ idClient è generato in maniera casuale tra 1 e 100.
 */


public class Client {
	
	public static void main (String[] args) {
		
		int T = 10;
		int R = 10;
		
		Registry rmiRegistry;
		try {
			rmiRegistry = LocateRegistry.getRegistry();
	
		
			IGestoreSportello gestore = (IGestoreSportello) rmiRegistry.lookup("gestoreSportello");
		
		
			ClientThread[] threads = new ClientThread[T];
		
			for(int i = 0; i < threads.length; i++) {
				threads[i] = new ClientThread(R, gestore);
				threads[i].start();
			}
		
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
				
		
	}

}
