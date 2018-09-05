package engine.main;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * The game screen.
 * @author Taylor Houthoofd
 *
 */

public class GameScreen extends JFrame {
	
	private static final long serialVersionUID = 4944507058383316306L;
	
	private Insets insets;
	
	/**
	 * Sets up the game screen. Readjusts size to accommodate inserts.
	 * @param width Width of game screen.
	 * @param height Height of game screen.
	 * @param name Name of game.
	 */
	
	public GameScreen(int width, int height, String name) {
		this.setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(name);
		this.setVisible(true);
		
		insets = this.getInsets();
		setSize(insets.left + width + insets.right, insets.top + height + insets.bottom);
	}
	
	/**
	 * Draws the backbuffer to the screen.
	 * @param backbuffer image to draw.
	 */
	
	public void render(BufferedImage backbuffer) {
		
		Graphics g = this.getGraphics();
		g.drawImage(backbuffer, insets.left, insets.top, this);
	}
}
