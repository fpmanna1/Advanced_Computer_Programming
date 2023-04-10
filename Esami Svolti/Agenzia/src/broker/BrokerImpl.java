package broker;

import agenzia.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class BrokerImpl extends UnicastRemoteObject implements IBroker{

	private static final long serialVersionUID = 2293289982600742766L;

	private Vector<IAgenzia> agenzie;
	private int indice;
	private int max;
	
	protected BrokerImpl() throws RemoteException {
		super();
		agenzie = new Vector<IAgenzia>();
		this.indice = this.max = 0;
	}

	@Override
	public void sottoponi(int tipoOperazione, int quantita) throws RemoteException {
		// TODO Auto-generated method stub
		// un'agenzia è invocata a turno ogni 3 richieste
		this.indice++;
		System.out.println("[BROKER] Inoltro la richiesta all'agenzia "+indice%max);
		
		
		switch(tipoOperazione) {
		case 1:
			agenzie.get(indice).acquista(quantita);
			break;
		case 2:
			agenzie.get(indice).vendi(quantita);
			break;
		default:
			System.out.println("[BROKER] Invalid operation");
			break;
		}	
	}

	@Override
	public void sottoscrivi(int port) throws RemoteException {
		// TODO Auto-generated method stub
		Proxy proxy = new Proxy(port);
		agenzie.add(proxy);
		
		System.out.println("[BROKER] Agenzia sottoscritta");
		this.max++;
	}

}
