package path;

import java.awt.Point;
import java.util.LinkedList;

/** 
 * Class that has all the information about the grid 
 */
public class Map {
	int length, width;
	Point initialPoint;
	Point finalPoint;
	int cmax;
	int k;
	
	/**
	 * Implemented grid with a list of lists of edges. 
	 * They represent the connections that each point has
	 */
	LinkedList<LinkedList<Edge>> grid;
	
	public Map(int n, int m,  int cmax, int k, Point i , Point f, LinkedList<SpcZone> zones, LinkedList<Point> obstacles){
		this.length = n;
		this.width = m;
		this.initialPoint = i;
		this.finalPoint = f;
		this.cmax = cmax;
		this.k = k;
		
		fillGrid (zones, obstacles);
	}
	
	// The grid will be implemented as a list of all the points in it.Each one of them is a list of the edges associated
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
	
	/**
	 * FInding the connections of a point in the grid. 
	 * It starts by seeing the left position and goes clockwise until the down position
	 * @param x
	 * @param y
	 * @param obstacles
	 * @param new list of connections
	 */
	private void findEdges (int i, int j, LinkedList<Point> obstacles, LinkedList<Edge> aux) {
		
		// Left edge, if it exists
		if(inMap(i-1, j) && !obstacles.contains(new Point(i-1, j))) aux.addLast(new Edge(i-1, j, 1));
		
		// Upper edge, if it exists
		if(inMap(i, j+1) && !obstacles.contains(new Point(i, j+1))) aux.addLast(new Edge(i, j+1, 1));
				
		// Right edge, if it exists
		if(inMap(i+1, j) && !obstacles.contains(new Point(i+1, j))) aux.addLast(new Edge(i+1, j, 1));
				
		// Down edge, if it exists
		if(inMap(i, j-1) && !obstacles.contains(new Point(i, j-1))) aux.addLast(new Edge(i, j-1, 1));
	}
	
	// Sees if a point is in the map
	private boolean inMap (int i, int j) {
		if ( i >= 1 && i <= this.length && j >= 1 && j <= this.width) return true;
		return false;
	}
	
	/**
	 * Goes trough every point of the special zone and changes the right costs in the grid
	 * @param zone
	 */
	private void changeCost(SpcZone zone) {
		
		Point aux;
		int nrEdges = zone.edges.size();
		
		for (int i = 0 ; i < nrEdges ; i++) {
			aux = zone.edges.get(i);
			for(int j = 0 ; j < this.grid.get(getInd(aux)).size() ; j++) {
				if (this.grid.get(getInd(aux)).get(j).coord.equals(zone.edges.get((i+1)%nrEdges))) {
					this.grid.get(getInd(aux)).get(j).cost = Math.max(zone.cost, this.grid.get(getInd(aux)).get(j).cost);
				}
				if (this.grid.get(getInd(aux)).get(j).coord.equals(zone.edges.get((i == 0) ? nrEdges-1 : i-1))) {
					this.grid.get(getInd(aux)).get(j).cost = Math.max(zone.cost, this.grid.get(getInd(aux)).get(j).cost);
				}
			}
		}
	}
	
	/**
	 * Transforms coordinates in the id of the position. The positions will be sorted eith this 
	 * number in the grid.
	 * @param p
	 * @return
	 */
	int getInd(Point p) {
		return (p.x-1)*this.width + (p.y-1)%this.width;
	}
	
	// Calculation of the dist function needed to calculate the Fitness
	int dist(Point z) {
		return Math.abs(this.finalPoint.x - z.x) + Math.abs(this.finalPoint.y - z.y);
	}
	
	
}

