package broker;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBroker extends Remote{
	void sottoponi(int tipoOperazione, int quantita) throws RemoteException;
	void sottoscrivi(int port) throws RemoteException;
}
