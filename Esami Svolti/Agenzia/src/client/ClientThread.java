package client;

import java.rmi.RemoteException;
import java.util.Random;

import broker.IBroker;

public class ClientThread extends Thread{

	private IBroker broker;
	
	private static final int R = 25;
	
	public ClientThread(IBroker broker) {
		this.broker = broker;
	}
	
	public void run() {
		for(int i=0; i<R; i++) {
			try {
				Thread.sleep(1+(new Random().nextInt(4)));
				// 1 acquista, 2 vendi
				
				int tipoOp = 1 + (new Random().nextInt(2));
				int quantita = 1 + (new Random().nextInt(5));
				broker.sottoponi(tipoOp, quantita);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
