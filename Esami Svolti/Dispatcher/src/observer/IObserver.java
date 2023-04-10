package observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import reading.Reading;

public interface IObserver extends Remote{
	void notifyReading() throws RemoteException;

}
