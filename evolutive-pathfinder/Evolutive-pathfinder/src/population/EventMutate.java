package population;

public class EventMutate extends PopulationEvent {

    public EventMutate(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
    	
    	agent.mutate();
    	geneBank.addMutation(agent);
        
    }
  
}
