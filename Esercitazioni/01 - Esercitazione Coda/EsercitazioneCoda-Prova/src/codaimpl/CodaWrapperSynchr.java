package codaimpl;

import coda.*;

public class CodaWrapperSynchr extends CodaWrapper {

	
	public CodaWrapperSynchr( Coda c ){
		super (c);		
	}
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con blocchi synchronized
		// non posso inserire se la coda è piena + garantire mutua esclusione
		synchronized(coda){
			while(coda.full()){ // signal and continue
				try {
					coda.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			coda.inserisci(i);
			coda.notifyAll();
			}
	}
	
	
	public int preleva(){
		int x=0;
		
		// Implementare sincronizzazione con blocchi synchronized
		synchronized(coda) {
			while(coda.empty()) { // signal and continue
				try {
					coda.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			x = coda.preleva();
			
			coda.notifyAll();
			
		}
		return x;
	}
	
}
