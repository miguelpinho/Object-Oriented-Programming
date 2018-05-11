package simpopulation;
/**
 * Class which defines a Death event that will occur to a specific specimen of a population
 * In this event the Death is triggered
 * @author group16
 */
public class Death<T extends Organism<T>> extends PopulationEvent<T> {
	/**
     * Base constructor.
     * 
     * @see PopulationEvent
     */
    public Death(Specimen<T> agent, double triggerTime, PopulationSimulation<T> popSimul) {
        
        super(agent, triggerTime, popSimul);
    }
    /**
     * Trigger function
     * Triggers the agent's death
     */
    @Override
    public void trigger() {
    	
    	agent.die();
    	
    }
 
}
