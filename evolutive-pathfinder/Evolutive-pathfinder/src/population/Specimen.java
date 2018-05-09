package population;

import path.Path;

public class Specimen {

    protected Population popul;
    
    protected boolean alive;
    protected double deathTime;
    protected Path entity;
    
    public Specimen(Path entity) {
        
        this.entity = entity;
        
        alive = true;
    }

    public void reproduce() {
        
        Specimen son = new Specimen(entity.reproduce());
        
        try {
            
            popul.addSpecimen(son);
        } catch (ExceedsPopulation e) {
            
            popul.epidemic();
        }

    }
    
    public void die() {
        
        alive = false;
    }
    
    public void mutate() {
        
        entity.mutate();
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
