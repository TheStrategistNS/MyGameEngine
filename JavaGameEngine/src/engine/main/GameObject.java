package engine.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class GameObject {
	private static final int DEFAULT_DRAW_LEVEL = 5;
	
	protected Point pos;
	private Point com;
	protected Dimension size;
	private ObjectController controller;
	private int drawLevel;
	protected SpriteGraphic graphic;
	
	private GameEventListener listener;
	private ArrayList<String> tags;
	private ArrayList<GameComponent> components;
	
	//Constructors
	
	public GameObject(Point pos, Dimension size) {
		this.pos = pos;
		this.size = size;
		intantiation();
	}
	
	public GameObject(int x, int y, Dimension size) {
		pos = new Point(x, y);
		this.size = size;
		intantiation();
	}
	
	public GameObject(Point pos, int width, int height) {
		this.pos = pos;
		size = new Dimension(width, height);
		intantiation();
	}
	
	public GameObject(int x, int y, int width, int height) {
		pos = new Point(x, y);
		size = new Dimension(width, height);
		intantiation();
	}
	
	private void intantiation() {
		recalcCom();
		tags = new ArrayList<String>();
		components = new ArrayList<GameComponent>();
		drawLevel = DEFAULT_DRAW_LEVEL;
		graphic = new SpriteGraphic(size.width, size.height);
	}
	
	//Public methods
	
	void startUpdate() {
		for(GameComponent c: components) {
			c.Update();
		}
		Update();
	}
	
	public int getDrawLevel() {
		return drawLevel;
	}
	
	public void setDrawLevel(int level) {
		if(level >= 0) {
			drawLevel = level;
		}else {
			drawLevel = 0;
		}
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void setPos(Point newPos) {
		pos = newPos;
		recalcCom();
	}
	
	public void setPos(int x, int y) {
		pos = new Point(x, y);
		recalcCom();
	}
	
	public void incPos(int x, int y) {
		pos.x += x;
		pos.y += y;
		recalcCom();
	}
	
	public Point getCom() {
		return com;
	}
	
	public String[] getTags() {
		String[] tags = new String[this.tags.size()];
		for(int i = 0; i < this.tags.size(); i++) {
			tags[i] = this.tags.get(i);
		}
		return tags;
	}
	
	public boolean containsTag(String tag) {
		if(tags.contains(tag)) {
			return true;
		}
		return false;
	}
	
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
	
	void addObjectController(ObjectController controller) {
		this.controller = controller;
	}
	
	public void kill() {
		GameEvent e = new GameEvent(GameEvent.ON_DESTROY);
		triggerEvent(this, e);
		controller.removeObject(this);
	}
	
	public void Render(Graphics g) {
		if(graphic.isReady()) {
			graphic.Render(g, pos.x, pos.y);
		}
		else {
			System.err.println("Trying to render object not set up. Deleteing object.");
			kill();
		}
	}
	
	public void triggerEvent(GameObject sender, GameEvent e) {
		if(listener != null) {
			listener.onEvent(sender, e);
		}
	}
	
	public void setClickable(boolean clickable) {
		if(clickable) {
			addTag("MouseInteract");
		}else{
			removeTag("MouseInteract");
		}
	}
	
	//Protected methods
	
	protected void addGameEventListener(GameEventListener listener) {
		this.listener = listener;
	}
	
	protected void addComponent(GameComponent c) {
		c.addTo(this);
		components.add(c);
		
		if(c.type() == GameComponent.COLLIDER) {
			addTag("Collidable");
		}
	}
	
	protected void addTag(String tag) {
		if(!tags.contains(tag)) {
			tags.add(tag);
		}
	}
	
	protected void removeTag(String tag) {
		if(tags.contains(tag)) {
			tags.remove(tag);
		}
	}
	
	//Private methods
	
	private void recalcCom() {
		int x, y;
		x = pos.x + (size.width / 2);
		y = pos.y + (size.height / 2);
		com = new Point(x, y);
	}
	
	//Abstract methods
	
	public abstract void Update();
}
