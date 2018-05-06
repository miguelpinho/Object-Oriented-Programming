package population;

import event.Event;

public abstract class PopulationEvent extends Event {
    
    PopulationSimulation geneBank;
    Specimen agent;

    public PopulationEvent(Specimen agent, double triggerTime,PopulationSimulation geneBank ) {
        
        super(triggerTime);
        
        this.agent = agent;
        this.geneBank = geneBank;
    }
    
    @Override
    public boolean isValid() {
        
        return agent.isAlive();
    }

}
