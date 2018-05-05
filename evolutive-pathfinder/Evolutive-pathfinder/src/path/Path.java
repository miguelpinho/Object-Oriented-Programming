package path;
import population.Specimen;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

/** Type of Specimen used in this problem **/
public class Path extends Specimen implements Cloneable {
	
	Map map;
	int cost;
	LinkedList<Edge> path;
	
	public Path( Map m, Point i) {
		this.map = m;
		this.cost = 0;
		this.path = new LinkedList<Edge>();
		this.path.add(new Edge(i, 0));
		
	}
	
	@Override
	public String toString() {
		String toPrint = "{";
		for (int i = 0 ; i < this.path.size() ; i++) {
			toPrint += this.path.get(i);
		}
		toPrint += "}";
		return toPrint;
	}
	
	@Override
	public Path clone() throws CloneNotSupportedException {
	    return (Path)super.clone();
	}
	
	/** Function responsible for creating a new specimen, descendent from this one, with the characteristics needed **/
	public Specimen Reproduce() throws CloneNotSupportedException {
		Path son = (Path) this.clone();
		
		double newLength = Math.ceil(0.9 * (double) this.length() + 0.1 * (double) this.length() * this.getFitness());
		
		System.out.println(newLength);
		
		for(int i = son.path.size() - 1; i > newLength ; i--) {
			son.cost -= son.path.removeLast().cost; 
		}
		
		return son;
	}
    
	/** Function responsible for killing a Specimen **/
    public void Die() {
    	this.alive = false;
    }
    
    /** Function responsible for mutating this kind of Specimen - moving it **/
    public void Mutate() {
    	Random rn = new Random();
    	LinkedList<Edge> cur = this.map.grid.get(this.map.getInd(this.currentPos()));
    	
    	int nInd = rn.nextInt(cur.size());
    	
    	if ( !this.path.contains(cur.get(nInd))) {
    		this.path.add(cur.get(nInd));
    		this.cost += cur.get(nInd).cost;
    	} else {
    		// Avoiding loops
    		int newLast = this.path.indexOf(cur.get(nInd));
    		while( this.path.size() > newLast + 1 ) {
    			this.cost -= this.path.removeLast().cost;
    		}
    	}
    	
    }
    
    /** Function responsible for calculating the Fitness of a Specimen **/
    public double getFitness() {
    	float a = 1 -  ( (float) this.cost - (float) this.length() + 2) / (( (float) this.map.cmax-1)* (float) this.length() + 3);
    	float b = 1 - (float) this.dist() / ( (float) this.map.length + (float) this.map.width +1);
    	
    	return Math.pow(a, this.map.k) * Math.pow(b, this.map.k);
    }
    
    private int length() {
    	return this.path.size()-1;
    }
    
    private int dist() {
     	return this.map.dist(this.currentPos());
    }
    
    private Point currentPos() {
    	return this.path.getLast().coord;
    }
    
    public static void main(String[] args) throws CloneNotSupportedException {
		int n = 5, m = 4;
		Point i = new Point(1,1);
		Point f = new Point(5,4);
		LinkedList<SpcZone> z = new LinkedList<SpcZone>();
		LinkedList<Point> o = new LinkedList<Point>();
		
		z.add(new SpcZone(new Point(1, 1), new Point(5,4), 4));
		o.add(new Point(2, 3));
		
		
		Map map = new Map(n, m, 4, 1, i, f, z, o);
		
		for (int c1 = 0 ; c1 < map.grid.size(); c1++) {
			LinkedList<Edge> aux = map.grid.get(c1);
			for (int c2 = 0 ; c2 < aux.size(); c2++) {
				Edge aux2 = aux.get(c2);
				System.out.println(aux2.coord + "  " + aux2.cost + "  " + c2 + "  " + c1);
			}
		}
		
		Path p = new Path(map, i);
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.Mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		Path s = (Path) p.Reproduce();
		System.out.println(s + " " + s.cost + " " + s.getFitness());
		
		
		
	}
}
