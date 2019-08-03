package engine.main;

/**
 * A crash monitor that force closes the game if something breaks.
 * @author Taylor Houthoofd
 *
 */

public class CrashMonitor implements Runnable{
	Thread t;
	JavaEngine e;
	
	/**
	 * Creates a new CrashMonitor that monitors the given JavaEngine.
	 * @param engine JavaEngine
	 */
	
	public CrashMonitor(JavaEngine engine) {
		e = engine;
		t = new Thread(this, "CrashMonitor");
		t.start();
	}
	
	@Override
	public void run() {
		try {
			e.thread.join();
			if(!e.exitedSuccessfully()) {
				System.err.println("Crash detected!");
				System.exit(1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
