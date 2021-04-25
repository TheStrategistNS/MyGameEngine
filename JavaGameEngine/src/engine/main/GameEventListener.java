package engine.main;

/**
 * Interface that allows for a GameObject to act on GameEvents.
 * @author Taylor Houthoofd
 *
 */

public interface GameEventListener {
	
	/**
	 * Called when a GameEvent is triggered for the attached GameObject.
	 * @param sender GameObject triggering the event
	 * @param e the event
	 */
	
	public void onEvent(GameObject sender, GameEvent e);
	
}
