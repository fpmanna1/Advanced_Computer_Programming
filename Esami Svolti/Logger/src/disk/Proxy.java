package disk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import logger.ILogger;

public class Proxy implements ILogger{

	public DatagramSocket socket;
	public int port;
	
	public Proxy(DatagramSocket socket, int port) {
		this.socket = socket;
		this.port = port;
	}
	@Override
	public void registraDato(int dato) {
		// Socket UDP
		String msg =new String("registraDato#"+dato);
		
		
		try {
			DatagramPacket request = new DatagramPacket(msg.getBytes(), 0,msg.getBytes().length, InetAddress.getLocalHost(), port);
			this.socket.send(request);
			
			// CODICE PER EVENTUALE ACK
			/*
			 * byte[] ack = new byte[65508];
			 * DatagramPacket response = new DatagramPacket(ack, ack,length);
			 * this.socket.receive(response);
			 */
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
