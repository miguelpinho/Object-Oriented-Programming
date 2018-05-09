package population;

import simulation.StochasticSimulation;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import path.Path;

public class PopulationSimulation extends StochasticSimulation {

	LinkedList<Specimen> specimens;
	
    protected int maxPop;
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
    public PopulationSimulation(double simulationTime, int maxPop, int paramDeath, int paramMutation, int paramReproduce, LinkedList<Path> pioneers) {
        super(simulationTime);
        
        this.maxPop = maxPop;
        
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
    
        if (specimens.size() > maxPop) {
            
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
                
                return;
            }
        }
    }
    
    public void removeAllDead() {
    
        Specimen s;
        Iterator<Specimen> cur = specimens.iterator();
        
        while(cur.hasNext()) {
            
            s = (Specimen) cur.next();
            
            if (!(s.isAlive())) {
                
                cur.remove();
            }
        }
    }
    
    public void scheduleDeath(Specimen agent) {
            
        double timeAux;
        
        timeAux = currentTime + StochasticSimulation.randomExp((1 - Math.log10(1 - agent.getFitness())) * (double) paramDeath);
        
        if(timeAux < simulationTime) {
            
            agent.setDeathTime(timeAux);
            PEC.add(new Death(agent, agent.getDeathTime(), this));  
        } else {
            
            agent.setDeathTime(simulationTime);
        }
    }
    
    public void scheduleReproduce(Specimen agent) {
        
        double timeAux;
        
        timeAux = currentTime + StochasticSimulation.randomExp((1 - Math.log10(agent.getFitness())) * (double) paramReproduce);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new Mutation(agent, timeAux, this));
        }   
        
    }
    
    public void scheduleMutation(Specimen agent) {
        
        double timeAux;
        
        fittest = agent.updateFittest(fittest);
        
        timeAux = currentTime + StochasticSimulation.randomExp((1 - Math.log10(agent.getFitness())) * (double) paramMutation);
        
        if(timeAux < agent.getDeathTime()) {
            PEC.add(new Reproduction(agent, timeAux, this));
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
    	
        Collections.sort(specimens, new OrderFit());
        
        for (int i = 0; i < specimens.size(); i++) {
			if(specimens.get(i).isAlive()) {
				if(spareCounter < 5) {
					
					spareCounter++;
				} else {
				    
				    if(randGen.nextDouble() > specimens.get(i).getFitness()) {
                        specimens.get(i).die();
                    }
				}
			}
			
		}
        
        PEC.deleteAllInvalid();
        
        removeAllDead();
        
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
        
        return specimens.size();
    }

}
