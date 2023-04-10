package magazzino;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import coda.Coda;

public class MagazzinoImpl extends Skeleton {

	private Coda laptop;
	private Coda smartphone;
	
	private Lock lock_l;
	private Lock lock_s;
	private Condition full_l;
	private Condition empty_l;
	private Condition full_s;
	private Condition empty_s;
	
	public MagazzinoImpl() {
		lock_l = new ReentrantLock();
		lock_s = new ReentrantLock();
		full_l = lock_l.newCondition();
		empty_l = lock_l.newCondition();
		full_s = lock_s.newCondition();
		empty_s = lock_s.newCondition();
		laptop = new Coda(5);
		smartphone = new Coda(5);
	}
	
	@Override
	public void deposita(String tipo, int id) {
		// TODO Auto-generated method stub
		if(tipo.equals("laptop")) {
			lock_l.lock();
			
			while(laptop.full()) {
				try {
					empty_l.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			laptop.push(id);
				
			full_l.signal();
			
			lock_l.unlock();
				
			System.out.println("[MAGAZZINO] Aggiunto id "+id+" a laptop");
				
		} else if(tipo.equals("smartphone")){
			lock_s.lock();
			
			while(smartphone.full()) {
				try {
					empty_s.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			smartphone.push(id);
				
			full_s.signal();
			
			lock_s.unlock();
				
			System.out.println("[MAGAZZINO] Aggiunto id "+id+" a smartphone");
		} else {
			System.out.println("[MAGAZZINO] Articolo sbagliato");
		}
	}
	
	
	@Override
	public int preleva(String tipo) {
		// TODO Auto-generated method stub
		int value = 0;
		
		if(tipo.equals("laptop")) {
			lock_l.lock();
			
			while(laptop.empty()) {
				try {
					full_l.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			value = laptop.pop();
			
			
			try {
				FileOutputStream fileOut = new FileOutputStream("file1.txt", true);
				PrintWriter printw = new PrintWriter(fileOut);
				printw.println(value);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			empty_l.signal();
			lock_l.unlock();
			System.out.println("[MAGAZZINO] Prelevato l'id "+value+" dalla coda laptop");
		} else if(tipo.equals("smartphone")) {
			
			lock_s.lock();
			
			while(smartphone.empty()) {
				try {
					full_s.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			value = smartphone.pop();
			
			try {
				FileOutputStream fileOut = new FileOutputStream("file2.txt", true);
				PrintWriter printw = new PrintWriter(fileOut);
				printw.println(value);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				empty_s.signal();
				lock_s.unlock();
				System.out.println("[MAGAZZINO] Prelevato id"+value+" dalla coda samrtphone");
			}
		} else {
			System.out.println("[MAGAZZINO] Articolo sbagliato");
		}
		return value;
	}
}
