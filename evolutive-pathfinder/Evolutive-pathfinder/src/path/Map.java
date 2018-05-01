package path;

import java.awt.Point;
import java.util.LinkedList;

class Map {
	int length, width;
	Point initialPoint;
	Point finalPoint;
	LinkedList<LinkedList<Edge>> grid;
	
	Map(int n, int m,  Point i , Point f, LinkedList<SpcZone> zones, LinkedList<Point> obstacles){
		this.length = n;
		this.width = m;
		this.initialPoint = i;
		this.finalPoint = f;
		
		fillGrid (zones, obstacles);
	}
	
	private void fillGrid (LinkedList<SpcZone> zones, LinkedList<Point> obstacles) {
		this.grid = new LinkedList<LinkedList<Edge>>();
		
		for (int i = 0 ; i < this.length ; i++) {
			for (int j = 0 ; j < this.width ; j++) {
				
				LinkedList<Edge> aux = new LinkedList<Edge>();
				
				if( !obstacles.contains(new Point(i , j))) {
					findEdges(i, j, obstacles, aux);
				}
				
				this.grid.add(aux);
			}
		}
		
		for (int i = 0 ; i < zones.size(); i++) {
			
		}
		
	}
	
	private void findEdges (int i, int j, LinkedList<Point> obstacles, LinkedList<Edge> aux) {
		
		// Left edge, if it exists
		if(inMap(i-1, j) && !obstacles.contains(new Point(i-1, j))) aux.addLast(new Edge(i-1, j, 1));
		
		// Right edge, if it exists
		if(inMap(i+1, j) && !obstacles.contains(new Point(i+1, j))) aux.addLast(new Edge(i+1, j, 1));
				
		// Upper edge, if it exists
		if(inMap(i, j-1) && !obstacles.contains(new Point(i, j-1))) aux.addLast(new Edge(i, j-1, 1));
		
		// Down edge, if it exists
		if(inMap(i, j+1) && !obstacles.contains(new Point(i, j+1))) aux.addLast(new Edge(i, j+1, 1));
	}
	
	private boolean inMap (int i, int j) {
		if ( i >= 0 && i <= this.length && j >= 0 && j < this.width) return true;
		return false;
	}
	
	private void changeCost(SpcZone zone) {
		
	}
}
