package simulation;

import java.util.Random;

/**
 * Abstract class which defines an Event based random stochastic.
 * A simulation, which has a current time and a maximum simulation time, 
 * can be run for a given time, while there are events to process.
 * 
 * Provides the basic functioning for extension.
 * 
 * @author group16
 */
public abstract class StochasticSimulation {

    /**
     * Present instant of the simulation
     */
    protected double currentTime;
    
    /**
     * Maximum simulation time
     */
    protected double simulationTime;
    
    /**
     * Keeps the future events and implicitely orders them by temporal order
     */
    protected PendingEventContainer PEC;
    
    /**
     * Random generator of this simulation
     */
    protected Random randGen;  
	
    /**
     * Default constructor.
     * @param simulationTime maximum simulation time
     */
	public StochasticSimulation(double simulationTime) {
        super();
        
        this.currentTime = 0.0;
        this.simulationTime = simulationTime;
        PEC = new PendingEventContainer();
        
        randGen = new Random();
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
	 * @param mean mean of the random variable
	 * @return random exponential variable
	 */
	protected double randExp(double mean) {
        
	    return -mean * Math.log(1.0 - randGen.nextDouble());
	}
	
	
	
}
