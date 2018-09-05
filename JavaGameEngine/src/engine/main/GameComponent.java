package engine.main;

/**
 * Game component base class. Components are objects to be attached to a GameObject to extend functionality, 
 * an example being a collider to detect collisions between GameObjects.
 * @author Taylor Houthoofd
 *
 */

public abstract class GameComponent {
	//Add component types here as they are created!!!
	public static final int COLLIDER = 0;
	
	private int type;
	protected GameObject attached;
	
	/**
	 * Constructor. Component type should be a named constant in the GameComponent class. 
	 * If it is not, someone needs to add it.
	 * @param type type of component being created
	 */
	
	protected GameComponent(int type) {
		this.type = type;
	}
	
	/**
	 * Gets the component type. Should check against the named constants for the GameComponent class.
	 * @return type of component
	 */
	
	public int type() {
		return type;
	}
	
	/**
	 * Adds a way to call the attached GameObject.
	 * @param obj GameObject to attach to
	 */
	
	public void addTo(GameObject obj) {
		attached = obj;
	}
	
	/**
	 * Called every frame, before the attached GameObject.
	 */
	
	public abstract void Update();
}
