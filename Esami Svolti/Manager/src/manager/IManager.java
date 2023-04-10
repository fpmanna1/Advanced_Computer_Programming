package manager;

import java.rmi.Remote;
import java.rmi.RemoteException;

import alert.AlertNotification;

public interface IManager extends Remote{
	void subscribe(int componentID, int port) throws RemoteException;
	void sendNotification(AlertNotification alert) throws RemoteException;
}
