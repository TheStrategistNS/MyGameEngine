package engine.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {

	private Image g;
	
	public Sprite() {
		g = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
	}
	
	public Sprite(Image img) {
		g = img;
	}
	
	public Sprite(Sprite other) {
		g = other.getBI();
	}
	
	public Graphics getGraphics() {
		return g.getGraphics();
	}
	
	public Image getBI() {
		return g;
	}
}
