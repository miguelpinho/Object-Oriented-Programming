package population;

abstract public class Specimen {

    boolean alive;
    double deathTime;
    
    public Specimen() {
        
        alive = true;
    }
    
    abstract public Specimen Reproduce() throws CloneNotSupportedException;
    
    abstract public void Die();
    
    abstract public void Mutate();

    abstract public double getFitness();
    
    public boolean isAlive() {
        
        return alive;
    }
    
    public double getDeathTime() {
        
        return deathTime;
    }
   
    public void setDeathTime(double deathTime) {
        
        this.deathTime = deathTime;
    }
    
    public static double computeTime(int cnt) {
    	
    	
    	return (1 - Math.log10(getFitness())*cnt);//static problem
    	
    }
    
    
}
