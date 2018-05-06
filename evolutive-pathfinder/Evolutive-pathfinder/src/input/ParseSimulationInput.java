package input;

import java.io.File;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ParseSimulationInput extends DefaultHandler{
	
	static String file_name;
	
	 public void startDocument(){   
		 System.out.println("Beginning the parsing of"+ file_name);   
	 }  
	 public void endDocument(){   
		 System.out.println("Parsing concluded");   
	 }  
	 public void startElement(String uri, String name, String tag, Attributes atts){   
		 int l = atts.getLength();
		 
		 System.out.println("Element <" + tag + "> " + uri);
		 
		 for (int i = 0 ; i < l ; i++) {
			 System.out.println("Attribute " + atts.getLocalName(i) + " " + atts.getValue(i));
		 }
	 }  
	 public void characters(char[]ch,int start,int length){   
		 System.out.println(new String(ch,start,length));  
	 } 
	 

	public static void main(String[] args) throws Exception{
		file_name = "test.xml";
		
		SAXParserFactory fact = SAXParserFactory.newInstance();
		SAXParser saxParser = fact.newSAXParser();
		
		DefaultHandler handler = new ParseSimulationInput();
		saxParser.parse(new File(file_name), handler);
	}

}