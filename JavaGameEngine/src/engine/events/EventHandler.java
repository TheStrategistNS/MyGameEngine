package engine.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import engine.main.GameObject;

public class EventHandler {
	private HashMap<Integer,Class<? extends GameEvent>> eventRegistry;
	private HashMap<Integer,ArrayList<GameObject>> subscribedObjects;
	private HashMap<String,Integer> eventNames;
	private int index;
	
	private HashSet<GameEvent> pending;
	
	public EventHandler() {
		eventRegistry = new HashMap<Integer, Class<? extends GameEvent>>();
		subscribedObjects = new HashMap<Integer, ArrayList<GameObject>>();
		eventNames = new HashMap<String, Integer>();
		pending = new HashSet<GameEvent>();
		index = 0;
		
		registerEvent(new MouseEvent());
	}
	
	public <T extends GameEvent> void registerEvent(T event) {
		Integer id = index++;
		
		eventRegistry.put(id, event.getClass());
		subscribedObjects.put(id, new ArrayList<GameObject>());
		eventNames.put(event.getName(), id);
	}
	
	public boolean subscribeEvent(GameObject obj, String name) {
		Integer id = eventNames.get(name);
		if(id != null) {
			subscribedObjects.get(id).add(obj);
			return true;
		}
		return false;
	}
	
	public boolean unsubscribeEvent(GameObject obj, String name) {
		Integer id = eventNames.get(name);
		if(id != null) {
			subscribedObjects.get(id).remove(obj);
			return true;
		}
		return false;
	}
	
	public void unsubscribeAll(GameObject obj) {
		for(ArrayList<GameObject> ar:subscribedObjects.values()) {
			ar.remove(obj);
		}
	}
	
	public <T extends GameEvent> void raiseEvent(T event) {
		pending.add(event);
	}
	
	public void update() {
		Iterator<GameEvent> events = pending.iterator();
		while(events.hasNext()) {
			GameEvent e = events.next();
			Integer id = eventNames.get(e.getName());
			for(GameObject obj:subscribedObjects.get(id)) {
				if(e.filter(obj)) {
					obj.onEvent(e.getEventType(), e);
				}
			}
		}
		pending.clear();
	}
}