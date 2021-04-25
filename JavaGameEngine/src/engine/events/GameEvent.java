package engine.events;

import engine.main.GameObject;

/**
 * Basis for a basic event system, the event itself. Consists of a name and a type.
 * @author Taylor Houthoofd
 *
 */

public abstract class GameEvent {
	public static final int CUSTOM = 0;
	public static final int MOUSE_EVENT = 1;
	public static final int COLLISION = 2;
	
	private int eventType;
	private String name;
	
	public GameEvent(String name) {
		eventType = CUSTOM;
		this.name = name;
	}
	
	GameEvent(int type){
		eventType = type;
		switch(type) {
		case MOUSE_EVENT:
			name = "mouse";
			break;
		case COLLISION:
			name = "collision";
			break;
		}
	}
	
	public String getName() {
		return name;
	}
	
	int getEventType() {
		return eventType;
	}
	
	public boolean filter(GameObject obj) {
		return true;
	}
}
