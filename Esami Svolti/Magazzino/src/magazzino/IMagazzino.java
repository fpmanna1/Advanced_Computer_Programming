package magazzino;

public interface IMagazzino {
	
	void deposita(String tipo, int id);
	
	int preleva(String tipo);
}
