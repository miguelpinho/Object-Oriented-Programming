package population;

import simulation.StochasticSimulation;

public class PopulationSimulation extends StochasticSimulation {

    Population geneBank;
    
    public PopulationSimulation(Population initial) {
        super();
        
        geneBank = initial;
    }

}
