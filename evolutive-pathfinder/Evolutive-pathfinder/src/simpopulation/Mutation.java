package simpopulation;
/**
 * Class which defines a Mutation event that will occur to a specific specimen of a population
 * In this event the Mutation is triggered
 * @author group16
 */
public class Mutation<T extends Organism<T>> extends PopulationEvent<T> {
	 
    /**
     * 
     * @param agent specimen involved   
     * @param triggerTime time to trigger the event
     * @param popSimul simulation this event belongs to
     */
    public Mutation(Specimen<T> agent, double triggerTime, PopulationSimulation<T> popSimul) {
        
        super(agent, triggerTime, popSimul);
    }
    /**
     * Trigger function
     * Creates a new mutation event for the same individual
     * Triggers the agent's mutation 
     */
    @Override
    public void trigger() {
    	
    	agent.mutate();
    	
    	popSimul.scheduleMutation(agent);
        
    }
  
}
