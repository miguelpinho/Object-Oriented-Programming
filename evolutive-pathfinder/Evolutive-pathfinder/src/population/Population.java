package population;

import java.util.LinkedList;

abstract public class Population {
    
    LinkedList<Specimen> specimens;
    
    public Population() {
        
    }
    
    abstract public void epidemic();

}
