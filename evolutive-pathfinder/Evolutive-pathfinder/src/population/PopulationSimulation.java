package population;

import simulation.StochasticSimulation;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import path.Path;

public class PopulationSimulation extends StochasticSimulation {

	LinkedList<Specimen> specimens;
	
    protected int initPop, maxPop, curPop;
    protected int paramDeath, paramMutation, paramReproduce;
    
    protected Path fittest;
    
    /**
     * Creates a simulation of a population evolution, with random death, reproduction and mutation events.
     * @param simulationTime The maximum time the simulation is allowed to run
     * @param initPop The population at the beginning of the simulation
     * @param maxPop The max population allowed before an epidemic is triggered
     * @param paramDeath Parameter for death event random variable
     * @param paramMutation Parameter for mutation event random variable 
     * @param paramReproduce Parameter for reproduce event random variable
     */
    public PopulationSimulation(double simulationTime, int initPop, int maxPop, int paramDeath, int paramMutation, int paramReproduce, LinkedList<Path> pioneers) {
        super(simulationTime);
        
        this.initPop = initPop;
        this.maxPop = maxPop;
        this.curPop = 0;
        
        this.paramDeath = paramDeath;
        this.paramMutation = paramMutation;
        this.paramReproduce = paramReproduce;
        
        this.specimens = new LinkedList<Specimen>();
        
        fittest = null;
        
        genesis(pioneers);
    }

    /**
	 *  Adds a new specimen to the population, creating the corresponding death,reproduction and mutation events 
	 *  @param newSpecimen Specimen to add
	 */
    public void addSpecimen(Specimen newSpecimen) throws ExceedsPopulation  {
        
        specimens.add(newSpecimen);
        
        scheduleDeath(newSpecimen);
        scheduleReproduce(newSpecimen);
        scheduleMutation(newSpecimen);
    
        curPop++;
        if (curPop > maxPop) {
            
            throw new ExceedsPopulation();
        }
    }
    
    public void removeDead() {
    
        Specimen s;
        Iterator<Specimen> cur = specimens.iterator();
        
        while(cur.hasNext()) {
            
            s = (Specimen) cur.next();
            
            if (!(s.isAlive())) {
                
                cur.remove();
            }
        }
    }
    
    public void scheduleReproduce(Specimen agent) {
        
        double timeAux;
        
        timeAux = currentTime + StochasticSimulation.randomExp((1 - Math.log10(agent.getFitness())) * paramReproduce);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new EventMutate(agent, timeAux, this));
        }   
        
    }
    
    public void scheduleMutation(Specimen agent) {
        
        double timeAux;
        
        agent.updateFittest(fittest);
        
        timeAux = currentTime + StochasticSimulation.randomExp((1 - Math.log10(agent.getFitness())) * paramMutation);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new EventReproduce(agent, timeAux, this));
        }   
        
    }
    
    
    public void scheduleDeath(Specimen agent) {
        
        double timeAux;
        
        timeAux = currentTime + StochasticSimulation.randomExp((1 - Math.log10(1 - agent.getFitness())) * paramDeath);
        
        if(timeAux < simulationTime) {
            
            agent.setDeathTime(timeAux);
            PEC.add(new EventDeath(agent, agent.getDeathTime(), this));  
        } else {
            
            agent.setDeathTime(simulationTime);
        }
    }
    
    /**
     * Create initial population.
     */
    public void genesis(LinkedList<Path> pioneers) {
    	
    	for(int i=0; i < pioneers.size(); i++) {

    		try {
    		    
    		    addSpecimen(new Specimen(pioneers.get(i)));
    		} catch (ExceedsPopulation e) {
    		    
    		    epidemic();
    		}
    		
    	}
    	
    }
    
    /**
     * The population suffers an epidemic.
     */
    public void epidemic() {
        
    	int spareCounter = 0;
    	
        Collections.sort(specimens, new CompareFit());
        
        for (int i = 0; i < specimens.size(); i++) {
			if(specimens.get(i).isAlive()) {
				if(spareCounter > 5) {
					if(randGen.nextDouble() > specimens.get(i).getFitness()) {
						specimens.get(i).die();
					}
				}
				
				spareCounter++;
			}
			
		}
        
        PEC.deleteAllInvalid();
        
    }
    
    @Override
    public void printState() {
        
        super.printState();
        
        System.out.print("Population size: ");
        System.out.println(populationSize());
        
        if (fittest != null) {
            fittest.printState();
        }
        
    }

    private int populationSize() {
        
        return curPop;
    }

}
