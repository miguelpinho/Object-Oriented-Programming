package population;

public class EventDeath extends PopulationEvent {

    public EventDeath(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
    	
    	agent.die();
    }
 
}
