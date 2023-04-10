package coda;

public interface ICoda {
	
	public int getSize();
	
	public void push(int id);
	
	public int pop();
	
	boolean full();
	
	boolean empty();
}
