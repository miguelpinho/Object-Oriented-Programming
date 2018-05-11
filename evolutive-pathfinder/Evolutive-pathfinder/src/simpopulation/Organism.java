package simpopulation;

/**
 * Interface for the model of a living organism, which can replicate, reproduce (assexually) and mutate.
 * Its fitness in its environment can be computed or compared.
 * 
 * @author group16
 *
 * @param <T> generic type for the organism implementation
 */
public interface Organism<T> {
    
    T replicate();
    
    T reproduce();
    
    void mutate();
    
    boolean isFitter(T other);
    
    double getFitness();
    
    String toString();
}
