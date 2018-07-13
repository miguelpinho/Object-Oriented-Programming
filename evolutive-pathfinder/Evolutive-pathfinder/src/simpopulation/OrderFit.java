package simpopulation;

import java.util.Comparator;

/**
 * Comparator implementation for ordering {@link Specimen} by decreasing fitness.
 * 
 * @author group16
 *
 * @param <T> Generic type (that implements {@link Organism}) of the specimen
 */
public class OrderFit<T extends Organism<T>> implements Comparator<Specimen<T>> {

    /**
     * Compare by decreasing fitness.
     */
	@Override
	public int compare(Specimen<T> arg0, Specimen<T> arg1) {
		
	    // Descending fitness order
		if(arg0.getFitness() < arg1.getFitness()) {
			return 1;
		}
		if(arg0.getFitness() > arg1.getFitness()) {
			return -1;
		}
		
		return 0;	
	}
	
}
