package engine.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.interfaces.GameScript;

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

	private boolean isRunning;
	private GameScreen screen;
	private ArrayList<GameScript> scripts;
	
	private int fps;
	private int screenWidth, screenHeight;
	private KeyboardInputHandler keyboardInput;
	private MouseInputHandler mouseInput;
	
	/**
	 * Sets up the engine for use.
	 * @param name Name of the game.
	 */
	
	public JavaEngine(String name) {
		gameName = name;
		
		screenWidth = 800;
		screenHeight = 600;
		fps = 60;
		
		scripts = new ArrayList<GameScript>();
		
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
		
		System.out.printf("Initializing %d scripts...\n", scripts.size());
		
		for(GameScript script: scripts) {
			script.init();
		}
		
		System.out.println("Initializing screen...");
		screen = new GameScreen(screenWidth, screenHeight, gameName);
		
		mouseInput = new MouseInputHandler(screen);
		keyboardInput = new KeyboardInputHandler(screen);
	}
	
	/**
	 * Runs through the update method in all GameScripts
	 */
	
	private void update() {
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
	
}
