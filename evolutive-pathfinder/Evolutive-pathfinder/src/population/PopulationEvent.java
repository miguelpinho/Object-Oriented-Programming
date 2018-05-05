package population;

import event.Event;

public abstract class PopulationEvent extends Event {
    
    PopulationSimulation geneBank;
    Specimen agent;

    public PopulationEvent(Specimen agent, double triggerTime) {
        
        super(triggerTime);
        
        this.agent = agent;
    }
    
    @Override
    public boolean isValid() {
        
        return agent.isAlive();
    }

}