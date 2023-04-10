package subscriber;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Skeleton implements ISubscriber{

	private ISubscriber sub;
	private int port;
	
	public Skeleton(ISubscriber sub, int port) {
		this.sub = sub;
		this.port = port;
	}
	@Override
	public void notifyAlert(int critically) {
		// TODO Auto-generated method stub
		sub.notifyAlert(critically);
	}
	
	public void runSkeleton() {
		try {
			ServerSocket srv = new ServerSocket(port);
			
			while(true) {
				System.out.println("[SKELETON] Server in ascolto...");
				Socket socket = srv.accept();
				
				DataInputStream dataIn = new DataInputStream(socket.getInputStream());
				String method = dataIn.readUTF();
				if(method.equals("notifyAlert")) {
					int critically = dataIn.readInt();
					sub.notifyAlert(critically);
					
					System.out.println("[SKELETON] Ricevuto critically");
				} else {
					System.out.println("[SKELETON] Invalid method");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	@Override
	public int getComponentID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
