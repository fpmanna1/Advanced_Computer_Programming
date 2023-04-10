package storage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StorageImpl extends UnicastRemoteObject implements IStorage{

	private static final long serialVersionUID = 675832752395340118L;

	private Lock lock;
	
	public StorageImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		lock = new ReentrantLock();
	}

	@Override
	public void store(String reqType, int result) throws RemoteException {
		// TODO Auto-generated method stub
		
		lock.lock();
		
		System.out.println("[STORAGE] reqType : "+reqType+" result : "+result);
		
		try {
			FileOutputStream fileOut = new FileOutputStream("results.txt", true);
			PrintWriter printw = new PrintWriter(fileOut);
			
			printw.println(reqType+":"+result);
			
			printw.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

}
