package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import gestoreSportello.IGestoreSportello;

public class Client {
	
	private static final int T = 10;
	
	public static void main(String[] args) {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IGestoreSportello gestore = (IGestoreSportello) rmiregistry.lookup("mygestore");
			
			Thread threads[] = new Thread[T];
			
			for(int i=0; i<T; i++) {
				threads[i] = new ClientThread(gestore);
				threads[i].start();
			}
			
			for(int i=0; i<T; i++) {
				threads[i].join();
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
