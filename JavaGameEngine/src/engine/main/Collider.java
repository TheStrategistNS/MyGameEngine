package engine.main;

import java.util.ArrayList;

/**
 * A collider component for a GameObject. Every frame, checks to see whether any other colliders intersect 
 * with this one. Creates a collision event if so.
 * @author Taylor Houthoofd
 *
 */

public class Collider extends GameComponent {
	private ArrayList<GameObject> previous, current;

	/**
	 * Constructor.
	 */
	
	public Collider() {
		super(GameComponent.COLLIDER);
		previous = new ArrayList<GameObject>();
	}
	
	/**
	 * Checks whether this collider is touching any other colliders.
	 */
	
	public void Update() {
		GameObject[] objs = JavaEngine.getObjectController().getGameObjects("Collidable");
		current = new ArrayList<GameObject>();
		//if(JavaEngine.instance.getFrame() % 180 == 0) {
		//	System.out.println(objs.length+" objects with collider");
		//}
		for(GameObject obj: objs) {
			if(obj != attached) {				
				if(checkTouching(obj)) {
					current.add(obj);
					GameEvent e = new GameEvent(GameEvent.COLLISION);
					e.put("object", obj);
					if(previous.contains(obj)) {
						e.put("type", "touching");
						previous.remove(obj);
					}
					else {
						e.put("type", "onEnter");
					}
					attached.triggerEvent(attached, e);
				}
			}
		}
		for(GameObject obj: previous) {
			GameEvent e = new GameEvent(GameEvent.COLLISION);
			e.put("object", obj);
			e.put("type", "onExit");
			attached.triggerEvent(attached, e);
		}
		previous = current;
	}
	
	/**
	 * Checks whether the bounds of an object intersect with the attached object.
	 * @param obj object to check
	 * @return true if touching
	 */
	
	private boolean checkTouching(GameObject obj) {
		boolean touching = true;
		
		if(obj.pos.x >= attached.pos.x + attached.size.width) {
			touching = false;
		}
		else if(obj.pos.x + obj.size.width <= attached.pos.x) {
			touching = false;
		}
		else if(obj.pos.y >= attached.pos.y + attached.size.height) {
			touching = false;
		}
		else if(obj.pos.y + obj.size.height <= attached.pos.y) {
			touching = false;
		}
		
		return touching;
	}
}
