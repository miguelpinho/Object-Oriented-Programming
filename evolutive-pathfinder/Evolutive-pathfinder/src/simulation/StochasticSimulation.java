package simulation;

public abstract class StochasticSimulation {

    double currentTime;
	
    /**
     * 
     */
	public StochasticSimulation() {
        super();
        
        this.currentTime = 0.0;
    }

	/**
	 * Runs the simulation for a given time interval, processing
	 * all generated events which belong to that time frame.
	 * 
	 * @param time  time interval for the simulation to be run
	 * @return current time after this step
	 */
    double step(double time) {
        
        return currentTime + time;
    }
	
	double getTime() {
	    
	    return currentTime;
	}

}
