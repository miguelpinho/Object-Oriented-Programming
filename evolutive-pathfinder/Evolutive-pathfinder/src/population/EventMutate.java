package population;

public class EventMutate extends PopulationEvent {

    public EventMutate(Specimen agent, double triggerTime) {
        
        super(agent, triggerTime);
    }

    @Override
    public void trigger() {
    	
    	agent.Mutate();
        
    }
  
}
