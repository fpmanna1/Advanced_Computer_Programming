package sportello;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class SportelloImpl extends UnicastRemoteObject implements ISportello{

	private static final long serialVersionUID = -2488530750069439681L;

	private Semaphore max_concurrent_requests;
	private Semaphore max_requests;
	
	public SportelloImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		max_concurrent_requests = new Semaphore(3);
		max_requests = new Semaphore(5);
	}

	@Override
	public boolean serviRichiesta(int idCliente) throws RemoteException {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		if(!max_requests.tryAcquire()) {
			System.out.println("[SPORTELLO] Massimo richieste raggiunto");
			return result;
		}
		
		try {
			max_concurrent_requests.acquire();
			
			FileOutputStream fileOut = new FileOutputStream("file.txt", true);
			PrintWriter printw = new PrintWriter(fileOut);
			
			printw.println(idCliente);
			
			result = true;
			
			printw.close();
			fileOut.close();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			max_concurrent_requests.release();
			max_requests.release();
		}
		
		return result;
	}

}
