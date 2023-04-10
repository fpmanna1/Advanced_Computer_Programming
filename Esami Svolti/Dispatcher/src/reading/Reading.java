package reading;

import java.io.Serializable;

public class Reading implements Serializable{
	
	private static final long serialVersionUID = 5776402166708934570L;
	
	private String tipo;
	private int valore;
	
	public Reading(String tipo, int valore) {
		this.tipo = tipo;
		this.valore = valore;
	}
	
	public Reading(Reading reading) {
		// TODO Auto-generated constructor stub
		this.tipo = reading.getTipo();
		this.valore = reading.getValore();
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public int getValore() {
		return this.valore;
	}
	
	public void setTipo(String tipo) {
		this.tipo = new String(tipo);
	}
	
	public void setValore(int valore) {
		this.valore = valore;
	}
	
	
}
