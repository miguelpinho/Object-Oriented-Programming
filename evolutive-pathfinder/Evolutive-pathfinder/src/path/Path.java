package path;
import population.Specimen;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;


public class Path extends Specimen  {
	
	Map map;
	int cost;
	LinkedList<Edge> path;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Specimen Reproduce() {
		
	}
    
    public void Die() {
    	
    }
    
    public void Mutate() {
    	Random rn = new Random();
    	LinkedList<Edge> cur = this.map.grid.get(this.map.getInd(this.currentPos()));
    	
    	int nInd = rn.nextInt(cur.size());
    	
    	if ( !this.path.contains(cur.get(nInd))) {
    		this.path.add(new Edge(cur.get(nInd).coord, cur.get(nInd).cost));
    		this.cost += cur.get(nInd).cost;
    	} else {
    		// Closing loops
    		int newLast = this.path.indexOf(cur.get(nInd));
    		while( this.path.size() > newLast + 1 ) {
    			this.cost -= this.path.removeLast().cost;
    		}
    	}
    	
    }

    public double getFitness() {
    	return ((1 - (this.cost - this.length() + 2) / ((this.map.cmax-1)*this.length() + 3))^this.map.k) * ((1 - this.dist() / (this.map.length + this.map.width +1 ))^this.map.k);
    }
    
    private int length() {
    	return this.path.size();
    }
    
    private int dist() {
     	return this.map.dist(this.currentPos());
    }
    
    private Point currentPos() {
    	return this.path.getLast().coord;
    }

}
