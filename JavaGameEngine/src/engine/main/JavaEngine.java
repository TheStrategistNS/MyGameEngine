package engine.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>My basic Java game engine. For use in java code.</p>
 * <p>Works through GameScript interfaces. Each script, once added to the engine, gets initialized on start, then runs through an update and run method every frame.</p>
 * 
 * @author Taylor Houthoofd
 *
 */

public class JavaEngine implements Runnable {
	private Thread t;
	private String gameName;
	
	public static JavaEngine instance;

	private boolean isRunning, showPerform;
	private long frame;
	private GameScreen screen;
	private ObjectController controller;
	private ArrayList<GameScript> scripts;
	
	private int fps;
	private int screenWidth, screenHeight;
	private int gameWidth, gameHeight;
	private KeyboardInputHandler keyboardInput;
	private MouseInputHandler mouseInput;
	
	/**
	 * Sets up the engine for use.
	 * @param name Name of the game.
	 */
	
	public JavaEngine(String name) {
		gameName = name;
		showPerform = false;
		
		screenWidth = 800;
		screenHeight = 600;
		fps = 60;
		
		scripts = new ArrayList<GameScript>();
		
		controller = new ObjectController(this);
		
		instance = this;
		
		System.out.println("Engine created successfully");
	}
	
	/**
	 * Game loop. Initializes and runs the game. Keeps frames stable by limiting FPS.
	 */

	public void run() {
		System.out.println("Game initializing...");
		init();
		
		System.out.println("Game running...");
		while(isRunning) {
			long time = System.currentTimeMillis();
			
			update();
			render();
			
			time = (1000 / fps) - (System.currentTimeMillis() - time);
			
			if(showPerform && frame % fps == 0) {
				System.out.printf("frame %d completed with time of %d\n", frame, time * -1);
			}
			
			if(time > 0) {
				try {
					Thread.sleep(time);
				}
				catch(Exception e) {}
			}
		}
		
		System.out.println("Game stopping...");
		screen.setVisible(false);
	}
	
	/**
	 * Initializes the game. Runs through all added scripts and initializes them.
	 */
	
	private void init() {
		isRunning = true;
		frame = 0;
		
		System.out.printf("Initializing %d scripts...\n", scripts.size());
		
		for(GameScript script: scripts) {
			script.init();
		}
		
		if(screenWidth < gameWidth || screenHeight < gameHeight) {
			System.err.println("Attempting to make game with screen size less than game size. Errors may occur.");
		}
		
		System.out.println("Initializing screen...");
		screen = new GameScreen(screenWidth, screenHeight, gameName);
		
		mouseInput = new MouseInputHandler(screen, this);
		keyboardInput = new KeyboardInputHandler(screen);
	}
	
	/**
	 * Runs through the update method in all GameScripts
	 */
	
	private void update() {
		frame++;
		
		controller.Update();
		
		for(GameScript script: scripts) {
			script.update();
		}
	}
	
	/**
	 * Runs through the render method in all GameScripts. Passes a graphics object with which to draw to the screen.
	 */
	
	private void render() {
		BufferedImage backbuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		Graphics bbg = backbuffer.getGraphics();
		
		controller.Render(bbg);
		
		for(GameScript script: scripts) {
			script.render(bbg);
		}

		screen.render(backbuffer);
	}
	
	/**
	 * Adds a GameScript to the engine, to be run at start.
	 * @param script Script to be added.
	 */
	
	public void addGameScript(GameScript script) {
		scripts.add(script);
		System.out.println("Script added");
	}
	
	/**
	 * Gets the keyboard input handler for the screen.
	 * @return Keyboard handler.
	 */
	
	public KeyboardInputHandler getKeyboardInput() {
		return keyboardInput;
	}
	
	/**
	 * Gets the mouse input handler for the screen.
	 * @return Mouse handler.
	 */
	
	public MouseInputHandler getMouseInput() {
		return mouseInput;
	}
	
	public ObjectController getObjectController() {
		return controller;
	}
	
	public long getFrame() {
		return frame;
	}
	
	/**
	 * Starts the game thread.
	 */
	
	public void start() {
		System.out.printf("Thread '%s' starting...\n", gameName);
		if(t == null) {
			t = new Thread(this, gameName);
			t.start();
		}
	}
	
	public void setGameSize(int width, int height) {
		gameWidth = width;
		gameHeight = height;
	}
	
	public void setScreenSize(int width, int height) {
		screenWidth = width;
		screenHeight = height;
	}
	
	public void showPerformance(boolean show) {
		showPerform = show;
	}
	
	public double getXRatio() {
		return screenWidth / gameWidth;
	}
	
	public double getYRatio() {
		return screenHeight / gameHeight;
	}
	
}
