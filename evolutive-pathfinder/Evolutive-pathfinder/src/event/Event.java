package event;

/**
 * 
 * 
 * @author group06
 */
abstract public class Event implements Comparable<Event> {
    
    double triggerTime;

    public Event(double triggerTime) {
        
        this.triggerTime = triggerTime;
    }

    @Override
    public int compareTo(Event toEvent) {
        int cmp = 0;
               
        // Ascending order
        if (triggerTime == toEvent.triggerTime) {
            //TODO handle duplicate events!!
            
        } else if (triggerTime < toEvent.triggerTime) {
            
            cmp = -1;
        } else {
            
            cmp = 1;
        }
        
        return cmp;
    }
    
    public double getTime() {
        
        return triggerTime;
    }

    abstract public void trigger();
}
