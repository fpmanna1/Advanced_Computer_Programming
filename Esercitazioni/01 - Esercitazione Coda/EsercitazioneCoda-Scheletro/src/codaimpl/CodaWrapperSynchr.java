package codaimpl;

import coda.*;

public class CodaWrapperSynchr extends CodaWrapper {

	
	public CodaWrapperSynchr( Coda c ){
		super (c);		
	}
	
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con blocchi synchronized
		
			coda.inserisci(i);
			
		
	}
	
	
	public int preleva(){
		int x=0;
		
		// Implementare sincronizzazione con blocchi synchronized
		
			
			x = coda.preleva();
			

		return x;
	}
	
}
