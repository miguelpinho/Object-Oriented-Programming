package population;

public class EventReproduce extends PopulationEvent {

    public EventReproduce(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
        
    	Specimen son = agent.reproduce();
    	
    	geneBank.addSpecimen(son); 	
        geneBank.addReproduce(agent);
    	//throw new ExceedsPopulation(); Ve isto Pinho
    }
    

}
