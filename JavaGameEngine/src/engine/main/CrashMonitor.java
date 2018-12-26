package engine.main;

public class CrashMonitor implements Runnable{
	Thread t;
	JavaEngine e;
	
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
