package agenzia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Skeleton implements IAgenzia{

	
	private IAgenzia agenzia;
	private int port;
	
	public Skeleton(IAgenzia agenzia, int port) {
		this.agenzia = agenzia;
		this.port = port;
	}
	@Override
	public void acquista(int quantita) {
		// TODO Auto-generated method stub
		agenzia.acquista(quantita);
		
	}

	@Override
	public void vendi(int quantita) {
		// TODO Auto-generated method stub
		agenzia.vendi(quantita);
	}
	
	public void runSkeleton() {
		
		try {
			ServerSocket srv = new ServerSocket(port);
	
			while(true) {
			
				Socket socket = srv.accept();
				DataInputStream dataIn = new DataInputStream(socket.getInputStream());
				DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
				String method;
				int quantita;
				method = dataIn.readUTF();
				
				if(method.equals("acquista")) {
					quantita = dataIn.readInt();
					acquista(quantita);
				}else if(method.equals("vendi")) {
					quantita = dataIn.readInt();
					vendi(quantita);
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
