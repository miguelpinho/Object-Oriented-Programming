package simpopulation;

import simulation.StochasticSimulation;
import java.util.LinkedList;
/**
 * 
 * Class the manages the population simulation at a top level. 
 * Used with the API defined in {@link simulation.StochasticSimulation}. Also outputs the fittest Specimen in any given observation
 * of the simulation. 
 * Creates new events, manages event times and keeps the internal state of the simulation (the population).
 * @author group16
 * 
 */
public class PopulationSimulation<T extends Organism<T>> extends StochasticSimulation {
	
    protected Population<T> geneBank;
    protected int paramDeath, paramMutation, paramReproduce;
    
    /**
     * Creates a simulation of a population evolution, with random death, reproduction and mutation events.
     * @param simulationTime The maximum time the simulation is allowed to run
     * @param maxPop The max population allowed before an epidemic is triggered
     * @param paramDeath Parameter for death event random variable
     * @param paramMutation Parameter for mutation event random variable 
     * @param paramReproduce Parameter for reproduce event random variable
     * @param pioneers List of original individuals
     * 
     */
    public PopulationSimulation(double simulationTime, int maxPop, int paramDeath, int paramMutation, int paramReproduce, LinkedList<T> pioneers) {
        super(simulationTime);
        
        this.paramDeath = paramDeath;
        this.paramMutation = paramMutation;
        this.paramReproduce = paramReproduce;
        
        geneBank = new Population<T>(this, pioneers, maxPop);

    }
    /**
     * Death event creator
     * Calculates the time of death and if it's valid adds the event to the PEC
     * @param agent Specimen to which the event applies
     */
    protected void scheduleDeath(Specimen<T> agent) {
            
        double timeAux;
        
        timeAux = currentTime + randExp((1 - Math.log10(1 - agent.getFitness())) * (double) paramDeath);
        
        if(timeAux < simulationTime) {
            
            agent.setDeathTime(timeAux);
            PEC.add(new Death<T>(agent, agent.getDeathTime(), this));  
        } else {
            
            agent.setDeathTime(simulationTime);
        }
    }
    /**
     * Reproduction event creator
     * Calculates the Reproduction time  and if it's valid adds the event to the PEC
     * @param agent Specimen to which the event applies
     * 
     */
    protected void scheduleReproduce(Specimen<T> agent) {
        
        double timeAux;
        
        timeAux = currentTime + randExp((1 - Math.log10(agent.getFitness())) * (double) paramReproduce);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new Reproduction<T>(agent, timeAux, this));
        }   
        
    } 
    /**
     * Mutation event creator
     * Calculates the Mutation time and if it's valid adds the event to the PEC
     * @param agent Specimen to which the event applies
     */
    
    protected void scheduleMutation(Specimen<T> agent) {
        
        double timeAux;
        
        timeAux = currentTime + randExp((1 - Math.log10(agent.getFitness())) * (double) paramMutation);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new Mutation<T>(agent, timeAux, this));
        }   
        
    }
    /**
     * Validity control of the PEC
     * Verifies if all events are valid or not, removing the ones that are not.
     */
    protected void updateSchedule() {
        
        PEC.deleteAllInvalid();
    }
    /**
     * Random double number generator
     * @return random number between 0 and 1
     */
    protected double randUniform() {
            
            return randGen.nextDouble();
    }

    /**
     * State control
     * Prints to the console the current state of the simulation
     */
    @Override
    public void printState() {
        
        super.printState();
        
        geneBank.printState();
    }
    
    /**
     * Check best fit organism
     * @return fittest organism if any, null otherwise
     */
    public T getFittest() {
    	
    	return geneBank.getFittest();
    }

}
