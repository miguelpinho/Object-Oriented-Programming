package simulation;

import event.PendingEventContainer;

public abstract class StochasticSimulation {

    double currentTime;
    protected PendingEventContainer PEC;
	
    /**
     * 
     */
	public StochasticSimulation() {
        super();
        
        this.currentTime = 0.0;
        PEC = new PendingEventContainer();
    }

	/**
	 * Runs the simulation for a given time interval, processing
	 * all generated events which belong to that time frame.
	 * 
	 * @param time  time interval for the simulation to be run
	 */
    void step(double time) {
        double nextTime = currentTime + time;
        
        while (!(PEC.isEmpty()) && PEC.nextTime() <= nextTime) {
            PEC.triggerNext();
        }
        
        currentTime = nextTime;
        
        return;
    }
	
	public double getTime() {
	    
	    return currentTime;
	}
	
	public boolean checkEnded() {
	    return PEC.isEmpty();
	}
	
	public void printState() {
	    System.out.print("Present instant:");
	    System.out.println(currentTime);
	    System.out.print("Number of realized events:");
        System.out.println(PEC.eventsTriggered());
	    
	}

}
