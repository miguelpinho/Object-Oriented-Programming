package population;

import simulation.Event;

public abstract class PopulationEvent extends Event {
   
    PopulationSimulation popSimul;
    Specimen agent;
    
    static double param;

    public PopulationEvent(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(triggerTime);
        
        this.agent = agent;
        this.popSimul = geneBank;
    }
    
    @Override
    public boolean isValid() {
        
        return agent.isAlive();
    }

}
