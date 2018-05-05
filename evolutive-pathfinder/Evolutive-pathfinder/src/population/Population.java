package population;

import java.util.LinkedList;

abstract public class Population {
    
    LinkedList<Specimen> specimens;
    
    public Population() {
        
        this.specimens = new LinkedList<Specimen>();
    }
    
    public Population(LinkedList<Specimen> specimens) {
        
        this.specimens =  specimens;
    }
    
    abstract public void addSpecimen(Specimen newSpecimen);
    
    abstract public void epidemic();

}
