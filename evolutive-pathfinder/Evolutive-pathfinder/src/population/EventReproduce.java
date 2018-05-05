package population;

public class EventReproduce extends PopulationEvent {

    public EventReproduce(Specimen agent, double triggerTime) {
        
        super(agent, triggerTime);
    }

    @Override
    public void trigger() {

        agent.Reproduce();
    	
    }


}
