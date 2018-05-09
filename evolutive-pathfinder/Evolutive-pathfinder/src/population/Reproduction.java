package population;

public class Reproduction extends PopulationEvent {

    public Reproduction(Specimen agent, double triggerTime, PopulationSimulation geneBank) {
        
        super(agent, triggerTime, geneBank);
    }

    @Override
    public void trigger() {
        
    	Specimen son = agent.reproduce();
    	
        geneBank.scheduleReproduce(agent);
    	
    	try {
            
            geneBank.addSpecimen(son);
        } catch (ExceedsPopulation e) {
            
            geneBank.epidemic();
        }

    }

}
