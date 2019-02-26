package engine.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {

	private BufferedImage g;
	
	public Sprite(Image img) {
		g = (BufferedImage)img;
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
}
