package logger;

public class Logger {
	public static void main(String[] args) {
		// deve istanziare l'implementazione e lo skeleton
		
		int port = Integer.parseInt(args[0]);
		ILogger logger = new LoggerImpl();
		
		Skeleton sk = new Skeleton(logger, port);
		sk.runSkeleton();
	}
}
