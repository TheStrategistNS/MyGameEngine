package engine.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage g;
	
	public Sprite() {
		g = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
	}
	
	public Sprite(String path) {
		try {
			g = ImageIO.read(new File(path));
		}
		catch(Exception e) {
			System.err.printf("Cannot load image '%s'.\n",path);
		}
	}
	
	public Sprite(Sprite other) {
		g = other.getBI();
	}
	
	public Graphics getGraphics() {
		return g.getGraphics();
	}
	
	public BufferedImage getBI() {
		return g;
	}
	
	public void setImage(String path) {
		try {
			g.getGraphics().drawImage(ImageIO.read(new File(path)), 0, 0, 100, 100, null);
		}
		catch(Exception e) {
			System.err.printf("Cannot load image '%s'.\n",path);
		}
	}
}
