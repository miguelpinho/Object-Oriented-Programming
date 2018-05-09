package population;

public class Mutation extends PopulationEvent {

    public Mutation(Specimen agent, double triggerTime, PopulationSimulation popSimul) {
        
        super(agent, triggerTime, popSimul);
    }

    @Override
    public void trigger() {
    	
    	agent.mutate();
    	
    	popSimul.scheduleMutation(agent);
        
    }
  
}
