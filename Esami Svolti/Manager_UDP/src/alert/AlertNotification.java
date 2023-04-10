package alert;

import java.io.Serializable;

public class AlertNotification implements Serializable{

	private static final long serialVersionUID = 2197677908899888874L;

	private int componentID;
	private int critically;
	
	public AlertNotification(int componentID, int critically) {
		this.componentID = componentID;
		this.critically = critically;
	}
	
	public int getComponentID() {
		return this.componentID;
	}
	
	public int getCritically() {
		return this.critically;
	}

}
