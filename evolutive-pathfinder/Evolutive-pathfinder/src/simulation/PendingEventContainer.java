package simulation;

import java.util.TreeSet;

/**
 * Implements a pending event container, where Events can be added 
 * and accessed/triggered by temporal order.
 * @author grupo16
 */
public class PendingEventContainer {

    TreeSet<Event> events;
    
    /**
     * Empty constructor for pending event container.
     */
    public PendingEventContainer() {
        
        events = new TreeSet<Event>();
    }
    
    /**
     * Checks if there is any event pending to happen.
     * @return true if there is any pending event, false otherwise.
     */
    public boolean isEmpty() {
        
        return events.isEmpty();
    }
    
    /**
     * Returns the trigger time of the next pending event.
     * @return trigger time of next event.
     */
    public double nextTime() {
        Event next;
        
        next = events.first();
        
        return next.getTime();
    }
    
    public Event removeNext() {
        Event next;
        
        if (events.isEmpty()) {
            return null;
        }
        
        next = events.pollFirst();
        
        return next;
    }

}
