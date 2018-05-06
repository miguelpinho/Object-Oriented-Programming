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
    void step(double time) {
        double targetTime = currentTime + time;
        
        while (!(PEC.isEmpty()) && PEC.nextTime() <= targetTime) {
            this.currentTime = PEC.nextTime();
            
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
	
	public static double randomExp(double mean) {
        
	    return mean;
	}

}
