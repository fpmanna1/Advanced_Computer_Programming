package logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoggerImpl implements ILogger{
	
	private Lock lock;
	
	public LoggerImpl() {
		lock = new ReentrantLock();
	}

	@Override
	public void registraDato(int dato) {
		// TODO Auto-generated method stub
		lock.lock();
		
		try {
			FileOutputStream file = new FileOutputStream("logger.txt", true);
			PrintWriter printw = new PrintWriter(file);
			
			System.out.println("[LOGGER] Dato ricevuto : "+dato);
			printw.println(dato);
			
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
	}

}
