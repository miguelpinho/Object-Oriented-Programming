package population;

public class EventDeath extends PopulationEvent {

    public EventDeath(Specimen agent, double triggerTime) {
        
        super(agent, triggerTime);
    }

    @Override
    public void trigger() {
        
    }

}
