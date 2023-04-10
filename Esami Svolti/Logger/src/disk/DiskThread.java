package disk;

import java.net.DatagramSocket;
import java.net.SocketException;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import logger.ILogger;

public class DiskThread extends Thread{
	private MapMessage msg;
	

	public DiskThread(MapMessage msg) {
		this.msg = msg;
	}
	
	public void run() {
		try {
			
			int dato = msg.getInt("dato");
			int port = msg.getInt("port");
			
			System.out.println("[DISK THREAD] Dato ricevuto : "+dato);
			DatagramSocket socket = new DatagramSocket();
			
			ILogger proxy = new Proxy(socket, port);
			proxy.registraDato(dato);
			
		} catch (JMSException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
