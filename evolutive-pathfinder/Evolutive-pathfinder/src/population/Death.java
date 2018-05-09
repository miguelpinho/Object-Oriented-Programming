package population;

public class Death extends PopulationEvent {

    public Death(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
    	
    	agent.die();
    	
    }
 
}
