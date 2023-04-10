package exec;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ListenerM implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage msg = (MapMessage) arg0;
		
		try {
			int val1 = msg.getInt("val1");
			int val2 = msg.getInt("val2");
			
			int result = val1 * val2;
			
			Thread thread = new ThreadM(result);
			thread.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
