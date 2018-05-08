package event;

import java.util.PriorityQueue;

/**
 * Implements a pending event container, where Events can be added 
 * and accessed/triggered by temporal order.
 * @author grupo16
 */
public class PendingEventContainer {

    PriorityQueue<Event> events;
    int eventCounter;
    
    /**
     * Empty constructor for pending event container.
     */
    public PendingEventContainer() {
        
        events = new PriorityQueue<Event>();
        eventCounter = 0;
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
        
        next = events.peek();
        
        return next.getTime();
    }
    
    /**
     * 
     * @return
     */
    public Event removeNext() {
        Event next;
        
        if (events.isEmpty()) {
            return null;
        }
        
        next = events.poll();
        
        eventCounter++;
        
        return next;
    }
    
    /**
     * 
     */
    public boolean triggerNext() {
        Event next;
        
        if (events.isEmpty()) {
            return false;
        }
        
        next = events.poll();
        
        eventCounter++;
        
        next.trigger();
        
        return true;
    }
    
    /**
     * Add new pending event.
     */
    public void add(Event newEvent) {
        
        events.add(newEvent);
    }
    
    /**
     * Delete all the pending events that are marked as no longer valid, due to,
     * for example, an exception.
     */
    public void deleteAllInvalid() {
    	
    	Event[] aux = events.toArray(new Event[0]); 
        events = new PriorityQueue<Event>();
        
        for(int i = 0;i<aux.length;i++) {
        	if(aux[i].isValid()) {
        		events.add(aux[i]);
        	}
        }
    }
    
    public int eventsTriggered() {
       
        return eventCounter;
    }

}
