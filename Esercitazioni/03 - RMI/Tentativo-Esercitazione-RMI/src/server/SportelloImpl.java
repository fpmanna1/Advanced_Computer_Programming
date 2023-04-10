package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.Semaphore;

/*
 * 1. Sportello, che offre il servizio boolean serviRichiesta( int idCliente ). 
 * Ogni richiesta di servizio dura un tempo scelto a caso tra 1 e 5 secondi. 
 * Al termine della richiesta idCliente è salvato su un file. 
 * Lo Sportello, al proprio avvio, si sottoscrive al Gestore (invocazione di sottoscrivi).
 * Uno Sportello può servire in maniera concorrente 3 richieste. 
 * Ogni ulteriore richiesta è messa in attesa; tuttavia, al più 2 richieste 
 * possono essere accodate nell’attesa che lo Sportello abbia servito una richiesta precedente. 
 * Nel caso in cui la richiesta non può essere accodata, lo Sportello restituisce
 * immediatamente esito false. Le richieste servite correttamente terminano con esito true.
 */
import interfaces.ISportello;


public class SportelloImpl extends UnicastRemoteObject implements ISportello{
	
	
	private static final long serialVersionUID = -2572272653508662516L;

	private Semaphore maxConcurrentRequests;
	private Semaphore maxRequests;
	
	protected SportelloImpl() throws RemoteException{
		
		maxConcurrentRequests = new Semaphore(3);
		maxRequests = new Semaphore(5);
		// 5 richieste entrano, dopo le prime 3 acquire, le ulteriori
		// 2 richieste vengono messe in attesa
	}

	@Override
	public boolean serviRichiesta(int idCliente){
		
		boolean result = false;
		
		if(!maxRequests.tryAcquire()) { // se non ci sono permessi
			System.out.println("[SPORTELLO] Raggiunto limite massimo di richieste");
			System.out.println("[SPORTELLO] Richiesta da cliente" + idCliente + " terminata con esito " + result);
			
			return result;
		}
		
		try {
			
			maxConcurrentRequests.acquire();
			
			Random rand = new Random();
			
			Thread.sleep((rand.nextInt(5)+1) * 1000);
			
			// SCRITTURA SUL FILE IN MODO FORMATTATO
			FileWriter fileWriter = new FileWriter("richieste.txt", true);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(buffer);
			
			printWriter.println(idCliente);
			// essendo buffered la scrittura, dobbiamo effettuare il flush del printWriter
			printWriter.flush();
			
			result = true;
			
			printWriter.close();
			buffer.close();
			fileWriter.close();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			result = false;
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} finally {
			maxConcurrentRequests.release();
			maxRequests.release();
		}
		
		return result;
	}

}
