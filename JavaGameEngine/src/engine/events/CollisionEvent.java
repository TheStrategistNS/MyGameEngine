package engine.events;

import engine.main.GameObject;

public class CollisionEvent extends GameEvent {
	public static final int ON_ENTER = 0, TOUCHING = 1, ON_EXIT = 2;
	
	private final int t;
	private final GameObject[] objects;
	private GameObject obj;

	CollisionEvent() {
		super(GameEvent.COLLISION);
		t = -1;
		objects = new GameObject[0];
		
		obj = null;
	}
	
	public CollisionEvent(int type, GameObject obj1, GameObject obj2) {
		super(GameEvent.COLLISION);
		t = type;
		objects = new GameObject[2];
		objects[0] = obj1;
		objects[1] = obj2;
		
		obj = null;
	}
	
	public int getType() {
		return t;
	}
	
	public GameObject[] getObjects() {
		return objects;
	}
	
	public GameObject getObject() {
		return obj;
	}
	
	@Override
	public boolean filter(GameObject obj) {
		if(obj.equals(objects[0])) {
			this.obj = objects[1];
			return true;
		}
		else if(obj.equals(objects[1])){
			this.obj = objects[0];
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass() == getClass()) {
			CollisionEvent other = (CollisionEvent)o;
			
			if(other.t == t) {
				if(objects[0] == other.objects[0] && objects[1] == other.objects[1]) {
					return true;
				}
				else if(objects[0] == other.objects[1] && objects[1] == other.objects[0]) {
					return true;
				}
			}
		}
		return false;
	}
}
