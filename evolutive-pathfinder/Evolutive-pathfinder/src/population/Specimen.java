package population;

import path.Path;

public class Specimen {

    protected Population geneBank;
    
    protected boolean alive;
    protected double deathTime;
    protected Path entity;
    
    public Specimen(Population geneBank, Path entity) {
        this.geneBank = geneBank;
        
        this.entity = entity;
        alive = true;
    }

    public void reproduce() {
        
        Specimen son = new Specimen(geneBank, entity.reproduce());
        
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
    
    public Path updateFitter(Path fittestPath) {
        if (entity.isFitter(fittestPath)) {
            
            return new Path(entity);
        }
        
        return fittestPath;
    }
    
}
