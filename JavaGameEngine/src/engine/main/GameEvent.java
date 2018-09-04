package engine.main;

import java.util.HashMap;

public class GameEvent {
	public static final int CUSTOM = 0;
	public static final int MOUSE_EVENT = 1;
	public static final int ON_DESTROY = 2;
	public static final int COLLISION = 3;
	
	public int type;
	public String name;
	private HashMap<String, Object> data;
	
	public GameEvent(String name) {
		this.type = CUSTOM;
		this.name = name;
		data = new HashMap<String, Object>();
	}
	
	GameEvent(int type) {
		this.type = type;
		this.name = "GameEvent";
		data = new HashMap<String, Object>();
	}
	
	public void put(String key, Object item) {
		data.put(key, item);
	}
	
	public Object get(String key) {
		return data.get(key);
	}
	
	public HashMap<String, Object> getData() {
		return data;
	}
}
