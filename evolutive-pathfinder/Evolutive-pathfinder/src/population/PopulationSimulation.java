package population;

import simulation.StochasticSimulation;
import java.util.LinkedList;

public class PopulationSimulation extends StochasticSimulation {

	LinkedList<Specimen> specimens;
    int initPopulation;
    double mu = 1;
    double ro= 1;
    double delta = 1;
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
