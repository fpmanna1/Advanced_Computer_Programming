package codaimpl;

import coda.*;
import java.util.concurrent.locks.*;

public class CodaWrapperLock extends CodaWrapper{
	
	// Inserire Lock e variabili condition
	private Lock lock;
	private Condition msg_available;
	private Condition space_available;
	
	public CodaWrapperLock( Coda c ){
		super (c);
		
		// Inizializzare lock e variabili condition
		
		lock = new ReentrantLock();
		msg_available = lock.newCondition();
		space_available = lock.newCondition();
	}
	
	
	public void inserisci( int i){
				
		
		// Implementare sincronizzazione con lock e variabili condition
		
			coda.inserisci(i);
				
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con lock e variabili condition
				
			x= coda.preleva();
		
		return x;
	}

	
}
