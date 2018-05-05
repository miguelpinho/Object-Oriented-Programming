package population;

import simulation.StochasticSimulation;
import java.util.LinkedList;

public class PopulationSimulation extends StochasticSimulation {

    LinkedList<Specimen> specimens;
     
    public PopulationSimulation() {
        super();
    
        this.specimens = new LinkedList<Specimen>();
        
    }
    public PopulationSimulation(LinkedList<Specimen> specimens) {
	    super();
      
        //this.specimens =  specimens;
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


