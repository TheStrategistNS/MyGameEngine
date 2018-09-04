package engine.main;

import java.util.ArrayList;

public class Collider extends GameComponent {
	private ArrayList<GameObject> previous, current;

	public Collider() {
		super(GameComponent.COLLIDER);
		previous = new ArrayList<GameObject>();
	}
	
	public void Update() {
		GameObject[] objs = JavaEngine.instance.getObjectController().getGameObjects("Collidable");
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
