package gestoreSportello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import sportello.ISportello;

public class GestoreSportelloImpl extends UnicastRemoteObject implements IGestoreSportello{

	private static final long serialVersionUID = -4242446558874024422L;
	
	private Vector<ISportello> sportelli;
	
	protected GestoreSportelloImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		sportelli = new Vector<ISportello>();
	}

	@Override
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException {
		// TODO Auto-generated method stub
		boolean result = false;
		
		for(int i=0; i<sportelli.size() && !result; i++) {
			if(sportelli.get(i).serviRichiesta(idCliente))
				result = true;
		}
		System.out.println("[GESTORE] Richiesta dal client terminata con esito "+result);
		return result;
	}

	@Override
	public void sottoscrivi(ISportello sportello) throws RemoteException {
		// TODO Auto-generated method stub
		sportelli.add(sportello);
		
		System.out.println("[GESTORE] Sottoscritto nuovo sportello");
		
	}
}
