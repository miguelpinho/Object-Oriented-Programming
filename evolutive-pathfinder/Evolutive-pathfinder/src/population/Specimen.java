package population;

import path.Path;

public class Specimen {

    boolean alive;
    double deathTime;
    Path entity;
    
    public Specimen(Path entity) {
        
        this.entity = entity;
        
        alive = true;
    }
    
    public Specimen() {
        
        this.entity = new Path();
        
        alive = true;
    }

    public Specimen reproduce() {
        
        return new Specimen(entity.reproduce());
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
    
}
