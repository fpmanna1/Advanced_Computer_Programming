package storage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStorage extends Remote{
	void store(String reqType, int result) throws RemoteException;
}
