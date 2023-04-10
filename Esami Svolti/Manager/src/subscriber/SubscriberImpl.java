package subscriber;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SubscriberImpl implements ISubscriber{

	private int componentID;
	private String path;
	
	public SubscriberImpl(int componentID, String path) {
		this.componentID = componentID;
		this.path = new String(path);
	}
	
	public int getComponentID() {
		return this.componentID;
	}
	

	@Override
	public void notifyAlert(int critically) {
		// TODO Auto-generated method stub
		System.out.println("[SUBSCRIBER] Valore critically : "+critically);
		try {
			FileOutputStream file = new FileOutputStream(path, true);
			PrintWriter printw = new PrintWriter(file);
			printw.println(critically);
			
			printw.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
