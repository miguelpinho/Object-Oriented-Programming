package simpopulation;

import simulation.Event;

public abstract class PopulationEvent<T extends Organism<T>> extends Event {
   
    PopulationSimulation<T> popSimul;
    Specimen<T> agent;
    
    static double param;

    public PopulationEvent(Specimen<T> agent, double triggerTime, PopulationSimulation<T> geneBank) {
        
        super(triggerTime);
        
        this.agent = agent;
        this.popSimul = geneBank;
    }
    
    @Override
    public boolean isValid() {
        
        return agent.isAlive();
    }

}
