package magazzino;

public class Magazzino {
	public static void main(String[] args) {
		MagazzinoImpl magazzino = new MagazzinoImpl();
		
		System.out.println("[MAGAZZINO] Avvio server");
		
		magazzino.runSkeleton();
		
	}
}
