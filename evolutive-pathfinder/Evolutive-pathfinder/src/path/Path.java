package path;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

/** Type of Specimen used in this problem **/
public class Path {
    
    static Map pathMap = null;
    static Path fittestPath = null;
	
	int cost;
	LinkedList<Edge> path;
	
	public static void setMap( Map m) {
	    
	    pathMap = m;
	}
	
	public static Path getFittest() {
	    
	    return fittestPath;
	}
	
	public Path( ) {
		this.cost = 0;
		this.path = new LinkedList<Edge>();
		this.path.add(new Edge(pathMap.initialPoint, 0));
		
	     // Check if this new path is the fittest
        this.updateFittest();
		
	}
	
	public Path( Path other) {
	    this.cost = other.cost;
	    this.path = new LinkedList<Edge>(other.path);
	    
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
	
	/**
	 * Function responsible for creating a new specimen, descendant from this one, with the characteristics needed
	 * @return
	 */
	public Path reproduce() {
		Path son = new Path(this);
		
		double newLength = Math.ceil(0.9 * (double) this.length() + 0.1 * (double) this.length() * this.getFitness());
		
		System.out.println(newLength);
		
		for(int i = son.path.size() - 1; i > newLength ; i--) {
			son.cost -= son.path.removeLast().cost; 
		}
		
	    // Check if this new reproduction path is the new fittest
        son.updateFittest();
		
		return son;
	}
    
	/**
	 * Function responsible for mutating this kind of Specimen - moving it
	 */
    public void mutate() {
    	Random rn = new Random();
    	LinkedList<Edge> cur = pathMap.grid.get(pathMap.getInd(this.currentPos()));
    	
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
    	
    	// Check if this mutation produced a new fittest path
        this.updateFittest();
    	
    }
   
    /**
     * Function responsible for calculating the Fitness of a Specimen
     * @return 
     */
    public double getFitness() {
    	float a = 1 -  ( (float) this.cost - (float) this.length() + 2) / (( (float) pathMap.cmax-1)* (float) this.length() + 3);
    	float b = 1 - (float) this.dist() / ( (float) pathMap.length + (float) pathMap.width +1);
    	
    	return Math.pow(a, pathMap.k) * Math.pow(b, pathMap.k);
    }
    
    /**
     * Checks if a path is a better candidate than another one. If only one of them has reached the end, it is the one chose,
     * Else, compares them based on their fitness.
     * @param path
     * @return
     */
    protected boolean isFitter(Path other) {
        if (other == null)
            return true;
        
        if (this.reachedEnd()) {
            if (!(other.reachedEnd()))
                return true;
        
        }else {
            if (other.reachedEnd()) 
                return false;
                        
        }
        
        if (this.getFitness() > other.getFitness())
            return true;
        
        return false;
    }
    
    /**
     * Updates fittest path.
     */
    protected void updateFittest() {
        if (this.isFitter(fittestPath)) {
            
            fittestPath = new Path(this);
        }
    }
    
    private int length() {
    	return this.path.size()-1;
    }
    
    private int dist() {
        
     	return pathMap.dist(this.currentPos());
    }
    
    private Point currentPos() {
        
    	return this.path.getLast().coord;
    }
    
    private boolean reachedEnd() {
        
        return currentPos().equals(pathMap.finalPoint) ;
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
		
		Path.setMap(map);
		
		Path p = new Path();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		p.mutate();
		System.out.println(p + " " + p.cost + " " +  p.getFitness());
		Path s = (Path) p.reproduce();
		System.out.println(s + " " + s.cost + " " + s.getFitness());
		
		
		
	}
}
