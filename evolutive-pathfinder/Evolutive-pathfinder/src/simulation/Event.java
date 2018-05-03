package simulation;

abstract public class Event implements Comparable<Event>{
    
    double triggerTime;

    public Event() {
        
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

}
