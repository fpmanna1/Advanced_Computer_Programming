package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import magazzino.IMagazzino;

public class Proxy implements IMagazzino{

	private int host;
	private int port;
	
	public Proxy(int port) {
		this.port = port;
	}
	@Override
	public void deposita(String tipo, int id) {
		// TODO Auto-generated method stub
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			
			dataOut.writeUTF("deposita");
			dataOut.writeUTF(tipo);
			dataOut.writeInt(id);
			
			System.out.println("[PROXY] Invio richiesta deposita");
			
			dataOut.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int preleva(String tipo) {
		// TODO Auto-generated method stub
		int value = 0;
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataIn = new DataInputStream(socket.getInputStream());
			
			dataOut.writeUTF("preleva");
			dataOut.writeUTF(tipo);
			
			System.out.println("[PROXY] Invio richiesta preleva");
			
			value = dataIn.readInt();
			
			dataOut.close();
			dataIn.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
