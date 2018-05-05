package path;
import population.Specimen;

import java.awt.Point;
import java.util.LinkedList;

public class Path extends Specimen  {
	
	Map map;
	int cost;
	LinkedList<Edge> path;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void Reproduce() {
		
	}
    
    public void Die() {
    	
    }
    
    public void Mutate() {
    	
    }

    public double getFitness() {
    	return ((1 - (this.cost - this.length() + 2) / ((this.map.cmax-1)*this.length() + 3))^this.map.k) * ((1 - this.dist() / (this.map.length + this.map.width +1 ))^this.map.k);
    }
    
    private int length() {
    	return this.path.size();
    }
    
    private int dist() {
    	Point cur = this.path.getLast().coord;
    	return this.map.dist(cur);
    }

}
