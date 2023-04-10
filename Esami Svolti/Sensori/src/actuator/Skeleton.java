package actuator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Skeleton implements IActuator{
	
	private int port;
	
	public Skeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		String method;
		try {
			ServerSocket srv = new ServerSocket(port);
			
			while(true) {
				Socket socket = srv.accept();
				DataInputStream dataIn = new DataInputStream(socket.getInputStream());
				DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
				
				method = dataIn.readUTF();
				if(method.equals("execute")) {
					String action = new String(dataIn.readUTF());
					boolean result = this.execute(action);
					dataOut.writeBoolean(result);
					
					dataIn.close();
					dataOut.close();
					
				} else {
					System.out.println("[SKELETON] Invalid method!");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
