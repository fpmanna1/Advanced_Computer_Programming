package subscriber;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubscriberImpl extends Skeleton{

	String path;
	int port;
	
	public SubscriberImpl(int port, String path) {
		super(port);
		this.path = new String(path);
	}

	@Override
	public void notifyAlert(int critically){
		// TODO Auto-generated method stub
		
		System.out.println("[SUBSCRIBER] Stampa critically"+critically);
		
		try {
			FileOutputStream fileOut = new FileOutputStream(path, true);
			PrintWriter printw = new PrintWriter(fileOut);
			
			printw.println(critically);
			
			printw.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
