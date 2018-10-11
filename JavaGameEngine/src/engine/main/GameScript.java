package engine.main;

import java.awt.Graphics;

/**
 * GameScript class. Runs the game. Contains the methods the engine uses to make the game run.
 * @author Taylor Houthoofd
 *
 */

public interface GameScript {
	
	/**
	 * Called once when the game starts, before the game screen is initialized.
	 */
	
	public abstract void preInit();
	
	/**
	 * Called once when the game starts, after the game screen is initialized.
	 */
	
	public abstract void init();
	
	/**
	 * Called every frame.
	 */
	
	public abstract void update();
	
	/**
	 * Called every frame. Used to draw graphics to the screen.
	 * @param g Graphics object used to interact with the screen.
	 */
	
	public abstract void render(Graphics g);
}
