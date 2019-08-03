package engine.main;

import java.awt.Point;

/**
 * A more precise position object than java.awt.point.
 * @author Taylor Houthoofd
 *
 */

public class Coord {
	public double x;
	public double y;

	/**
	 * Creates a new empty Coord object instantiated to (0, 0).
	 */
	
	public Coord() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Creates a new Coord object with given coords.
	 * @param x x position
	 * @param y y position
	 */
	
	public Coord(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new Coord object from a given Point.
	 * @param p point
	 */
	
	public Coord(Point p) {
		x = p.x;
		y = p.y;
	}
	
	/**
	 * Gets the x position.
	 * @return x position.
	 */
	
	public double getX() {
		return x;
	}
	
	/**
	 * Gets the y position.
	 * @return y position.
	 */
	
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the nearest Point to the given Coord.
	 * @return point
	 */
	
	public Point toPoint() {
		return new Point((int)Math.round(x), (int)Math.round(y));
	}
}
