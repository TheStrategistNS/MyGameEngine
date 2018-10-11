package engine.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

//TODO: Implement zoom feature

public class Camera {
	private int x, y;
	private int w, h;
	private double scale;
	private JavaEngine game;

	public Camera() {
		x = 0;
		y = 0;
		game = JavaEngine.instance;
		w = game.getGameWidth();
		h = game.getGameHeight();
		scale = 1;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void incPos(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void setScale(double s) {
		if(s > 0) {	
			
			scale = s;
		}
	}
	
	public void resetPos() {
		scale = 1;
		x = 0;
		y = 0;
	}
	
	void render(Graphics g) {
		GameObject[] objs = game.getObjectController().getGameObjects();
		HashMap<Integer, ArrayList<GameObject>> levels = new HashMap<Integer, ArrayList<GameObject>>();
		for(GameObject obj:objs) {
			if(isOnScreen(obj)) {
				if(levels.containsKey(obj.getDrawLevel())) {
					levels.get(obj.getDrawLevel()).add(obj);
				} else {
					levels.put(obj.getDrawLevel(), new ArrayList<GameObject>());
					levels.get(obj.getDrawLevel()).add(obj);
				}
			}
		}
		for(int i = 0; !levels.isEmpty(); i++) {
			if(levels.containsKey(i)) {
				for(GameObject obj: levels.get(i)) {
					obj.graphic.Render(g, obj.pos.x - x, obj.pos.y - y);
				}
				levels.remove(i);
			}
		}
	}
	
	public boolean isOnScreen(GameObject obj) {
		if((obj.pos.x + obj.size.width > x) && obj.pos.x < x + w) {
			if(obj.pos.y + obj.size.height > y && obj.pos.y < y + h) {
				return true;
			}
		}
		return false;
	}
}
