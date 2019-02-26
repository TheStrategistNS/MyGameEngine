package engine.main;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;

public class Sprites {
	private static Sprites instance;
	private HashMap<String,Image> images;
	private HashMap<String,HashMap<Dimension,Sprite>> sprites;
	
	private Sprites() {
		images = new HashMap<String,Image>();
		sprites = new HashMap<String,HashMap<Dimension,Sprite>>();
	}
	
	public static void registerImage(String name, Image img) {
		if(instance == null) {
			instance = new Sprites();
		}
		
		instance.images.put(name, img);
	}
	
	public static void putSprite(String name, Sprite sprite) {
		
	}
	
	public static Sprite getSprite(String name, Dimension size) {
		if(!instance.images.containsKey(name)) {
			return null;
		}
		if(!instance.sprites.containsKey(name)) {
			instance.sprites.put(name, new HashMap<Dimension,Sprite>());
		}
		if(!instance.sprites.get(name).containsKey(size)) {
			instance.sprites.get(name).put(size, new Sprite(instance.images.get(name).getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH)));
		}
		return instance.sprites.get(name).get(size);
	}
}
