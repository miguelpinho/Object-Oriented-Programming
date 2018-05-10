package population;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Population<T extends Organism<T>> {
    
    PopulationSimulation<T> popSimul;
    
    protected int maxPop;
    
    protected LinkedList<Specimen<T>> specimens;
    protected T fittest;

    public Population(PopulationSimulation<T> popSimul, LinkedList<T> pioneers, int maxPop) {
        this.popSimul = popSimul;
        this.maxPop = maxPop;
        
        this.specimens = new LinkedList<Specimen<T>>();
                
        fittest = null;
        
        for(int i=0; i < pioneers.size(); i++) {

            try {
                
                addSpecimen(new Specimen<T>(this, pioneers.get(i)));
            } catch (ExceedsPopulation e) {
                
                epidemic();
            }
            
        }
    }
    
    /**
     *  Adds a new specimen to the population, creating the corresponding death,reproduction and mutation events 
     *  @param newSpecimen Specimen to add
     */
    public void addSpecimen(Specimen<T> newSpecimen) throws ExceedsPopulation  {
        
        specimens.add(newSpecimen);
        updateFittest(newSpecimen);
        
        popSimul.scheduleDeath(newSpecimen);
        popSimul.scheduleReproduce(newSpecimen);
        popSimul.scheduleMutation(newSpecimen);
    
        if (specimens.size() > maxPop) {
            
            throw new ExceedsPopulation();
        }
    }
    
    /**
     * The population suffers an epidemic.
     */
    public void epidemic() {
        
        int spareCounter = 0;
        
        Collections.sort(specimens, new OrderFit<T>());
        
        for (int i = 0; i < specimens.size(); i++) {
            if(specimens.get(i).isAlive()) {
                if(spareCounter < 5) {
                    
                    spareCounter++;
                } else {
                    
                    if(popSimul.randUniform() > specimens.get(i).getFitness()) {
                        specimens.get(i).markDead();
                    }
                }
            }
            
        }
        
        popSimul.updateSchedule();
        
        removeAllDead();
        
    }
    
    public void removeDead() {
        
        Specimen<T> s;
        Iterator<Specimen<T>> cur = specimens.iterator();
        
        while(cur.hasNext()) {
            
            s = (Specimen<T>) cur.next();
            
            if (!(s.isAlive())) {
                
                cur.remove();
                
                return;
            }
        }
    }
    
    public void removeAllDead() {
    
        Specimen<T> s;
        Iterator<Specimen<T>> cur = specimens.iterator();
        
        while(cur.hasNext()) {
            
            s = (Specimen<T>) cur.next();
            
            if (!(s.isAlive())) {
                
                cur.remove();
            }
        }
    }
    
    public void printState() {
        
        System.out.print("Population size: ");
        System.out.println(specimens.size());
        
    }
    
    
    /**
     * Updates fittest.
     */
    public void updateFittest(Specimen<T> entity) {
        
        fittest = entity.updateFitter(fittest);
    }
    
    public T getFittest() {
    	
    	return fittest;
    }
    
}
