package simpopulation;
/**
 * Class which defines a reproduction event that will occur to a specific specimen of a population
 * In this event the reproduction is triggered
 * @author group16
 */
public class Reproduction<T extends Organism<T>> extends PopulationEvent<T> {
	 /**
     * Base constructor.
     * 
     * @see PopulationEvent
     */
    public Reproduction(Specimen<T> agent, double triggerTime, PopulationSimulation<T> popSimul) {
        
        super(agent, triggerTime, popSimul);
    }
    /**
     * Trigger function
     * Creates a new reproduce event for the same individual
     * Triggers the agent's reproduction 
     */
    @Override
    public void trigger() {
    	
    	popSimul.scheduleReproduce(agent);
    	
        agent.reproduce();
        
    }

}
