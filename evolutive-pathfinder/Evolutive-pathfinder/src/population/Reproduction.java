package population;

public class Reproduction<T extends Organism<T>> extends PopulationEvent<T> {

    public Reproduction(Specimen<T> agent, double triggerTime, PopulationSimulation<T> popSimul) {
        
        super(agent, triggerTime, popSimul);
    }

    @Override
    public void trigger() {
    	
    	//popSimul.scheduleReproduce(agent);
    	
    	popSimul.scheduleReproduce(agent);
    	
        agent.reproduce();
        
    }

}
