package population;

public class Reproduction extends PopulationEvent {

    public Reproduction(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
    	
        geneBank.scheduleReproduce(agent);
    	
        agent.reproduce();

    }

}
