package path;

import java.awt.Point;
import java.util.LinkedList;

class SpcZone {
	
	int cost;
	Point initialPoint;
	Point finalPoint;
	LinkedList<Point> edges;
	
	SpcZone(Point i, Point f, int z) {
		this.cost = z;
		this.initialPoint = i;
		this.finalPoint = f;
		
		getEdges();
	}
	
	void getEdges() {
		this.edges = new LinkedList<Point>();
		
		this.edges.add(initialPoint);
		this.edges.add(new Point(initialPoint.x, finalPoint.y));
		this.edges.add(finalPoint);
		this.edges.add(new Point(initialPoint.y, finalPoint.x));
		
	}

}
