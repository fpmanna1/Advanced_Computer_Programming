package dispatcher;

import java.rmi.Remote;
import java.rmi.RemoteException;
import observer.*;

import reading.Reading;

public interface IDispatcher extends Remote{

	public void setReading(Reading reading) throws RemoteException;
	public Reading getReading() throws RemoteException;
	public void attachObserver(IObserver observer, String tipo) throws RemoteException;
}
