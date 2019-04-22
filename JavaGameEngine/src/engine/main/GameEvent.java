package engine.main;

import java.util.HashMap;

/**
 * Basis for a basic event system, the event itself. Consists of a name, a type, and various data.
 * @author Taylor Houthoofd
 *
 */

public class GameEvent {
	public static final int CUSTOM = 0;
	public static final int MOUSE_EVENT = 1;
	public static final int ON_DESTROY = 2;
	public static final int COLLISION = 3;
	
	public int type;
	public String name;
	private HashMap<String, Object> data;
	
	/**
	 * Constructor for custom events.
	 * @param name name of event
	 */
	
	public GameEvent(String name) {
		this.type = CUSTOM;
		this.name = name;
		data = new HashMap<String, Object>();
	}
	
	/**
	 * Engine constructor for events.
	 * @param type type of event
	 */
	
	GameEvent(int type) {
		this.type = type;
		this.name = "GameEvent";
		data = new HashMap<String, Object>();
	}
	
	/**
	 * Adds a data item to this event in a key/value pair. The key is a String, and the value can be anything.
	 * @param key key for the data
	 * @param item data item to store
	 */
	
	public void put(String key, Object item) {
		data.put(key, item);
	}
	
	/**
	 * <p>Get the data item associated with a String key.</p>
	 * <p>Each event has its own data associated with it, as follows:</p>
	 * <ul>
	 * 	<li>Collision:
	 * 	<ul>
	 * 		<li>"object" - (GameObject) object colliding.</li>
	 * 		<li>"type" - (String) type of collision. Can be "onEnter" for first touch, or "touching" if it is still touching from a previous frame.</li>
	 * 	</ul>
	 * </ul>
	 * @param key key for item
	 * @return data item
	 */
	
	public Object get(String key) {
		return data.get(key);
	}
	
	/**
	 * Gets the HashMap containing the data directly. Not recommended.
	 * @return event data
	 */
	
	public HashMap<String, Object> getData() {
		return data;
	}
}
