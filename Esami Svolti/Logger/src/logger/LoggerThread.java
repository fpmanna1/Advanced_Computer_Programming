package logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class LoggerThread extends Thread{
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private ILogger logger;
	
	public LoggerThread(DatagramSocket socket, DatagramPacket packet, ILogger logger) {
		this.socket = socket;
		this.packet = packet;
		this.logger = logger;
	}
	
	public void run() {
		String msg = new String(packet.getData(), 0, packet.getLength());
		StringTokenizer st = new StringTokenizer(msg, "#");
		String method = st.nextToken();
		int dato = Integer.valueOf(st.nextToken()).intValue();
		
		logger.registraDato(dato);
		
		// eventuale ack
		
		/*
		 * 	 String ack = new String("ack"); 
		 *   DatagramPacket reply = new DatagramPacket(ack.getBytes(), ack.getBytes().length, packet.getAddress(), packet.getPort());
		 *   try {
                    this.socket.send(reply);
                } catch(IOException e) {
                    e.printStackTrace();
                }
		 */
	}
	
}
