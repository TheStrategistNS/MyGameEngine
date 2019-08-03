package engine.main;

public class Coord {
	public double x;
	public double y;

	public Coord() {
		x = 0;
		y = 0;
	}
	
	public Coord(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}