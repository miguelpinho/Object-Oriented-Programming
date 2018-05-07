package population;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        
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


