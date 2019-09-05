package engine.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * A sprite that gets drawn to the screen.
 * @author Taylor Houthoofd
 *
 */
public class Sprite {

	private Image g;
	
	/**
	 * Creates a new blank sprite.
	 */
	
	public Sprite() {
		g = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * Creates a sprite of the given image.
	 * @param img image
	 */
	
	public Sprite(Image img) {
		g = img;
	}
	
	/**
	 * Creates a copy of another sprite.
	 * @param other other sprite
	 */
	
	public Sprite(Sprite other) {
		g = other.getBI();
	}
	
	/**
	 * Gets the graphics object of the sprite's image. Used for drawing on the sprite.
	 * @return graphics
	 */
	
	public Graphics getGraphics() {
		return g.getGraphics();
	}
	
	/**
	 * Gets the sprite image.
	 * @return image
	 */
	
	public Image getBI() {
		return g;
	}
}
