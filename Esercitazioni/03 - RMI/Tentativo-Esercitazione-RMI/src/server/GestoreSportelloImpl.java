package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IGestoreSportello;
import interfaces.ISportello;

/*
 * 2. GestoreSportello: prende in carico le richieste di servizio 
 * dei Client tramite boolean sottoponiRichiesta( int idCliente ) 
 * e le smista verso un apposito Sportello. Gestore Sportello possiede 
 * una lista di Sportelli ordinata in base all’ordine delle sottoscizioni, 
 * ed invoca in sequenza ciascuno Sportello. 
 * La richiesta è servita dal primo sportello libero: 
 * al termine della richiesta il Gestore restituisce al Client esito true. 
 * Il Gestore restituisce esito false se ogni Sportello restituisce false.
 */


public class GestoreSportelloImpl extends UnicastRemoteObject implements IGestoreSportello{

	private static final long serialVersionUID = 4988481266638687111L;
	
	private Vector<ISportello> listaSportelli;

	protected GestoreSportelloImpl() throws RemoteException{
		
		listaSportelli = new Vector<ISportello>();
		
	}

	@Override
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException {
		
		boolean result = false;
		// valore di ritorno della richiesta che effettuiamo su sportello
		int i = 0;
		
		while((!result) && (i < listaSportelli.size())) { // non ho ancora trovato uno sportello utile (tutti occupati con code sature)
			// ISportello sportello = listaSportelli.get(i);
			// result = sportello.serviRichiesta(idCliente);
			result = listaSportelli.get(i).serviRichiesta(idCliente);
			i++;
		}
		
		System.out.println("[SPORTELLO] Richiesta dal client " + idCliente + " terminata con esito " + result);
		
		return result;
	}

	@Override
	public void sottoscrivi(ISportello sportello) throws RemoteException {
		
		listaSportelli.add(sportello);
		
		System.out.println("[SPORTELLO] Sottoscritto nuovo sportello");
	}

}
