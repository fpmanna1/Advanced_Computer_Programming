package observer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dispatcher.IDispatcher;
import reading.Reading;

public class ObserverImpl extends UnicastRemoteObject implements IObserver{

	private static final long serialVersionUID = -300226257955276574L;
	
	private IDispatcher dispatcher;
	private String path;
	
	public ObserverImpl(IDispatcher dispatcher, String path) throws RemoteException{
		this.dispatcher = dispatcher;
		this.path = path;
	}

	@Override
	public void notifyReading() throws RemoteException {
		// TODO Auto-generated method stub
		Reading reading = new Reading(dispatcher.getReading());
		
		try {
			FileOutputStream fileOut = new FileOutputStream(path, true);
			PrintWriter printw = new PrintWriter(fileOut);
			
			printw.println(reading.getValore());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
