package population;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import path.Path;

public class Population {
    
    PopulationSimulation popSimul;
    
    protected int maxPop;
    
    LinkedList<Specimen> specimens;
    protected Path fittest;

    public Population(LinkedList<Path> pioneers, int maxPop) {
        this.specimens = new LinkedList<Specimen>();
        
        fittest = null;
        
        for(int i=0; i < pioneers.size(); i++) {

            try {
                
                addSpecimen(new Specimen(pioneers.get(i)));
            } catch (ExceedsPopulation e) {
                
                epidemic();
            }
            
        }
    }
    
    /**
     *  Adds a new specimen to the population, creating the corresponding death,reproduction and mutation events 
     *  @param newSpecimen Specimen to add
     */
    public void addSpecimen(Specimen newSpecimen) throws ExceedsPopulation  {
        
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
        
        Collections.sort(specimens, new OrderFit());
        
        for (int i = 0; i < specimens.size(); i++) {
            if(specimens.get(i).isAlive()) {
                if(spareCounter < 5) {
                    
                    spareCounter++;
                } else {
                    
                    if(randGen.nextDouble() > specimens.get(i).getFitness()) {
                        specimens.get(i).die();
                    }
                }
            }
            
        }
        
        popSimul.updateSchedule();
        
        removeAllDead();
        
    }
    
    public void removeDead() {
        
        Specimen s;
        Iterator<Specimen> cur = specimens.iterator();
        
        while(cur.hasNext()) {
            
            s = (Specimen) cur.next();
            
            if (!(s.isAlive())) {
                
                cur.remove();
                
                return;
            }
        }
    }
    
    public void removeAllDead() {
    
        Specimen s;
        Iterator<Specimen> cur = specimens.iterator();
        
        while(cur.hasNext()) {
            
            s = (Specimen) cur.next();
            
            if (!(s.isAlive())) {
                
                cur.remove();
            }
        }
    }
    
    public void printState() {
        
        System.out.print("Population size: ");
        System.out.println(specimens.size());
        
        if (fittest != null) {
            fittest.printState();
        }
    }
    
    /**
     * Updates fittest path.
     */
    public void updateFittest(Specimen entity) {
        
        fittest = entity.updateFitter(fittest);
    }
    
}
