package simulation;

/**
 * Abstract class which defines an event of a simulation, scheduled to be triggered at a specific time
 * It should be extended to define a specific event, with an associated effect.
 * @author group06
 */
abstract public class Event implements Comparable<Event> {
    
    protected double triggerTime;

    /**
     * Base constructor.
     * @param triggerTime time for the event to be triggered
     */
    public Event(double triggerTime) {
        
        this.triggerTime = triggerTime;
    }

    /**
     * Events are sorted by default in ascending order of trigger times. 
     */
    @Override
    public int compareTo(Event toEvent) {
               
        // Ascending order
        if (triggerTime < toEvent.triggerTime) {
            
            return -1;
        } else if (triggerTime > toEvent.triggerTime) {
            
            return 1;
        }
        
        return 0;
    }
    
    /**
     * Get the trigger time of the event.
     * @return trigger time
     */
    public double getTime() {
        
        return triggerTime;
    }

    /**
     * Trigger the event effect.
     */
    abstract public void trigger();
    
    /**
     * Check if the event is still valid. It can be rendered invalid by some state in a simulation.
     * @return
     */
    abstract public boolean isValid();
    
}
