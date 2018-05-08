package population;

import java.awt.Point;
import java.util.LinkedList;

import path.Map;
import path.Path;
import path.SpcZone;

public class PopulationTest {
    
    public static void main(String[] args) {
        int n = 5, m = 4;
        Point i = new Point(1,1);
        Point f = new Point(5,4);
        LinkedList<SpcZone> z = new LinkedList<SpcZone>();
        LinkedList<Point> o = new LinkedList<Point>();
        
        z.add(new SpcZone(new Point(2, 2), new Point(3,3), 4));
        o.add(new Point(2, 3));
        
        Map map = new Map(n, m, 4, 3, i, f, z, o);
        
        LinkedList<Path> pioneers = new LinkedList<Path>();
        
        for (int k = 0; k < 4; k++) {
            pioneers.add(new Path(map));
        }
                        
        PopulationSimulation ps = new PopulationSimulation(200.0, 50, 5, 1, 1, pioneers);
        
        ps.printState();        
        while (!ps.checkEnded()) {
            
            ps.step(200.0/20);
            ps.printState();
        }
    }

}
