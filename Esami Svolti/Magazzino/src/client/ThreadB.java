package client;

import java.util.Random;

import magazzino.IMagazzino;

public class ThreadB extends Thread{
	
	private IMagazzino proxy;;
	
	public ThreadB(IMagazzino proxy) {
		this.proxy = proxy;
	}
	
	public void run() {
		String articolo;
		
		for(int i=0; i<3; i++) {
			try {
				Thread.sleep(1000*(2+new Random().nextInt(3)));
				if(new Random().nextInt(2) == 0) {
					articolo = new String("laptop");
				}else {
					articolo = new String("smartphone");
				}
				
				int value = proxy.preleva(articolo);
				
				System.out.println("[THREAD B] Prelevato "+value);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
