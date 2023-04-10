package manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import subscriber.ISubscriber;

public class Proxy implements ISubscriber{

	private int componentID;
	private int port;
	
	public Proxy(int componentID, int port) {
		this.componentID = componentID;
		this.port = port;
	}
	
	public int getComponentID() {
		return componentID;
	}
	@Override
	public void notifyAlert(int critically) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			
			dataOut.writeUTF("notifyAlert");
			dataOut.writeInt(critically);
			System.out.println("[PROXY] Invio notifyAlert");
			
			dataOut.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
