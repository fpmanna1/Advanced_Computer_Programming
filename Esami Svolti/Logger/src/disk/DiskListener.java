package disk;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class DiskListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage msg = (MapMessage)arg0;
		Thread thread = new DiskThread(msg);
		
		System.out.println("[LISTENER] DiskThread avviato");
		thread.start();
	}
}
