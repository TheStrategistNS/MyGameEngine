package engine.events;

import java.awt.Point;

import engine.main.GameObject;

public class MouseEvent extends GameEvent {
	public static final int MOUSE_PRESSED = 0, MOUSE_RELEASED = 1, MOUSE_CLICK = 2;
	
	private final int mouse;
	private final Point p;
	
	MouseEvent(){
		super(GameEvent.MOUSE_EVENT);
		p = new Point(0,0);
		mouse = -1;
	}
	
	public MouseEvent(int mouse, Point p) {
		super(GameEvent.MOUSE_EVENT);
		this.mouse = mouse;
		this.p = p;
	}
	
	public int getType() {
		return mouse;
	}
	
	public int getX() {
		return p.x;
	}
	
	public int getY() {
		return p.y;
	}
	
	@Override
	public boolean filter(GameObject obj) {
		if(obj.isTouching(p)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass() == this.getClass()) {
			MouseEvent other = (MouseEvent)o;
			if(other.mouse == mouse && other.p.equals(p)) {
				return true;
			}
		}
		return false;
	}
}
