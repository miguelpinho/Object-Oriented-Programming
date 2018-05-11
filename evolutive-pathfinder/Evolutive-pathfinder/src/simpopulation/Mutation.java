package simpopulation;

public class Mutation<T extends Organism<T>> extends PopulationEvent<T> {

    public Mutation(Specimen<T> agent, double triggerTime, PopulationSimulation<T> popSimul) {
        
        super(agent, triggerTime, popSimul);
    }

    @Override
    public void trigger() {
    	
    	agent.mutate();
    	
    	popSimul.scheduleMutation(agent);
        
    }
  
}
