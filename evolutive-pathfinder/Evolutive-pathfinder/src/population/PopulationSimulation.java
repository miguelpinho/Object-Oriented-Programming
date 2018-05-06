package population;

import simulation.StochasticSimulation;
import java.util.LinkedList;

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
        
        
        specimens.add(newSpecimen);
        
        addDeath(newSpecimen);
        
        addReproduce(newSpecimen);
        
        addMutation(newSpecimen);
    
    }
    
    
    public void addReproduce(Specimen agent) {
        
        double timeAux;
        
        timeAux = randomTime(agent, delta);
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new EventMutate(agent, timeAux, this));
        }   
        
    }
    
    public void addMutation(Specimen agent) {
        
        double timeAux;
        
        timeAux = randomTime(agent, ro);
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new EventReproduce(agent,timeAux,this));
        }   
        
    }
    
    
    public void addDeath(Specimen agent) {
        
        double timeAux;
        
        timeAux = randomTime(agent, mu);
        if(timeAux < simulationTime) {
        agent.setDeathTime(randomTime(agent, mu));
        PEC.add(new EventDeath(agent, agent.getDeathTime(),this));  
        }else {
            agent.setDeathTime(simulationTime);
        }
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
        
        Collections.sort(specimens, new CompareFit());
        
        
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
    
    @Override
    public void printState() {
        
        super.printState();
        
        System.out.print("Population size: ");
        System.out.println(populationSize());
        
        Specimen.printState();
        
    }

    private int populationSize() {
        
        return 0;
    }

    public void main() {

        
	}

}
