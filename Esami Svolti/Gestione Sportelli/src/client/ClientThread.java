package client;

import java.rmi.RemoteException;
import java.util.Random;

import gestoreSportello.IGestoreSportello;

public class ClientThread extends Thread{

	private static final int R = 5;
	
	private IGestoreSportello gestore;
	
	public ClientThread(IGestoreSportello gestore) {
		this.gestore = gestore;
	}
	
	public void run() {
		
		for(int i=0; i<R; i++) {
			try {
				Thread.sleep(1000 * (1+new Random().nextInt(3)));
				int id = 1+new Random().nextInt(100);
				boolean result = gestore.sottoponiRichiesta(id);
				 
				 System.out.println("[CLIENT THREAD] Sottoposta richiesta con id "+ id+" con esito "+result);
			} catch (InterruptedException | RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
