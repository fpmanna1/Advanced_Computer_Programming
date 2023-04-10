package client;

public class ClientA {
	public static void main(String[] args) {
		Thread threads[] = new Thread[5];
		
		Proxy proxy = new Proxy(8000);
		for(int i=0; i<5; i++) {
			threads[i] = new ThreadA(proxy);
			threads[i].start();
		}
		
		for(int i=0; i<5; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
