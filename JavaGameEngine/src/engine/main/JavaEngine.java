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
	private Thread t;								//Game thread
	private String gameName;						//Game name
	
	public static JavaEngine instance;				//Running instance of the Java engine

	private boolean isRunning;						//Is game running
	private boolean showPerform;					//Show performance or not
	private long frame;								//Current frame
	private GameScreen screen;						//Game screen
	private ObjectController controller;			//Object controller object
	private ArrayList<GameScript> scripts;			//List of game scripts
	
	private int fps;								//FPS to run at
	private int screenWidth, screenHeight;			//Screen size
	private int gameWidth, gameHeight;				//Game size
	private KeyboardInputHandler keyboardInput;		//Keyboard input handler
	private MouseInputHandler mouseInput;			//Mouse input handler
	
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
		
		controller = new ObjectController();
		
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
			script.preInit();
		}
		
		if(screenWidth < gameWidth || screenHeight < gameHeight) {
			System.err.println("Attempting to make game with screen size less than game size. Errors may occur.");
		}
		
		System.out.println("Initializing screen...");
		screen = new GameScreen(screenWidth, screenHeight, gameName);
		
		mouseInput = new MouseInputHandler(screen, this);
		keyboardInput = new KeyboardInputHandler(screen);
		
		for(GameScript script: scripts) {
			script.init();
		}
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
	
	/**
	 * Returns the object controller for the current instance.
	 * @return ObjectController
	 */
	
	public ObjectController getObjectController() {
		return controller;
	}
	
	/**
	 * Gets the current frame the game is on. Used to measure time passed.
	 * @return current frame
	 */
	
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
	
	/**
	 * Sets how many units fit on the screen for the game. Different than screen size.
	 * @param width width of game
	 * @param height height of game
	 */
	
	public void setGameSize(int width, int height) {
		gameWidth = width;
		gameHeight = height;
	}
	
	/**
	 * Sets the size of the game screen in pixels
	 * @param width width in pixels
	 * @param height height in pixels
	 */
	
	public void setScreenSize(int width, int height) {
		screenWidth = width;
		screenHeight = height;
	}
	
	/**
	 * Sets whether the engine outputs the time taken for a frame in a console.
	 * @param show true if yes
	 */
	
	public void showPerformance(boolean show) {
		showPerform = show;
	}
	
	/**
	 * Gets current X ratio of screen width to game width.
	 * @return x ratio
	 */
	
	public double getXRatio() {
		return screenWidth / gameWidth;
	}
	
	/**
	 * Gets the current Y ratio of screen height to game height.
	 * @return y ratio
	 */
	
	public double getYRatio() {
		return screenHeight / gameHeight;
	}
	
}
