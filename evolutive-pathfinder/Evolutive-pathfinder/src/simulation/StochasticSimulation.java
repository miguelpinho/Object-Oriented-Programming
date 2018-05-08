package simulation;

import java.util.Random;
import event.PendingEventContainer;

public abstract class StochasticSimulation {

    protected double currentTime;
    protected double simulationTime;
    protected PendingEventContainer PEC;
    
    protected static Random randGen = new Random();  
	
    /**
     * 
     */
	public StochasticSimulation(double simulationTime) {
        super();
        
        this.currentTime = 0.0;
        this.simulationTime = simulationTime;
        PEC = new PendingEventContainer();
    }

	/**
	 * Runs the simulation for a given time interval, processing
	 * all generated events which belong to that time frame.
	 * 
	 * @param time  time interval for the simulation to be run
	 */
    public void step(double time) {
        double targetTime = currentTime + time;
        
        if (targetTime > simulationTime) {
            targetTime = simulationTime;
        }
        
        while (!(PEC.isEmpty()) && PEC.nextTime() <= targetTime) {
            currentTime = PEC.nextTime();
            
            PEC.triggerNext();
        }
        
        currentTime = targetTime;
        
        return;
    }
	
	public double getTime() {
	    
	    return currentTime;
	}
	
	public boolean checkEnded() {
	    
	    return PEC.isEmpty();
	}
	
	public void printState() {
	    
	    System.out.print("Present instant: ");
	    System.out.println(currentTime);
	    
	    System.out.print("Number of realized events: ");
        System.out.println(PEC.eventsTriggered());
	}
	
	/**
	 * Generates a random exponential variable. Based on the code given in the project presentation lecture.
	 * @param mean
	 * @return
	 */
	protected static double randomExp(double mean) {
        
	    return -mean * Math.log(1.0 - randGen.nextDouble());
	}

}
