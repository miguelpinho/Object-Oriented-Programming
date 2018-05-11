package simpopulation;

import simulation.StochasticSimulation;
import java.util.LinkedList;

public class PopulationSimulation<T extends Organism<T>> extends StochasticSimulation {
	
    Population<T> geneBank;
    protected int paramDeath, paramMutation, paramReproduce;
    
    /**
     * Creates a simulation of a population evolution, with random death, reproduction and mutation events.
     * @param simulationTime The maximum time the simulation is allowed to run
     * @param initPop The population at the beginning of the simulation
     * @param maxPop The max population allowed before an epidemic is triggered
     * @param paramDeath Parameter for death event random variable
     * @param paramMutation Parameter for mutation event random variable 
     * @param paramReproduce Parameter for reproduce event random variable
     */
    public PopulationSimulation(double simulationTime, int maxPop, int paramDeath, int paramMutation, int paramReproduce, LinkedList<T> pioneers) {
        super(simulationTime);
        
        this.paramDeath = paramDeath;
        this.paramMutation = paramMutation;
        this.paramReproduce = paramReproduce;
        
        geneBank = new Population<T>(this, pioneers, maxPop);

    }
    
    public void scheduleDeath(Specimen<T> agent) {
            
        double timeAux;
        
        timeAux = currentTime + randExp((1 - Math.log10(1 - agent.getFitness())) * (double) paramDeath);
        
        if(timeAux < simulationTime) {
            
            agent.setDeathTime(timeAux);
            PEC.add(new Death<T>(agent, agent.getDeathTime(), this));  
        } else {
            
            agent.setDeathTime(simulationTime);
        }
    }
    
    public void scheduleReproduce(Specimen<T> agent) {
        
        double timeAux;
        
        timeAux = currentTime + randExp((1 - Math.log10(agent.getFitness())) * (double) paramReproduce);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new Reproduction<T>(agent, timeAux, this));
        }   
        
    }
    
    public void scheduleMutation(Specimen<T> agent) {
        
        double timeAux;
        
        timeAux = currentTime + randExp((1 - Math.log10(agent.getFitness())) * (double) paramMutation);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new Mutation<T>(agent, timeAux, this));
        }   
        
    }
    
    public void updateSchedule() {
        
        PEC.deleteAllInvalid();
    }
    
    protected double randUniform() {
            
            return randGen.nextDouble();
    }

    
    @Override
    public void printState() {
        
        super.printState();
        
        geneBank.printState();
    }
    
    /**
     * 
     * @return fittest organism if any, null otherwise
     */
    public T getFittest() {
    	
    	return geneBank.getFittest();
    }

}
