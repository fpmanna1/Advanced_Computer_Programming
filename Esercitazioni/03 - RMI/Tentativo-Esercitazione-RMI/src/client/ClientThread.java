package client;


import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IGestoreSportello;


public class ClientThread extends Thread {
	
	private int requests;
	private IGestoreSportello gestore;
	
	public ClientThread(int requests, IGestoreSportello gestore) {
		
		this.requests = requests;
		this.gestore = gestore;
	}
	
	public void run() {
		
		Random rand = new Random();
		
		for(int i=0; i<requests; i++) {
			
			try {
				
				int idCliente = rand.nextInt(100) + 1;
				
				Thread.sleep(rand.nextInt(3) + 1);
			
				boolean results = gestore.sottoponiRichiesta(idCliente);
				
				System.out.println("[CLIENT THREAD] Richiesta terminata con esito " + results);
			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	

}
