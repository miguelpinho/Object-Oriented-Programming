package input;

import java.io.File;
import java.awt.Point;
import java.util.LinkedList;
import path.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ParseSimulationInput extends DefaultHandler{
	
	static String file_name;
	
	int n, m, tau, k, v, vmax, u, delta, p;
	Point i, f;
	LinkedList<SpcZone> z;
	LinkedList<Point> o;

	 public void startDocument(){   
		 System.out.println("Beginning the parsing of"+ file_name);   
	 }  
	 public void endDocument(){   
		 System.out.println("Parsing concluded");   
	 }  
	 public void startElement(String uri, String name, String tag, Attributes atts){   
		 int l = atts.getLength();
		 int xi = 0, xf = 0, yi = 0, yf = 0;
		 
		 
		 System.out.println("Element <" + tag + "> " + uri);
		 
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
					System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
					
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
					System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
					
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
		 			
					System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
					
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
		 			
					System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
					
				 }
		 		this.f = new Point(xf, yf);
		 		break;
		 	case "specialcostzones":
		 		this.z = new LinkedList<SpcZone>();
		 		break;
		 	case "zone":
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
		 			
					System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
				 }
		 		this.z.add(new SpcZone(new Point(xi, yi), new Point(xf, yf), 0));
		 		break;
		 	case "obstacles":
		 		this.o = new LinkedList<Point>();
		 		break;
		 	case "obstacle":
		 		for (int i = 0 ; i < l ; i++) {
		 			switch(atts.getLocalName(i)) {
		 				case "xpos":
		 					xi = Integer.parseInt(atts.getValue(i));
		 					break;
		 				case "ypos":
		 					yi = Integer.parseInt(atts.getValue(i));
		 					break;
		 			}
		 			
					System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
				 }
		 		this.o.add(new Point(xi, yi));
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
		 this.z.getLast().setCost(Integer.parseInt(cost));
		 System.out.println(cost);  
	 } 
	
	public static void main(String[] args ) throws Exception{
		file_name = "test.xml";
		
		SAXParserFactory fact = SAXParserFactory.newInstance();
		SAXParser saxParser = fact.newSAXParser();
		
		DefaultHandler handler = new ParseSimulationInput();
		saxParser.parse(new File(file_name), handler);
	}
}