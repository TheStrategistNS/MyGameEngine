package engine.main;

public class CrashMonitor implements Runnable {
	private Thread monitor;
	private Thread game;
	
	public CrashMonitor() {
		game = Thread.currentThread();
		monitor = new Thread(this,"Crash_Monitor");
		monitor.start();
	}
	
	public void run() {
		try {
			game.join();
			if(!JavaEngine.isFinished()) {
				System.err.println("Crash detected!");
				System.err.println("Closing program.");
				System.exit(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
