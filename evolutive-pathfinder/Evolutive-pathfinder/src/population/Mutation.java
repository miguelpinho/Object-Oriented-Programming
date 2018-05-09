package population;

public class Mutation extends PopulationEvent {

    public Mutation(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
    	
    	agent.mutate();
    	
    	geneBank.scheduleMutation(agent);
        
    }
  
}
