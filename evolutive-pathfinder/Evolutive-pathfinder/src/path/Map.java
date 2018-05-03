package path;

import java.awt.Point;
import java.util.LinkedList;

public class Map {
	int length, width;
	Point initialPoint;
	Point finalPoint;
	LinkedList<LinkedList<Edge>> grid;
	
	public Map(int n, int m,  Point i , Point f, LinkedList<SpcZone> zones, LinkedList<Point> obstacles){
		this.length = n;
		this.width = m;
		this.initialPoint = i;
		this.finalPoint = f;
		
		fillGrid (zones, obstacles);
	}
	
	private void fillGrid (LinkedList<SpcZone> zones, LinkedList<Point> obstacles) {
		this.grid = new LinkedList<LinkedList<Edge>>();
		
		for (int i = 1 ; i <= this.length ; i++) {
			for (int j = 1 ; j <= this.width ; j++) {
				
				LinkedList<Edge> aux = new LinkedList<Edge>();
				
				if( !obstacles.contains(new Point(i , j))) {
					findEdges(i, j, obstacles, aux);
				}
				
				this.grid.add(aux);
			}
		}
		
		for (int i = 0 ; i < zones.size(); i++) {
			changeCost(zones.get(i));
		}
		
	}
	
	private void findEdges (int i, int j, LinkedList<Point> obstacles, LinkedList<Edge> aux) {
		
		// Left edge, if it exists
		if(inMap(i-1, j) && !obstacles.contains(new Point(i-1, j))) aux.addLast(new Edge(i-1, j, 1));
		
		// Right edge, if it exists
		if(inMap(i+1, j) && !obstacles.contains(new Point(i+1, j))) aux.addLast(new Edge(i+1, j, 1));
				
		// Down edge, if it exists
		if(inMap(i, j-1) && !obstacles.contains(new Point(i, j-1))) aux.addLast(new Edge(i, j-1, 1));
		
		// Upper edge, if it exists
		if(inMap(i, j+1) && !obstacles.contains(new Point(i, j+1))) aux.addLast(new Edge(i, j+1, 1));
	}
	
	private boolean inMap (int i, int j) {
		if ( i >= 1 && i <= this.length && j >= 1 && j <= this.width) return true;
		return false;
	}
	
	private void changeCost(SpcZone zone) {
		
		Point aux;
		
		for (int i = 0 ; i < 4 ; i++) {
			aux = zone.edges.get(i);
			for(int j = 0 ; j < this.grid.get(getInd(aux)).size() ; j++) {
				if (this.grid.get(getInd(aux)).get(j).coord.equals(zone.edges.get((i+1)%4))) {
					this.grid.get(getInd(aux)).get(j).cost = zone.cost;
				}
				if (this.grid.get(getInd(aux)).get(j).coord.equals(zone.edges.get((i == 0) ? 3 : i-1))) {
					this.grid.get(getInd(aux)).get(j).cost = zone.cost;
				}
			}
		}
	}
	
	private int getInd(Point p) {
		return (p.x-1)%this.length + (p.y-1)/this.width;
	}
	
	public static void main(String[] args) {
		int n = 5, m = 4;
		Point i = new Point(1,1);
		Point f = new Point(5,4);
		LinkedList<SpcZone> z = new LinkedList<SpcZone>();
		LinkedList<Point> o = new LinkedList<Point>();
		
		Map map = new Map(n, m, i, f, z, o);
		
		

	}
}

