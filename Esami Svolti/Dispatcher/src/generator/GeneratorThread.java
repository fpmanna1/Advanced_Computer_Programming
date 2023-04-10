package generator;

import java.rmi.RemoteException;
import java.util.Random;

import dispatcher.IDispatcher;
import reading.Reading;

public class GeneratorThread extends Thread{
	
	IDispatcher dispatcher;
	
	public GeneratorThread(IDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public void run() {
		String tipo;
		int valore;
		
		for(int i=0; i<3; i++) {
			
			if(new Random().nextInt(1) == 0) {
				tipo = new String("temperatura");
			} else {
				tipo = new String("pressione");
			}
			
			valore = new Random().nextInt(100);
			try {
				dispatcher.setReading(new Reading(tipo, valore));
				System.out.println("[THREAD GENERATOR] Invio richiesta setReading al dispatcher");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
