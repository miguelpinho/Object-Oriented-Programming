package population;

public interface Organism<T> {
    
    T replicate();
    
    T reproduce();
    
    void mutate();
    
    boolean isFitter(T other);
    
    double getFitness();
    
    void printState();
    
    String toString();
}
