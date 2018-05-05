package population;

import simulation.StochasticSimulation;
import java.util.LinkedList;

public class PopulationSimulation extends StochasticSimulation {

    LinkedList<Specimen> specimens;
    int initPopulation;
    //int mu = mu;
    //int ro= ro;
    //int delta = delta;
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
    	
    	specimens.add(newSpecimen);
    	newSpecimen.setDeathTime(PopulationEvent.computeTime(mu));
    	PEC.add(new EventDeath(new_specimen,newSpecimen.getDeathTime()));
    	
    	timeAux = PopulationEvent.computeTime(delta);
    	if(timeAux < newSpecimen.getDeathTime()) {
    		PEC.add(new EventMove(new_specimen,timeAux));
    	}
    	
    	timeAux = PopulationEvent.computeTime(ro);
    	if(timeAux < newSpecimen.getDeathTime()) {
    		PEC.add(new EventReproduce(new_specimen,timeAux));
    	}
    	
    	//TODO the list is not sorted according to time of event!!
    }
    
    /**
     * Create initial population.
     */
    
    public void genesis() {
    	
    	Specimen newSpecimen;
    	
    	for(int i=1;i <= initPopulation; i++) {
    		
    		newSpecimen = new Specimen();
    		addSpecimen(newSpecimen);
    	}
    	
    }
    
    /**
     * The population suffers an epidemic.
     */
    public void epidemic() {
    	
    }

}


