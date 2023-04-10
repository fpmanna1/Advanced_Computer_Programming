package generator;

import java.rmi.RemoteException;
import java.util.Random;

import alert.AlertNotification;
import manager.IManager;

public class GeneratorThread extends Thread{
	
	private IManager manager;
	
	public GeneratorThread(IManager manager) {
		this.manager = manager;
	}
	
	public void run() {
		int componentID = 0;
		int critically = 0;
		try {
			componentID= 1 + new Random().nextInt(5);
			critically = 1 + new Random().nextInt(3);
			manager.sendNotification(new AlertNotification(componentID, critically));
			
			System.out.println("[GENERATOR THREAD] Invio notifica al manager");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
