package population;

import simulation.StochasticSimulation;
import java.util.LinkedList;

import path.Path;

public class PopulationSimulation extends StochasticSimulation {

	LinkedList<Specimen> specimens;
	
    int initPopulation;
    double mu = 1;
    double ro= 1;
    double delta = 1;
    
    public PopulationSimulation(int initPopulation) {
        super();
        
        this.initPopulation = initPopulation;
        this.specimens = new LinkedList<Specimen>();
        
    }
  
    /**
	 *  Adds a new specimen to the population, creating the corresponding death,reproduction and mutation events 
	 *  @param newSpecimen Specimen to add
	 */
    public void addSpecimen(Specimen newSpecimen) {
    	
    	double timeAux;
    	
    	specimens.add(newSpecimen);
    	
    	newSpecimen.setDeathTime(randomTime(newSpecimen, mu));
    	PEC.add(new EventDeath(newSpecimen, newSpecimen.getDeathTime()));
    	
    	timeAux = randomTime(newSpecimen, delta);
    	if(timeAux < newSpecimen.getDeathTime()) {
    		PEC.add(new EventMutate(newSpecimen, timeAux));
    	}
    	
    	timeAux = randomTime(newSpecimen, ro);
    	if(timeAux < newSpecimen.getDeathTime()) {
    		PEC.add(new EventReproduce(newSpecimen,timeAux));
    	}
    	
    	//TODO the list is not sorted according to time of event!!
    	// It is, by default! The event list is sorted automatically
    }
    
    /**
     * Create initial population.
     */
    public void genesis() {
    	
    	for(int i=1;i <= initPopulation; i++) {

    		addSpecimen(new Specimen());
    	}
    	
    }
    
    /**
     * The population suffers an epidemic.
     */
    public void epidemic() {
    	
    }
    
    /**
     * 
     * @param agent
     * @param param
     * @return
     */
    protected double randomTime(Specimen agent, double param) {
        
        return (1 - Math.log10(agent.getFitness()) * param);  
    }


    public void main() {
	
        Specimen newSpecimen;

	}

}
