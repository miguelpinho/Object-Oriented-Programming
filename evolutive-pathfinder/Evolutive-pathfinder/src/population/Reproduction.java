package population;

public class Reproduction extends PopulationEvent {

    public Reproduction(Specimen agent, double triggerTime, PopulationSimulation popSimul) {
        
        super(agent, triggerTime, popSimul);
    }

    @Override
    public void trigger() {
    	
        popSimul.scheduleReproduce(agent);
    	
        agent.reproduce();

    }

}
