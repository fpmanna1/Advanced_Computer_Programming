package gestoreSportello;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sportello.ISportello;

public interface IGestoreSportello extends Remote{
	
	boolean sottoponiRichiesta(int idCliente) throws RemoteException;
	
	void sottoscrivi(ISportello sportello) throws RemoteException;
}
