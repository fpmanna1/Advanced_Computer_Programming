package agenzia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AgenziaImpl implements IAgenzia{

	private int disponibilita = 10;
	private Lock lock;
	private Condition disp;
	
	public AgenziaImpl() {
		lock = new ReentrantLock();
		disp = lock.newCondition();
	}
	@Override
	public void acquista(int quantita) {
		// TODO Auto-generated method stub
		lock.lock();
		while(disponibilita<quantita) {
			try {
				disp.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		disponibilita-=quantita;
		System.out.println("[AGENZIA] Biglietti acquistati, disp. attuale : "+disponibilita);
		
		lock.unlock();
	}

	@Override
	public void vendi(int quantita) {
		// TODO Auto-generated method stub
		lock.lock();
		while(disponibilita>=quantita) {
			disp.signal();
		}
		disponibilita+=quantita;
		System.out.println("[AGENZIA] Biglietti venduti, disp. attuale : "+disponibilita);
		
		lock.unlock();
	}
	
}
