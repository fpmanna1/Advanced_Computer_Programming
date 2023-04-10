package codaimpl;

import java.util.concurrent.Semaphore;

import coda.*;

public class CodaWrapperSem extends CodaWrapper {

	
	// Inserire semafori
	// ne serviranno 2 per la cooperazione e 1 blocco synchronized per la mutua esclusione
	
	Semaphore msg_available;
	Semaphore space_available;

	
	public CodaWrapperSem ( Coda c ){
		super (c);	
		
		// inizializzare semafori
		msg_available = new Semaphore(0);
		space_available = new Semaphore(coda.getSize());
	}
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con semafori
		try {
			space_available.acquire();
		
			synchronized(coda){
				coda.inserisci(i);
		}
			
			msg_available.release(); // altri consumatori possono essere sbloccati
			// quando produco un nuovo elemento
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
				
		
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con semafori
		try {
			msg_available.acquire();
		
		synchronized(coda) {
					x = coda.preleva();
		}
			space_available.release();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		return x;
	}
	
	
}