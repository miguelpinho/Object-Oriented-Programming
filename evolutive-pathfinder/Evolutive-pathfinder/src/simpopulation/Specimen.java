package simpopulation;

/**
 * 
 * Class for an element of the current {@link Population}, with keeps its state and provides an api for its actions.
 * 
 * @author group16
 *
 * @param <T> generic type of an {@link Organism} implementation
 */
public class Specimen<T extends Organism<T>> {

    /** State of this population simulation */
    protected Population<T> geneBank;
    
    /** Alive flag */
    protected boolean alive;
    
    /** Time of death */
    protected double deathTime;
    
    /** This organism (generic)*/
    protected T entity;
    
    /**
     * Specimen constructor.
     * 
     * @param geneBank the population to which this specimen belongs
     * @param entity organism object (generic) that represents this specimen
     */
    public Specimen(Population<T> geneBank, T entity) {
        this.geneBank = geneBank;
        
        this.entity = entity;
        alive = true;
    }

    /**
     * Generates a new specimen from this one, following the reproduction rule supplied in the {@link Organism} type.
     */
    public void reproduce() {
        
        Specimen<T> son = new Specimen<T>(geneBank, entity.reproduce());
        
        try {
            
            geneBank.addSpecimen(son);
        } catch (ExceedsPopulation e) {
            
            geneBank.epidemic();
        }

    }
    
    /**
     * Kills this specimen and removes it from the associated population.
     */
    public void die() {
        
        alive = false;
        
        geneBank.removeDead();
    }
    
    /**
     * Marks this specimen for death, to be removed later.
     */
    public void markDead() {
    	
    	alive = false;
    }
    
    /**
     * Mutates this specimen, following the mutation rule supplied in the {@link Organism} type.
     */
    public void mutate() {
        
        entity.mutate();
        
        geneBank.updateFittest(this);
    }

    /**
     * Calculates the specimen fitness using the facility provided in the {@link Organism} type.
     * 
     * @return the fitness of this specimen
     */
    public double getFitness() {
        
        return entity.getFitness();
    }
    
    /**
     * Returns the state of this specimen.
     * 
     * @return true if alive, false if dead
     */
    public boolean isAlive() {
        
        return alive;
    }
    
    /**
     * 
     * @return
     */
    public double getDeathTime() {
        
        return deathTime;
    }
   
    public void setDeathTime(double deathTime) {
        
        this.deathTime = deathTime;
    }
    
    public T updateFitter(T fittest) {
        if (entity.isFitter(fittest)) {
            
            return entity.replicate();
        }
        
        return fittest;
    }
    
}
