package population;

import path.Path;

public class Specimen {

    protected boolean alive;
    protected double deathTime;
    protected Path entity;
    
    public Specimen() {
        
        this.entity = new Path();
        
        alive = true;
    }
    
    public Specimen(Path entity) {
        
        this.entity = entity;
        
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
    
    public static void printState() {
        
        Path.printState();
    }
    
}
