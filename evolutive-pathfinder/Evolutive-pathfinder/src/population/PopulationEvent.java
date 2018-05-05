package population;

import event.Event;
import java.lang.Math;

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
    @Override
    public double computeTime(int constant) {
    	
    	
    	return (1 - Math.log10(agent.getFitness())*constant);
    	
    }

}
