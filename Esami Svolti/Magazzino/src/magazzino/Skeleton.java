package magazzino;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Skeleton implements IMagazzino{
	
	public void runSkeleton() {
		
		try {
			ServerSocket srv = new ServerSocket(8000);
			
			while(true) {
				//Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
				Socket socket = srv.accept();
				
				DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
				DataInputStream dataIn = new DataInputStream(socket.getInputStream());
				
				String method = new String(dataIn.readUTF());
				
				if(method.equals("deposita")) {
					
					System.out.println("[SKELETON] Richiesta deposita");
					String tipo = new String(dataIn.readUTF());
					int id = dataIn.readInt();
					this.deposita(tipo, id);
					
				}else if(method.equals("preleva")) {
					
					System.out.println("[SKELETON] Richiesta preleva");
					String tipo = new String(dataIn.readUTF());
					int value = this.preleva(tipo);
					dataOut.writeInt(value);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
