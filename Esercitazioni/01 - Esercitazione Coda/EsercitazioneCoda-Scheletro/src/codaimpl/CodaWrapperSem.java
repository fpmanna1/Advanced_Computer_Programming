package codaimpl;

import coda.*;

public class CodaWrapperSem extends CodaWrapper {

	
	// Inserire semafori
	
	
	public CodaWrapperSem ( Coda c ){
		super (c);
		
		// Inizializzare semafori
	}
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con semafori
		
					coda.inserisci(i);
				
		
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con semafori
		
					x = coda.preleva();
		
		
		return x;
	}
	
	
}