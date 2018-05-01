package path;

import java.awt.Point;

class Edge {
	
	Point coord;
	int cost;
	
	public Edge(int x, int y, int c) {
		this.coord = new Point(x, y);
		this.cost = c;
	}

}
