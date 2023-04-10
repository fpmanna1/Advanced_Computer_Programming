package subscriber;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public abstract class Skeleton implements ISubscriber{

	private int port;
	
	public Skeleton(int port) {
		this.port = port;
	}
	
	void runSkeleton() {
		
		try {
			DatagramSocket socket = new DatagramSocket(port);
			System.out.println("[SKELETON] Skeleton in ascolto sul porto "+port);
		
			while(true) {
				byte buff[] = new byte[65508];
				
				DatagramPacket packet = new DatagramPacket(buff, 0, buff.length, InetAddress.getLocalHost(), port);
				socket.receive(packet);
				
				String msg = new String(packet.getData(), 0, packet.getLength());
				StringTokenizer st = new StringTokenizer(msg, "#");
				
				String method = st.nextToken();
				if(method.equals("notifyAlert")) {
					
					int critically = Integer.parseInt(st.nextToken());
					
					System.out.println("[SKELETON] Richiesta notifyAlert");
					this.notifyAlert(critically);
					
				}
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}
