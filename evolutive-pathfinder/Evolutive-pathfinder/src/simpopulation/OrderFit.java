package simpopulation;

import java.util.Comparator;

public class OrderFit<T extends Organism<T>> implements Comparator<Specimen<T>> {

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