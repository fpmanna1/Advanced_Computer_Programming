package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import broker.*;

public class Client {
	public static final int T = 5;
	
	
	public static void main(String[] args) {
		// RECUPERO RIFERIMENTO OGGETTO REMOTO
		Registry rmiregistry;
		IBroker broker = null;
		try {
			
			rmiregistry = LocateRegistry.getRegistry();
			broker = (IBroker)rmiregistry.lookup("mybroker");
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Thread threads[] = new Thread[T];
		
		for(int i=0; i<T; i++) {
			threads[i] = new ClientThread(broker);
			threads[i].start();
		}
		
		for(int j=0; j<T; j++) {
			try {
				threads[j].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
