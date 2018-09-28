package engine.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Manages all GameObjects in the game.</p><p>Holds all active game objects. Updates and renders each object each frame. 
 * Provides methods of getting game objects and filtering objects based on a String tag.</p>
 * @author Taylor Houthoofd
 *
 */

public class ObjectController {
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> toAdd;
	private ArrayList<GameObject> toRemove;
	
	/**
	 * Constructor. Sets up the object controller for use.
	 */
	
	public ObjectController() {
		objects = new ArrayList<GameObject>();
		toAdd = new ArrayList<GameObject>();
		toRemove = new ArrayList<GameObject>();
	}
	
	/**
	 * Adds a GameObject to the list of active game objects. Passes the controller to the object and
	 *  adds to the list of objects to add.
	 * @param obj GameObject to add
	 */
	
	public void addObject(GameObject obj) {
		obj.addObjectController(this);
		toAdd.add(obj);
	}
	
	/**
	 * Adds a given GameObject to the list of objects to remove.
	 * @param obj GameObject to remove
	 */
	
	public void removeObject(GameObject obj) {
		toRemove.add(obj);
	}
	
	/**
	 * Gets an array of all GameObjects in the game
	 * @return Array of GameObjects
	 */
	
	public GameObject[] getGameObjects() {
		GameObject[] objs = new GameObject[objects.size()];
		for(int i = 0; i < objects.size(); i++) {
			objs[i] = objects.get(i);
		}
		return objs;
	}
	
	/**
	 * Gets an array of all GameObjects that contain a String tag
	 * @param tagFilter filter
	 * @return filtered GameObjects
	 */
	
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
	
	public void clear() {
		toAdd.clear();
		toRemove.addAll(objects);
	}
	
	/**
	 * Calls the "startUpdate" function in all active GameObjects. After all objects are updated, 
	 * removes all GameObjects queued to be removed, adds all GameObjects queued to be added.
	 */
	
	void Update() {
		
		//Update all objects
		for(GameObject obj: objects) {
			obj.startUpdate();
		}
		
		//Remove objects to be removed
		for(GameObject obj: toRemove) {
			objects.remove(obj);
		}
		toRemove.clear();
		
		//Add objects to be added
		for(GameObject obj: toAdd) {
			objects.add(obj);
		}
		toAdd.clear();
		
	}
	
	/**
	 * Calls the render method in all GameObjects. GameObjects called in layer order, with lowest layer 
	 * called before higher layers.
	 * @param g Graphics object
	 */
	
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