package actuator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import controller.IController;

public class Actuator {
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		String docname = new String(args[1]);
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IController controller = (IController)rmiregistry.lookup("mycontroller");
			controller.addActuator(Integer.parseInt(args[0]));
			
			ActuatorImpl actuator = new ActuatorImpl(port, docname);
			actuator.runSkeleton();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
