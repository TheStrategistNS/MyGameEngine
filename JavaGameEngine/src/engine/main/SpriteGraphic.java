 package engine.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The graphic object associated with a GameObject. Describes the appearance of the GameObject. 
 * Can use an image as the appearance.
 * @author Taylor Houthoofd
 *
 */

public class SpriteGraphic {
	private BufferedImage graphic;
	private int width, height;
	
	/**
	 * Constructor.
	 * @param width width of attached GameObject
	 * @param height height of attached GameObject
	 */
	
	public SpriteGraphic(int width, int height) {
		this.width = (int)(width * JavaEngine.getXRatio());
		this.height = (int)(height * JavaEngine.getYRatio());
	}
	
	/**
	 * Draws the graphic to the screen.
	 * @param g the screen's graphics object
	 * @param x x position of object
	 * @param y y position of object
	 */
	
	public void Render(Graphics g, int x, int y) {
		x *= JavaEngine.getXRatio();
		y *= JavaEngine.getYRatio();
		g.drawImage(graphic, x, y, width, height, null);
	}
	
	public void setSprite(Sprite sprite) {
		graphic = sprite.getBI();
	}
	
	/**
	 * Checks whether the SpriteGraphic has been set up for use.
	 * @return true if set up
	 */
	
	public boolean isReady() {
		if(graphic != null) {
			return true;
		}
		return false;
	}
}
