 package engine.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteGraphic {
	private BufferedImage graphic;
	private int width, height;
	
	public SpriteGraphic(int width, int height) {
		this.width = width * JavaEngine.instance.getXRatio();
		this.height = height * JavaEngine.instance.getYRatio();
	}
	
	public void Render(Graphics g, int x, int y) {
		x *= JavaEngine.instance.getXRatio();
		y *= JavaEngine.instance.getYRatio();
		g.drawImage(graphic, x, y, width, height, null);
	}
	
	public Graphics getGraphics() {
		graphic = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		return graphic.getGraphics();
	}
	
	public void setImage(String path) {
		try {
			graphic = ImageIO.read(new File(path));
		}catch(Exception e) {
			System.err.printf("Cannot load image '%s'.\n",path);
		}
	}
	
	public boolean isReady() {
		if(graphic != null) {
			return true;
		}
		return false;
	}
}
