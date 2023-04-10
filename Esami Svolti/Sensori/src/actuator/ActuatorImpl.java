package actuator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ActuatorImpl extends Skeleton {

	
	private int port;
	String docname;
	private Lock lock;
	
	public ActuatorImpl(int port, String docname) {
		super(port);
		// TODO Auto-generated constructor stub
		lock = new ReentrantLock();
		this.docname = new String(docname);
	}

	@Override
	public boolean execute(String action) {
		// TODO Auto-generated method stub
		if(!lock.tryLock()) {
			System.out.println("[ACTUATOR] Execute fallita");
			return false;
		}
		
		StringTokenizer st = new StringTokenizer(action, "#");
		String type = st.nextToken();
		int value = Integer.parseInt(st.nextToken());
		
		System.out.println("[ACTUATOR] Ricevuto dati sensori, ora eseguo..");
		System.out.println("TYPE  :"+type+" DATA : "+value);
		
		try {
			FileOutputStream file = new FileOutputStream(docname, true);
			PrintWriter printw = new PrintWriter(file);
			printw.println("TYPE  :"+type+" DATA : "+value);
			
			printw.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return true;
	}

}
