package simpopulation;

import simulation.Event;

/**
 * Abstract class which defines an event that will occur to a specific specimen of a population
 * It should be extended to define a specific event, with an associated effect.
 * @author group16
 */
public abstract class PopulationEvent<T extends Organism<T>> extends Event {
   
    protected PopulationSimulation<T> popSimul;
    protected Specimen<T> agent;
    
    static double param;
    /**
     * Base constructor.
     * @param geneBank The population whose state will change as a result of the event
     * @param agent The specimen that the event will occur over.
     * @param triggerTime Time of event trigger
     */
    public PopulationEvent(Specimen<T> agent, double triggerTime, PopulationSimulation<T> geneBank) {
        
        super(triggerTime);
        
        this.agent = agent;
        this.popSimul = geneBank;
    }
    /**
     * Validity function
     * Checks if an event is valid by verifying that it's agent is still alive
     * @return true if the is valid, false if not
     */
    @Override
    public boolean isValid() {
        
        return agent.isAlive();
    }

}
