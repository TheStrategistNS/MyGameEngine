package engine.main;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;

/**
 * Holds all the sprites currently in use by the game engine. Instantiated the first time it is used.
 * @author Taylor Houthoofd
 *
 */
public class Sprites {
	private static Sprites instance;
	private HashMap<String,Image> images;
	private HashMap<String,HashMap<Dimension,Sprite>> sprites;
	
	/**
	 * Creates the Sprites object.
	 */
	
	private Sprites() {
		images = new HashMap<String,Image>();
		sprites = new HashMap<String,HashMap<Dimension,Sprite>>();
	}
	
	/**
	 * Registers a image with the given name for use as a sprite.
	 * @param name name
	 * @param img image
	 */
	
	public static void registerImage(String name, Image img) {
		if(instance == null) {
			instance = new Sprites();
		}
		
		instance.images.put(name, img);
	}
	
	/**
	 * Stores a premade sprite as an image with a given name. Normally used for generated graphics, otherwise sprites are made automatically.
	 * @param name name
	 * @param sprite sprite
	 */
	
	public static void putSprite(String name, Sprite sprite) {
		if(instance == null) {
			instance = new Sprites();
		}
		
		instance.images.put(name, sprite.getBI());
	}
	
	/**
	 * Gets a sprite of image name with a specified size. If the specified size does not currently exist in the sprite index, creates and returns a sprite of the given size.
	 * @param name name of sprite
	 * @param size size of sprite
	 * @return sprite
	 */
	
	public static Sprite getSprite(String name, Dimension size) {
		if(instance == null) {
			instance = new Sprites();
		}
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
	
	/**
	 * Gets a sprite of image name with a specified size. If the specified size does not currently exist in the sprite index, creates and returns a sprite of the given size.
	 * @param name name of sprite
	 * @param width width of sprite
	 * @param height height of sprite
	 * @return sprite
	 */
	
	public static Sprite getSprite(String name, int width, int height) {
		return getSprite(name,new Dimension(width,height));
	}
}
