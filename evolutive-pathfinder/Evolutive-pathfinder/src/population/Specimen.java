package population;

abstract public class Specimen {

    boolean alive;
    
    public Specimen() {
        
        alive = true;
    }
    
    abstract public void Reproduce();
    
    abstract public void Die();
    
    abstract public void Mutate();

    abstract public double getFitness();
    
    public boolean isAlive() {
        
        return alive;
    }
}
