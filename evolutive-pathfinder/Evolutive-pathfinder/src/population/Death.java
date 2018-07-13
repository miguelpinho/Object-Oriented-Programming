package population;

public class Death extends PopulationEvent {

    public Death(Specimen agent, double triggerTime, PopulationSimulation popSimul) {
        
        super(agent, triggerTime, popSimul);
    }

    @Override
    public void trigger() {
    	
    	agent.die();
    	
    }
 
}
