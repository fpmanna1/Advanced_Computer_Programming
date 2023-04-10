package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestoreSportello extends Remote{ 
	// è un oggetto remoto utilizzato dal client per sottoporre richieste
	
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException;
	
	public void sottoscrivi(ISportello sportello) throws RemoteException;

}
