package population;

import simulation.StochasticSimulation;
import java.util.LinkedList;

public class PopulationSimulation extends StochasticSimulation {


	LinkedList<Specimen> specimens;
    int initPopulation;
    int mu = 1;
    int ro= 1;
    int delta = 1;
    //Map map;//TODO import map to this class;
    
    
    public PopulationSimulation(int initPopulation) {
        super();
        this.initPopulation = initPopulation;
        this.specimens = new LinkedList<Specimen>();
        
    }
    public PopulationSimulation(LinkedList<Specimen> specimens) {
	    super();
      
        this.specimens =  specimens;
    }
  
    /**
	 *  Create new specimen with corresponding death,reproduction and mutation events and adds it
	 *  to the population.
	 * */
    public void addSpecimen(Specimen newSpecimen) {
    	
    	double timeAux;
    	PopulationEvent pEvent = null;
    	
    	specimens.add(newSpecimen);
    	newSpecimen.setDeathTime(PopulationEvent.computeTime(mu));
    	PEC.add(new EventDeath(newSpecimen,newSpecimen.getDeathTime()));
    	
    	timeAux = pEvent.computeTime(delta);
    	if(timeAux < newSpecimen.getDeathTime()) {
    		PEC.add(new EventMutate(newSpecimen,timeAux));
    	}
    	
    	timeAux = pEvent.computeTime(ro);
    	if(timeAux < newSpecimen.getDeathTime()) {
    		PEC.add(new EventReproduce(newSpecimen,timeAux));
    	}
    	
    	//TODO the list is not sorted according to time of event!!
    }
    
    /**
     * Create initial population.
     */
    
    public void genesis() {
    	
    	Specimen newSpecimen;
    	
    	for(int i=1;i <= initPopulation; i++) {
    		
    		newSpecimen = new Specimen(map,initPoint);
    		addSpecimen(newSpecimen);
    	}
    	
    }
    
    /**
     * The population suffers an epidemic.
     */
    public void epidemic() {
    	
    }



public void main() {
	
	Specimen newSpecimen;

	}

}
