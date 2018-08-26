package engine.interfaces;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import engine.main.ObjectController;
import engine.main.SpriteGraphic;

public abstract class GameObject {
	private static final int DEFAULT_DRAW_LEVEL = 5;
	
	protected Point pos;
	private Point com;
	protected Dimension size;
	private MouseInteraction mouse;
	private ArrayList<String> tags;
	private ObjectController controller;
	private int drawLevel;
	protected SpriteGraphic graphic;
	
	//Constructors
	
	public GameObject(Point pos, Dimension size) {
		this.pos = pos;
		this.size = size;
		recalcCom();
		tags = new ArrayList<String>();
		drawLevel = DEFAULT_DRAW_LEVEL;
		graphic = new SpriteGraphic(size.width, size.height);
	}
	
	public GameObject(int x, int y, Dimension size) {
		pos = new Point(x, y);
		this.size = size;
		recalcCom();
		tags = new ArrayList<String>();
		drawLevel = DEFAULT_DRAW_LEVEL;
		graphic = new SpriteGraphic(size.width, size.height);
	}
	
	public GameObject(Point pos, int width, int height) {
		this.pos = pos;
		size = new Dimension(width, height);
		recalcCom();
		tags = new ArrayList<String>();
		drawLevel = DEFAULT_DRAW_LEVEL;
		graphic = new SpriteGraphic(size.width, size.height);
	}
	
	public GameObject(int x, int y, int width, int height) {
		pos = new Point(x, y);
		size = new Dimension(width, height);
		recalcCom();
		tags = new ArrayList<String>();
		drawLevel = DEFAULT_DRAW_LEVEL;
		graphic = new SpriteGraphic(size.width, size.height);
	}
	
	//Public methods
	
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
	
	public void addMouseInteraction(MouseInteraction mouse) {
		this.mouse = mouse;
		addTag("MouseInteract");
	}
	
	public MouseInteraction getMouseInteraction() {
		return mouse;
	}
	
	public void addTag(String tag) {
		if(!tags.contains(tag)) {
			tags.add(tag);
		}
	}
	
	public void removeTag(String tag) {
		if(tags.contains(tag)) {
			tags.remove(tag);
		}
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
	
	public boolean isTouching(GameObject other) {
		boolean touching = true;
		
		if(other.pos.getX() >= pos.getX() + size.getWidth() || other.pos.getX() + other.size.getWidth() <= pos.getX()) {
			touching = false;
		}
		if(other.pos.getY() >= pos.getY() + size.getHeight() || other.pos.getY() + other.size.getWidth() <= pos.getY()) {
			touching = false;
		}
		
		return touching;
	}
	
	public boolean isTouching(Point point) {
		boolean touching = true;
		
		if(point.getX() <= pos.getX() || point.getX() >= pos.getX() + size.getWidth()) {
			touching = false;
		}
		if(point.getY() <= pos.getY() || point.getY() >= pos.getY() + size.getHeight()) {
			touching = false;
		}
		
		return touching;
	}
	
	public void addObjectController(ObjectController controller) {
		this.controller = controller;
	}
	
	public void kill() {
		controller.removeObject(this);
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
	
	public void Render(Graphics g) {
		if(graphic.isReady()) {
			graphic.Render(g, pos.x, pos.y);
		}
		else {
			System.err.println("Trying to render object not set up. Deleteing object.");
			kill();
		}
	}
}
