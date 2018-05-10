package population;

public class Death<T extends Organism<T>> extends PopulationEvent<T> {

    public Death(Specimen<T> agent, double triggerTime, PopulationSimulation<T> popSimul) {
        
        super(agent, triggerTime, popSimul);
    }

    @Override
    public void trigger() {
    	
    	agent.die();
    	
    }
 
}
