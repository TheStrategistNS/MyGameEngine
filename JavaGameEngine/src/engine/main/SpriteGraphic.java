 package engine.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * The graphic object associated with a GameObject. Describes the appearance of the GameObject. 
 * Can use an image as the appearance.
 * @author Taylor Houthoofd
 *
 */

public class SpriteGraphic {
	private BufferedImage graphic;
	private int width, height;
	private boolean vis;
	
	/**
	 * Constructor.
	 * @param width width of attached GameObject
	 * @param height height of attached GameObject
	 */
	
	public SpriteGraphic(int width, int height) {
		this.width = (int)(width * JavaEngine.instance.getXRatio());
		this.height = (int)(height * JavaEngine.instance.getYRatio());
		vis = true;
	}
	
	/**
	 * Draws the graphic to the screen.
	 * @param g the screen's graphics object
	 * @param x x position of object
	 * @param y y position of object
	 */
	
	public void Render(Graphics g, int x, int y) {
		if(vis && isReady()) {
			x *= JavaEngine.instance.getXRatio();
			y *= JavaEngine.instance.getYRatio();
			g.drawImage(graphic, x, y, width, height, null);
		}
	}
	
	/**
	 * Gets the graphics object for this SpriteGraphic.
	 * @return graphics object
	 */
	
	public Graphics getGraphics() {
		graphic = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		return graphic.getGraphics();
	}
	
	/**
	 * Sets an image as the graphic for this SpriteGraphic.
	 * @param path path for the image ("example.png")
	 */
	
	public void setImage(String path) {
		try {
			graphic = ImageIO.read(new File(path));
		}catch(Exception e) {
			System.err.printf("Cannot load image '%s'.\n",path);
		}
	}
	
	/**
	 * Checks whether the SpriteGraphic has been set up for use.
	 * @return true if set up
	 */
	
	private boolean isReady() {
		if(graphic != null) {
			return true;
		}
		return false;
	}
	
	public void setVisible(boolean visible) {
		vis = visible;
	}
}
