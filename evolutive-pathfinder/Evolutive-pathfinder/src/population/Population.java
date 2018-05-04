package population;

import java.util.LinkedList;

abstract public class Population {
    
    LinkedList<Specimen> specimens;
    
    public Population() {
        
    }
    
    public Population(LinkedList<Specimen> specimens) {
        
        this.specimens = (LinkedList<Specimen>) specimens.clone();
    }
    
    abstract public void addSpecimen(Specimen newSpecimen);
    
    abstract public void epidemic();

}
