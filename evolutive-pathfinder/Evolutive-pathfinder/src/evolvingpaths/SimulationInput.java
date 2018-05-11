package evolvingpaths;

import java.io.File;
import java.awt.Point;
import java.util.LinkedList;
import path.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 * 
 * Class that works as parser. It saves all the parameters that come in the input file. 
 *
 */
public class SimulationInput extends DefaultHandler{
    
    static String fileName;
    
    int n, m, tau, k, v, vmax, u, delta, p, cmax = 0, nz = 0, no = 0;
    Point i, f;
    LinkedList<SpcZone> z;
    LinkedList<Point> o;
    

     public void startDocument(){   
  
     }  
     public void endDocument(){   
 
     }  
     public void startElement(String uri, String name, String tag, Attributes atts){   
         int l = atts.getLength();
         int xi = 0, xf = 0, yi = 0, yf = 0;
         
         switch(tag) {
            case "simulation":
                for (int i = 0 ; i < l ; i++) {
                    switch(atts.getLocalName(i)) {
                        case "finalinst":
                            this.tau = Integer.parseInt(atts.getValue(i));
                            break;
                        case "initpop":
                            this.v = Integer.parseInt(atts.getValue(i));
                            break;
                        case "maxpop":
                            this.vmax = Integer.parseInt(atts.getValue(i));
                            break;
                        case "comfortsens":
                            this.k = Integer.parseInt(atts.getValue(i));
                            break;
                            
                    }
                 }
                break;
            case "grid":
                for (int i = 0 ; i < l ; i++) {
                    switch(atts.getLocalName(i)) {
                        case "colsnb":
                            this.n = Integer.parseInt(atts.getValue(i));
                            break;
                        case "rowsnb":
                            this.m = Integer.parseInt(atts.getValue(i));
                            break;
                    }
                 }
                break;
            case "initialpoint":
                for (int i = 0 ; i < l ; i++) {
                    switch(atts.getLocalName(i)) {
                        case "xinitial":
                            xi = Integer.parseInt(atts.getValue(i));
                            break;
                        case "yinitial":
                            yi = Integer.parseInt(atts.getValue(i));
                            break;
                            
                    }
                 }
                this.i = new Point(xi, yi);
                break;
            case "finalpoint":
                for (int i = 0 ; i < l ; i++) {
                    switch(atts.getLocalName(i)) {
                        case "xfinal":
                            xf = Integer.parseInt(atts.getValue(i));
                            break;
                        case "yfinal":
                            yf = Integer.parseInt(atts.getValue(i));
                            break;
                    }
                 }
                this.f = new Point(xf, yf);
                break;
            case "specialcostzones":
            	this.nz = Integer.parseInt(atts.getValue(0));
                this.z = new LinkedList<SpcZone>();
                break;
            case "zone":
            	// Just saves the number of zones that was indicated in the input file
            	if (z.size() < nz) {
            		for (int i = 0 ; i < l ; i++) {
                        switch(atts.getLocalName(i)) {
                            case "xinitial":
                                xi = Integer.parseInt(atts.getValue(i));
                                break;
                            case "yinitial":
                                yi = Integer.parseInt(atts.getValue(i));
                                break;
                            case "xfinal":
                                xf = Integer.parseInt(atts.getValue(i));
                                break;
                            case "yfinal":
                                yf = Integer.parseInt(atts.getValue(i));
                                break;
                        }                    
                     }
            		this.z.add(new SpcZone(new Point(xi, yi), new Point(xf, yf), 0));
            	}
                
                break;
            case "obstacles":
            	this.no = Integer.parseInt(atts.getValue(0));
                this.o = new LinkedList<Point>();
                break;
            case "obstacle":
            	// Just saves the number of obstacles indicated in the input file
            	if(o.size() < no) {
            		for (int i = 0 ; i < l ; i++) {
                        switch(atts.getLocalName(i)) {
                            case "xpos":
                                xi = Integer.parseInt(atts.getValue(i));
                                break;
                            case "ypos":
                                yi = Integer.parseInt(atts.getValue(i));
                                break;
                        }
                     }
                    this.o.add(new Point(xi, yi));
            	}
                
                break;
            case "death":
                this.u = Integer.parseInt(atts.getValue(0));
                break;
            case "reproduction":
                this.p = Integer.parseInt(atts.getValue(0));
                break;
            case "move":
                this.delta = Integer.parseInt(atts.getValue(0));
                break;
         }
         
         
     }  
     public void characters(char[]ch,int start,int length){   
         String cost = new String(ch,start,length);
         this.cmax = Math.max(Integer.parseInt(cost), cmax);
         this.z.getLast().setCost(Integer.parseInt(cost));
     } 
    
    public static void main(String[] args ) throws Exception{
        String file_name = "test.xml";
        
        SAXParserFactory fact = SAXParserFactory.newInstance();
        SAXParser saxParser = fact.newSAXParser();
        
        DefaultHandler handler = new SimulationInput();
        saxParser.parse(new File(file_name), handler);
    }
}