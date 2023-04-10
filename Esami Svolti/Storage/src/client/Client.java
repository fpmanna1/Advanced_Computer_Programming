package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	private static final int num_request = 5;
	
	public static void main(String[] args) {
		String type = new String(args[0]);
		type.toLowerCase();
		
		try {
			Hashtable<String, String> prop = new Hashtable<String, String>();
		
			prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
			prop.put("topic."+type, type);
		
			Context context = new InitialContext(prop);
		
			TopicConnectionFactory conn = (TopicConnectionFactory)context.lookup("TopicConnectionFactory");
			Topic topic = (Topic) context.lookup(type);
		
			TopicConnection tconn = conn.createTopicConnection();
			TopicSession session = (TopicSession) tconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
			TopicPublisher pub = session.createPublisher(topic);
		
		
			MapMessage msg = session.createMapMessage();
			TextMessage text = session.createTextMessage();
		
			for(int i=0; i<num_request; i++) {
				Thread.sleep(2000);
			
				if(type.equals("math")) {
			
					msg.setInt("val1", new Random().nextInt(100));
					msg.setInt("val2", new Random().nextInt(100));
					
					pub.publish(msg);
					
				}else if(type.equals("text")) {
					
					text.setText("save#"+new Random().nextInt(100));
					pub.publish(text);
				}else {
					System.out.println("[CLIENT] Invalid type");
				}
				
				System.out.println("[CLIENT] Invio messaggio sul topic "+type);
				
			}
		
			session.close();
			tconn.close();
			pub.close();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
