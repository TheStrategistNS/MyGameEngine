package engine.main;

import java.util.ArrayList;

import engine.events.CollisionEvent;

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
					int type = -1;
					if(previous.contains(obj)) {
						type = CollisionEvent.TOUCHING;
						previous.remove(obj);
					}
					else {
						type = CollisionEvent.ON_ENTER;
					}
					JavaEngine.getEventHandler().raiseEvent(new CollisionEvent(type,obj,attached));
				}
			}
		}
		for(GameObject obj: previous) {
			JavaEngine.getEventHandler().raiseEvent(new CollisionEvent(CollisionEvent.ON_EXIT, obj, attached));
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
