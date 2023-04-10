package manager;

import java.rmi.Remote;
import java.rmi.RemoteException;

import alert.AlertNotification;

public interface IManager extends Remote{
	void sendNotification(AlertNotification alert) throws RemoteException;
	
	void subscribe(int componentID, int porta) throws RemoteException;
}
