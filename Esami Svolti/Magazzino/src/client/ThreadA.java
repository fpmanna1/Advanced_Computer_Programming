package client;

import java.util.Random;

import magazzino.IMagazzino;

public class ThreadA extends Thread{

	private IMagazzino proxy;
	
	public ThreadA(IMagazzino proxy) {
		this.proxy = proxy;
	}
	
	
	public void run() {
		String articolo;
		int id_articolo;
		
		for(int i=0; i<3; i++) {
			try {
				Thread.sleep(1000*(2+new Random().nextInt(3)));
				if(new Random().nextInt(2) == 0) {
					articolo = new String("laptop");
				}else {
					articolo = new String("smartphone");
				}
				id_articolo = new Random().nextInt(100);
				proxy.deposita(articolo, id_articolo);
				
				System.out.println("[THREAD A] Invio richiesta deposita al proxy");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
