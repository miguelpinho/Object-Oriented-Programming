package simpopulation;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Generic class, that models the state of an living organism population and gives an interface for
 * addition and removal of specimens. Also keeps track of the fittest organism in memory.
 * 
 * @author group16
 *
 * @param <T> generic type that implements {@link Organism}
 */
public class Population<T extends Organism<T>> {
    
    protected PopulationSimulation<T> popSimul;
    
    protected int maxPop;
    
    protected LinkedList<Specimen<T>> specimens;
    protected T fittest;
    
    private static final int SPARE_TOTAL = 5;
    
    /**
     * @param popSimul Simulation with it interacts
     * @param maxPop max number of individuals allowed in the population
     * @param pioneers List of original individuals belonging to the population
     */
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
     *  Throws exception if the maximum population size is exceeded
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
                if(spareCounter < SPARE_TOTAL) {
                    
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
    /**
     * Removes the first dead specimen found from the specimens list
     */
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
    /**
     * Removes all dead specimens from the specimens list
     */
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
    /**
     * Prints current population state
     */
    public void printState() {
        
        System.out.print("Population size: ");
        System.out.println(specimens.size());
        
    }
    
    
    /**
     * Updates fittest specimen
     */
    public void updateFittest(Specimen<T> entity) {
        
        fittest = entity.updateFitter(fittest);
    }
    /**
 	 * Finds fittest specimen
     * @return fittest specimen, null otherwise
     */
    public T getFittest() {
    	
    	return fittest;
    }
    
}
