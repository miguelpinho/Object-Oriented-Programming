package population;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import path.Map;
import path.Path;
import path.SpcZone;

public class Main {

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
        
        for (int k = 0; k < 10; k++) {
            pioneers.add(new Path(map));
        }
                        
        PopulationSimulation ps = new PopulationSimulation(100.0, 100, 10, 1, 1, pioneers);
        
        ps.printState();        
        while (!ps.checkEnded()) {
            
            ps.step(100.0/20);
            ps.printState();
        }
    }
    
    public static void main2(String[] args) {
        
        Dead o;        
        LinkedList<Dead> teste = new LinkedList<Dead>();
        
        teste.add(new Dead(false));
        teste.add(new Dead(false));
        teste.add(new Dead(true));
        teste.add(new Dead(true));
        teste.add(new Dead(false));
        
        for (int i = 0; i < teste.size(); i++) {
            
            System.out.println((teste.get(i)).dead);
        }
        
        
        Iterator<Dead> cur = teste.iterator();
        
        while(cur.hasNext()) {
            
            o = (Dead) cur.next();
            
            if (o.dead) {
                
                cur.remove();
            }
        }
        
        for (int i = 0; i < teste.size(); i++) {
            
            System.out.println((teste.get(i)).dead);
        }
        
    }

}

class Dead {
    boolean dead;
    
    Dead(boolean state) {
        
        dead = state;
    }
    
    boolean check() {
        
        return dead;
    }
}


