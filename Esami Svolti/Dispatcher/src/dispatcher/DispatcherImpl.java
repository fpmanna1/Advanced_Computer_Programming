package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import observer.IObserver;
import reading.Reading;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{

	private static final long serialVersionUID = 8371745228253987835L;
	
	private Vector<IObserver> obs_temperatura;
	private Vector<IObserver> obs_pressione;
	private Lock lock;
	private Reading state;
	
	public DispatcherImpl() throws RemoteException {
		super();
		obs_temperatura = new Vector<IObserver>();
		obs_pressione = new Vector<IObserver>();
		lock = new ReentrantLock();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setReading(Reading reading) throws RemoteException {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			Thread.sleep(1000*(1+new Random().nextInt(5)));
			
			if(reading.getTipo().equals("temperatura")) {
				for(int i=0; i<obs_temperatura.size(); i++) {
					System.out.println("[DISPATCHER] Notifica per obs temperatura");
					obs_temperatura.get(i).notifyReading();
				}
			}else {
				for(int i=0; i<obs_pressione.size(); i++) {
					System.out.println("[DISPATCHER] Notifica per obs pressione");
					obs_pressione.get(i).notifyReading();
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public Reading getReading() throws RemoteException{
		return this.state;
	}

	@Override
	public void attachObserver(IObserver observer, String tipo) throws RemoteException {
		// TODO Auto-generated method stub
		
		if(tipo.equals("temperatura"))
			obs_temperatura.add(observer);
		else
			obs_pressione.add(observer);
		System.out.println("[DISPATCHER] Aggiunto observer di tipo "+tipo);
	}

}
