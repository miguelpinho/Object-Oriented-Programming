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
		
		for (int c2 = 0 ; c2 < this.edges.size(); c2++) {
			System.out.println(this.edges.get(c2).x + "  " + this.edges.get(c2).y);
		}
		
	}

}
