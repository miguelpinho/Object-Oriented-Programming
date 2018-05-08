package population;

import java.util.Comparator;

public class CompareFit implements Comparator<Specimen> {

	@Override
	public int compare(Specimen arg0, Specimen arg1) {
		
		if(arg0.getFitness() > arg1.getFitness()) {
			return 1;
		}
		if(arg0.getFitness() < arg1.getFitness()) {
			return -1;
		}
		
		return 0;
		
	}
	
	

}
