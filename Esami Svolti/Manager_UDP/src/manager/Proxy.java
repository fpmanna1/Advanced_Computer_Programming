package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.Vector;

import alert.AlertNotification;
import subscriber.ISubscriber;

public class Proxy implements ISubscriber{
	
	private int componentID;
	private int port;
	
	// NUMERO DI PORTO DEL SUBSCRIBER, UTILIZZATO DAL MANAGER
	// PER CONTATTARE I SUBSCRIBER
	
	public Proxy(int componentID, int port) {
		this.componentID = componentID;
		this.port = port;	
	}

	@Override
	public void notifyAlert(int critically) {
		// TODO Auto-generated method stub
		
		// SOCKET UDP
		String msg = new String("notifyAlert#"+critically);

		try {
			DatagramPacket request = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, InetAddress.getLocalHost(), port);
			// l'IP e il porto è da intendersi come la DESTINAZIONE
			
			DatagramSocket socket = new DatagramSocket();
			socket.send(request);
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("[SOCKET] Inviata richiesta notifyAlert");
	}

	@Override
	public int getComponentID()  {
		// TODO Auto-generated method stub
		return this.componentID;
	}
	
}
