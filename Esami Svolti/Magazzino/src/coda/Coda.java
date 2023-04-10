package coda;

import java.util.Vector;

public class Coda implements ICoda{
	private Vector<Integer> id_articoli;
	private int count;
	private int head;
	private int tail;
	private int max_size;
	
	public Coda(int max_size) {
		this.max_size = max_size;
		id_articoli = new Vector<Integer>(max_size);
		count = head = tail = 0;
	}
	
	public int getSize() {
		return id_articoli.size();
	}

	@Override
	public void push(int id) {
		// TODO Auto-generated method stub
		this.id_articoli.add(id);
		this.count++;
		head = (head+1)%max_size;
	}

	@Override
	public int pop() {
		// TODO Auto-generated method stub
		int value = this.id_articoli.get(tail);
		count--;
		tail = (tail+1)%max_size;
		return value;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return count==max_size;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return count==0;
	}
	

	
	
}
