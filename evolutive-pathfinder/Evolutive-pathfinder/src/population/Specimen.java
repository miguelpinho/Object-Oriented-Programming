package population;

public class Specimen<T extends Organism<T>> {

    protected Population<T> geneBank;
    
    protected boolean alive;
    protected double deathTime;
    protected T entity;
    
    public Specimen(Population<T> geneBank, T entity) {
        this.geneBank = geneBank;
        
        this.entity = entity;
        alive = true;
    }

    public void reproduce() {
        
        Specimen<T> son = new Specimen<T>(geneBank, entity.reproduce());
        
        try {
            
            geneBank.addSpecimen(son);
        } catch (ExceedsPopulation e) {
            
            geneBank.epidemic();
        }

    }
    
    public void die() {
        
        alive = false;
        
        geneBank.removeDead();
    }
    
    public void mutate() {
        
        entity.mutate();
        
        geneBank.updateFittest(this);
    }

    public double getFitness() {
        
        return entity.getFitness();
    }
    
    public boolean isAlive() {
        
        return alive;
    }
    
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
