package population;

import java.util.LinkedList;

abstract public class Population {
    
    LinkedList<Specimen> specimens;
    
    public Population() {
        
        this.specimens = new LinkedList<Specimen>();
    }
    
    public Population(LinkedList<Specimen> specimens) {
        
        this.specimens =  specimens;
    }
    
    abstract public void addSpecimen(Specimen newSpecimen);
    
    /**
     * Create initial population.
     */
    abstract public void creation();
    
    /**
     * The population suffers an epidemic.
     */
    abstract public void epidemic();

}
