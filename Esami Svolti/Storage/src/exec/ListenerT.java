package exec;

import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ListenerT implements MessageListener{
	
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		TextMessage text = (TextMessage)arg0;
		
		try {
			StringTokenizer st = new StringTokenizer(text.getText(), "#");
			
			st.nextToken();
			int result = Integer.parseInt(st.nextToken());
			
			Thread thread = new ThreadT(result);
			thread.start();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
