package logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Skeleton implements ILogger{

	private ILogger logger;
	private int port;
	
	public Skeleton(ILogger logger, int port) {
		this.logger = logger;
		this.port = port;
	}
	
	@Override
	public void registraDato(int dato) {
		// TODO Auto-generated method stub
		logger.registraDato(dato);
	} // skeleton delega
	
	public void runSkeleton() {
		try {
			DatagramSocket socket = new DatagramSocket(this.port);
			System.out.println("[SKELETON] In ascolto sul porto "+this.port+"...");
			
			while(true) {
				byte[] buff = new byte[65508];
				DatagramPacket request = new DatagramPacket(buff, buff.length);
				socket.receive(request);
				LoggerThread thread = new LoggerThread(socket, request, this);
				thread.start();
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
