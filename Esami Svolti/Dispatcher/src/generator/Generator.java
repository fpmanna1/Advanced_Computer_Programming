package generator;

import reading.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dispatcher.*;

public class Generator {
	public static void main(String[] args) {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IDispatcher dispatcher = (IDispatcher) rmiregistry.lookup("mydispatcher");
			
			Thread[] threads = new Thread[3];
			
			for(int i=0; i<3; i++) {
				threads[i] = new GeneratorThread(dispatcher);
				
				System.out.println("[GENERATOR] Thread "+i+" avviato");
				
				threads[i].start();
			}
			
			for(int i=0; i<3; i++) {
				threads[i].join();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
