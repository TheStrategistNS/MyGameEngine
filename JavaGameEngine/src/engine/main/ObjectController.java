package engine.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import engine.interfaces.GameObject;

public class ObjectController {
	JavaEngine game;
	ArrayList<GameObject> objects;
	
	public ObjectController(JavaEngine game) {
		this.game = game;
		objects = new ArrayList<GameObject>();
	}
	
	public void addObject(GameObject obj) {
		obj.addObjectController(this);
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	
	public GameObject[] getGameObjects() {
		GameObject[] objs = new GameObject[objects.size()];
		for(int i = 0; i < objects.size(); i++) {
			objs[i] = objects.get(i);
		}
		return objs;
	}
	
	public GameObject[] getGameObjects(String tagFilter) {
		GameObject[] objs;
		ArrayList<GameObject> relevant = new ArrayList<GameObject>();
		for(GameObject obj: objects) {
			if(obj.containsTag(tagFilter)) {
				relevant.add(obj);
			}
		}
		objs = new GameObject[relevant.size()];
		for(int i = 0; i < relevant.size(); i++) {
			objs[i] = relevant.get(i);
		}
		return objs;
	}
	
	void Update() {
		for(GameObject obj: objects) {
			obj.Update();
		}
	}
	
	void Render(Graphics g) {
		HashMap<Integer, ArrayList<GameObject>> levels = new HashMap<Integer, ArrayList<GameObject>>();
		for(GameObject obj:objects) {
			if(levels.containsKey(obj.getDrawLevel())) {
				levels.get(obj.getDrawLevel()).add(obj);
			} else {
				levels.put(obj.getDrawLevel(), new ArrayList<GameObject>());
				levels.get(obj.getDrawLevel()).add(obj);
			}
		}
		for(int i = 0; !levels.isEmpty(); i++) {
			if(levels.containsKey(i)) {
				for(GameObject obj: levels.get(i)) {
					obj.Render(g);
				}
				levels.remove(i);
			}
		}
	}
}