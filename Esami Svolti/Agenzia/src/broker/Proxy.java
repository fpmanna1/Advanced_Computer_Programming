package broker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import agenzia.*;

public class Proxy implements IAgenzia{

	private int port;
	
	public Proxy(int port) {
		this.port = port;
	}
	@Override
	public void acquista(int quantita) {
		String method = new String("acquista");
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			
			dataOut.writeUTF(method);
			dataOut.writeInt(quantita);
			
			System.out.println("[PROXY] Richiesta di acquisto...");
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void vendi(int quantita) {
		// TODO Auto-generated method stub
		String method = new String("vendi");
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			
			dataOut.writeUTF(method);
			dataOut.writeInt(quantita);
			
			System.out.println("[PROXY] Richiesta di vendita...");
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
