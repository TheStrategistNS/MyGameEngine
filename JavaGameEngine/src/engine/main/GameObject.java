package engine.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Base class for all GameObjects in the game.
 * @author Taylor Houthoofd
 *
 */

public abstract class GameObject {
	private static final int DEFAULT_DRAW_LEVEL = 5;
	
	protected Coord pos;
	private Coord com;
	protected Dimension size;
	private ObjectController controller;
	private int drawLevel;
	protected SpriteGraphic graphic;
	
	private GameEventListener listener;
	private ArrayList<String> tags;
	private ArrayList<GameComponent> components;
	
	//Constructors
	
	/**
	 * Creates a GameObject with specified size and position.
	 * @param pos position in game of object
	 * @param size size of object
	 */
	
	public GameObject(Coord pos, Dimension size) {
		this.pos = pos;
		this.size = size;
		instantiation();
	}
	
	/**
	 * Creates a GameObject with specified size and position.
	 * @param pos position in game of object
	 * @param size size of object
	 */
	
	public GameObject(Point pos, Dimension size) {
		this.pos = new Coord(pos);
		this.size = size;
		instantiation();
	}
	
	/**
	 * Creates a GameObject with specified size and position.
	 * @param x x position in game of object
	 * @param y y position in game of object
	 * @param size size of object
	 */
	
	public GameObject(double x, double y, Dimension size) {
		pos = new Coord(x, y);
		this.size = size;
		instantiation();
	}
	
	/**
	 * Creates a GameObject with specified size and position.
	 * @param pos position in game of object
	 * @param width width of object
	 * @param height height of object
	 */
	
	public GameObject(Coord pos, int width, int height) {
		this.pos = pos;
		size = new Dimension(width, height);
		instantiation();
	}
	
	/**
	 * Creates a GameObject with specified size and position.
	 * @param pos pos position in game of object
	 * @param width width of object
	 * @param height height of object
	 */
	
	public GameObject(Point pos, int width, int height) {
		this.pos = new Coord(pos);
		size = new Dimension(width, height);
		instantiation();
	}
	
	/**
	 * Creates a GameObject with specified size and position.
	 * @param x x position in game of object
	 * @param y y position in game of object
	 * @param width width of object
	 * @param height height of object
	 */
	
	public GameObject(double x, double y, int width, int height) {
		pos = new Coord(x, y);
		size = new Dimension(width, height);
		instantiation();
	}
	
	/**
	 * Instantiates various instantiations, and calculates center of mass.
	 */
	
	private void instantiation() {
		recalcCom();
		tags = new ArrayList<String>();
		components = new ArrayList<GameComponent>();
		drawLevel = DEFAULT_DRAW_LEVEL;
		graphic = new SpriteGraphic(size.width, size.height);
	}
	
	//Public methods
	
	/**
	 * Updates all object components, then updates object.
	 */
	
	void startUpdate() {
		for(GameComponent c: components) {
			c.Update();
		}
		Update();
	}
	
	/**
	 * Returns the draw layer of the object.
	 * @return draw layer
	 */
	
	public int getDrawLevel() {
		return drawLevel;
	}
	
	/**
	 * Sets the draw layer of the object. Lower draw levels get drawn to the screen before higher ones.
	 * @param level draw level
	 */
	
	public void setDrawLevel(int level) {
		if(level >= 0) {
			drawLevel = level;
		}else {
			drawLevel = 0;
		}
	}
	
	/**
	 * Gets the position of the object.
	 * @return position
	 */
	
	public Coord getPos() {
		return pos;
	}
	
	/**
	 * Sets the position of the object and recalculates center of mass.
	 * @param newPos new position of object
	 */
	
	public void setPos(Coord newPos) {
		pos = newPos;
		recalcCom();
	}
	
	/**
	 * Sets the position of the object and recalculates center of mass.
	 * @param newPos new position of object
	 */
	
	public void setPos(Point newPos) {
		pos = new Coord(newPos);
		recalcCom();
	}
	
	/**
	 * Sets the position of the object and recalculates center of mass.
	 * @param x new x position
	 * @param y new y position
	 */
	
	public void setPos(double x, double y) {
		pos = new Coord(x, y);
		recalcCom();
	}
	
	/**
	 * Increments the position of the object and recalculates center of mass.
	 * @param x how far to move x
	 * @param y how far to move y
	 */
	
	public void incPos(double x, double y) {
		pos.x += x;
		pos.y += y;
		recalcCom();
	}
	
	/**
	 * Returns the object's center of mass.
	 * @return center of mass
	 */
	
	public Coord getCom() {
		return com;
	}
	
	/**
	 * Returns the object's size.
	 * @return size
	 */
	
	public Dimension getSize() {
		return size;
	}
	
	/**
	 * Gets all String tags associated with this object.
	 * @return tags
	 */
	
	public String[] getTags() {
		String[] tags = new String[this.tags.size()];
		for(int i = 0; i < this.tags.size(); i++) {
			tags[i] = this.tags.get(i);
		}
		return tags;
	}
	
	/**
	 * Checks to see if this object is associated with a String tag.
	 * @param tag tag to check
	 * @return true if contained
	 */
	
	public boolean containsTag(String tag) {
		if(tags.contains(tag)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether or not a point lies within the bounds of an object.
	 * @param point point to check
	 * @return true if yes
	 */
	
	boolean isTouching(Point point) {
		boolean touching = true;
		
		if(point.getX() <= pos.getX() || point.getX() >= pos.getX() + size.getWidth()) {
			touching = false;
		}
		if(point.getY() <= pos.getY() || point.getY() >= pos.getY() + size.getHeight()) {
			touching = false;
		}
		
		return touching;
	}
	
	/**
	 * Adds the object controller to the game object. Allows for object to remove itself and get other objects.
	 * @param controller Object controller
	 */
	
	void addObjectController(ObjectController controller) {
		this.controller = controller;
	}
	
	/**
	 * Removes this object from the game's active objects. Generates an onDestroy event for the object.
	 */
	
	public void kill() {
		GameEvent e = new GameEvent(GameEvent.ON_DESTROY);
		triggerEvent(this, e);
		controller.removeObject(this);
	}
	
	/**
	 * Renders the object to the screen using its SpriteGraphic object. If SpriteGraphic is not set up, 
	 * kills the object.
	 * @param g Graphics object used to render to screen.
	 */
	
	public void Render(Graphics g) {
		if(graphic.isReady()) {
			graphic.Render(g, (int)pos.x, (int)pos.y);
		}
		else {
			System.err.println("Trying to render object not set up. Deleteing object.");
			kill();
		}
	}
	
	/**
	 * Sets the sprite for this object given the sprite name, width and height.
	 * @param name name of the sprite
	 * @param width needed width of the sprite
	 * @param height needed height of the sprite
	 */
	
	public void setSprite(String name, int width, int height) {
		graphic.setSprite(Sprites.getSprite(name, width, height));
	}
	
	/**
	 * Triggers an event for this object. Calls the onEvent method for this object's event listener.
	 * @param sender GameObject calling the method
	 * @param e GameEvent being sent
	 */
	
	public void triggerEvent(GameObject sender, GameEvent e) {
		if(listener != null) {
			listener.onEvent(sender, e);
		}
	}
	
	/**
	 * Sets whether this object will trigger an event when clicked.
	 * @param clickable generate event
	 */
	
	public void setClickable(boolean clickable) {
		if(clickable) {
			addTag("MouseInteract");
		}else{
			removeTag("MouseInteract");
		}
	}
	
	//Protected methods
	
	/**
	 * Adds a GameEventListener for this GameObject. The listener's onEvent method is called whenever an event 
	 * for this object is triggered.
	 * @param listener GameEventListener to add
	 */
	
	protected void addGameEventListener(GameEventListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Adds a GameComponent to this object.
	 * @param c GameComponent to add
	 */
	
	protected void addComponent(GameComponent c) {
		c.addTo(this);
		components.add(c);
		
		if(c.type() == GameComponent.COLLIDER) {
			addTag("Collidable");
		}
	}
	
	/**
	 * Adds a String tag to be associated with this object.
	 * @param tag tag to associate
	 */
	
	protected void addTag(String tag) {
		if(!tags.contains(tag)) {
			tags.add(tag);
		}
	}
	
	/**
	 * Removes a tag from this object.
	 * @param tag tag to remove
	 */
	
	protected void removeTag(String tag) {
		if(tags.contains(tag)) {
			tags.remove(tag);
		}
	}
	
	//Private methods
	
	/**
	 * Recalculates this object's center of mass.
	 */
	
	private void recalcCom() {
		int x, y;
		x = (int)(pos.x + (size.width / 2));
		y = (int)(pos.y + (size.height / 2));
		com = new Coord(x, y);
	}
	
	//Abstract methods
	
	/**
	 * Called every frame. Used to make the object do what it does.
	 */
	
	public abstract void Update();
}
