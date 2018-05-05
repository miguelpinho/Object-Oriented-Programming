package population;

import event.Event;

public abstract class PopulationEvent extends Event {
    
    Specimen agent;

    public PopulationEvent(Specimen agent, double triggerTime) {
        
        super(triggerTime);
        
        this.agent = agent;
    }

}
