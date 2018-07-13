package path;

import java.awt.Point;

/**
 * 
 * Representation of an edge of the Map and the Path. It has the final position and the cost of connection.
 *
 */
class Edge {
	
	Point coord;
	int cost;
	
	@Override
	public String toString() {
		return "(" + this.coord.x + "," + this.coord.y + ")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coord.x + coord.y;
		return result;
	}
	
	/**
	 * In order to compare 2 edges it is just needed to compare the point that is connected and not the cost 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Edge))
			return false;
		Edge other = (Edge) obj;
		if (coord.x != other.coord.x || coord.y != other.coord.y)
			return false;
		return true;
	}
	
	public Edge(int x, int y, int c) {
		this.coord = new Point(x, y);
		this.cost = c;
	}
	
	public Edge(Point p, int c) {
		this.coord = p;
		this.cost = c;
	}

}
