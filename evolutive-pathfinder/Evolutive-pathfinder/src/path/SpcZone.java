package path;

import java.awt.Point;
import java.util.LinkedList;

/**
 * 
 * A special zone represented in a class. Used to keep all the info about the zones in the input
 *
 */
public class SpcZone {
	
	int cost;
	Point initialPoint;
	Point finalPoint;
	
	/**
	 * All the edges that will be part of the zone with the bigger cost
	 */
	LinkedList<Point> edges;
	
	public SpcZone(Point i, Point f, int z) {
		this.cost = z;
		this.initialPoint = i;
		this.finalPoint = f;
		
		getEdges();
	}
	
	/**
	 * Function responsible for getting all the edges that will be part of a zone.
	 * It goes from the initial point to the final point and vice versa in order to save all the 
	 * edges
	 */
	void getEdges() {
		this.edges = new LinkedList<Point>();
		
		int dirx1 = finalPoint.x - initialPoint.x;
		int dirx2 = initialPoint.x - finalPoint.x;
		int diry1 = finalPoint.y - initialPoint.y;
		int diry2 = initialPoint.y - finalPoint.y;
		
		
		Point current = new Point(initialPoint.x, initialPoint.y);
		
		for (int i = dirx1/Math.abs(dirx1) ; Math.abs(i) <= Math.abs(dirx1) ; i += dirx1/Math.abs(dirx1)) {
			current.x += dirx1/Math.abs(dirx1);
			this.edges.add(new Point(current.x, current.y));
		}
		for (int i = diry1/Math.abs(diry1) ; Math.abs(i) <= Math.abs(diry1) ; i += diry1/Math.abs(diry1)) {
			current.y += diry1/Math.abs(diry1);
			this.edges.add(new Point(current.x, current.y));
		}
		for (int i = dirx2/Math.abs(dirx2) ; Math.abs(i) <= Math.abs(dirx2) ; i += dirx2/Math.abs(dirx2)) {
			current.x += dirx2/Math.abs(dirx2);
			this.edges.add(new Point(current.x, current.y));
		}
		for (int i = diry2/Math.abs(diry2) ; Math.abs(i) <= Math.abs(diry2) ; i += diry2/Math.abs(diry2)) {
			current.y += diry2/Math.abs(diry2);
			this.edges.add(new Point(current.x, current.y));
		}
		
	}
	
	/**
	 * Change the cost of a zone
	 * @param c new cost
	 */
	public void setCost(int c) {
		this.cost = c;
	}

}
