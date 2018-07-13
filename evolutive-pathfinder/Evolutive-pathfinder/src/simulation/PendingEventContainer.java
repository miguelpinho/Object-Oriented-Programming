package simulation;

import java.util.PriorityQueue;

/**
 * Implements a pending event container, where Events can be added 
 * and accessed/triggered by temporal order.
 * @author grupo16
 */
public class PendingEventContainer {

    protected PriorityQueue<Event> events;
    protected int eventCounter;
    
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
    
    public boolean triggerNext() {
        Event next;
        
        if (events.isEmpty()) {
            System.out.println("**************No more events!");
            
            return false;
        }
        
        next = events.poll();
        
        if (!next.isValid()) {
            System.out.println("***************Invalid event!");            
            
            return false;
        }
        
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
    
    /**
     * Get the number of events triggered until this moment.
     * @return number of events triggered
     */
    public int eventsTriggered() {
       
        return eventCounter;
    }

}
