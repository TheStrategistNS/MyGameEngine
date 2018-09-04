package engine.main;

public abstract class GameComponent {
	public static final int COLLIDER = 0;
	
	private int type;
	protected GameObject attached;
	
	public GameComponent(int type) {
		this.type = type;
	}
	
	public int type() {
		return type;
	}
	
	public void addTo(GameObject obj) {
		attached = obj;
	}
	
	public abstract void Update();
}
