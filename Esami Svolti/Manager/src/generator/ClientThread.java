package generator;

import manager.*;

import java.rmi.RemoteException;
import java.util.Random;

import alert.*;

public class ClientThread extends Thread{
	
	IManager manager;
	
	public ClientThread(IManager manager) {
		this.manager = manager;
	}
	
	public void run() {
		// generazione alert
	
		int componentID = 1 + (new Random().nextInt(5));
		int critically = 1 + (new Random().nextInt(3));
		
		AlertNotification alert = new AlertNotification(componentID, critically);
		
		try {
			manager.sendNotification(alert);
			System.out.println("[CLIENT] Inviata notifica");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
