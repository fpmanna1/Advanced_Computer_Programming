package exec;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ExecT {
	
	public static void main(String[] args) {
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		prop.put("topic.text", "text");
		
		try {
			Context context = new InitialContext(prop);
			
			TopicConnectionFactory conn = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic topic = (Topic) context.lookup("text");
			
			TopicConnection tconn = (TopicConnection) conn.createConnection();
			tconn.start();
			TopicSession session = (TopicSession) tconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicSubscriber sub = session.createSubscriber(topic);
			
			ListenerT listener = new ListenerT();
		
			sub.setMessageListener(listener);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}
