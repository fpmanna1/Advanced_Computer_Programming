package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	public static void main(String[] args) {
		
		int dato = Integer.parseInt(args[0]);
		int port = Integer.parseInt(args[1]);
		
		// tutte le varie cose di JMS
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		prop.put("queue.storage", "storage");
		
		try {
			Context context = new InitialContext(prop);
			QueueConnectionFactory conn = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			
			Queue queue = (Queue) context.lookup("storage");
			QueueConnection qconn = conn.createQueueConnection();
			
			QueueSession session = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueSender sender = session.createSender(queue);
			MapMessage message = session.createMapMessage();
			
			message.setInt("dato", dato);
			message.setInt("port", port);
			
			sender.send(message);
			
			System.out.println("[CLIENT] Messaggio con dato: "+dato+" e porta: "+port+" inviato");
			
			sender.close();
			session.close();
			qconn.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
