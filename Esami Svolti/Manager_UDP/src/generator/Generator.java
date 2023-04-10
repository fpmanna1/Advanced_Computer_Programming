package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import manager.IManager;

public class Generator {
	public static void main(String[] args) {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IManager manager = (IManager) rmiregistry.lookup("mymanager");
			
			Thread threads[] = new Thread[3];
			
			for(int i=0; i<3; i++) {
				threads[i] = new GeneratorThread(manager);
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
