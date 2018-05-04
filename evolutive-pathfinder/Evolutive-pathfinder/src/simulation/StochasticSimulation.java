package simulation;

import event.Event;
import event.PendingEventContainer;

public abstract class StochasticSimulation {

    double currentTime;
    PendingEventContainer PEC;
	
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
            Event nextEvent = PEC.removeNext();
            
            nextEvent.trigger();
        }
        
        currentTime = nextTime;
        
        return;
    }
	
	double getTime() {
	    
	    return currentTime;
	}
	
	boolean checkEnded() {
	    return PEC.isEmpty();
	}

}
