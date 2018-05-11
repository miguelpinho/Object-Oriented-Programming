package evolvingpaths;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import path.Map;
import path.Path;
import simpopulation.PopulationSimulation;

public class Main {

    public static void main(String[] args)  {
       
        String fileName = args[0];
        
        SAXParserFactory fact = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
		try {
			saxParser = fact.newSAXParser();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			System.out.println("ERROR: Creating parser");
			System.exit(1);
		} catch (SAXException e1) {
			e1.printStackTrace();
			System.out.println("ERROR: Creating parser");
			System.exit(1);
		}
        
        SimulationInput input = new SimulationInput();
        try {
			saxParser.parse(new File(fileName), input);
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println("ERROR: Initializing parser");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("ERROR: Input file not available");
			System.exit(1);
		}
        
        // Create map
        Map inputMap = new Map(input.n, input.m, input.cmax, input.k, input.i, input.f, input.z, input.o);
        
        // Create initial population
        LinkedList<Path> initialPopulation = new LinkedList<Path>();
        for (int i = 0; i < input.v; i++) {
                        
            initialPopulation.add(new Path(inputMap));
        }
        
        // Initialize the population simulation
        PopulationSimulation<Path> simulPopulation = new PopulationSimulation<Path>(input.tau, input.vmax, input.u, input.p, input.delta, initialPopulation);
        
        // Run simulation step by step and print state
        int observation = 0;
        
        System.out.print("Observation ");
        System.out.print(observation);
        System.out.println(":");
        simulPopulation.printState();
        
        while (!(simulPopulation.checkEnded())) {
            simulPopulation.step((double) input.tau * 0.05);
            
            observation ++;
                      
            System.out.print("Observation ");
            System.out.print(observation);
            System.out.println(":");
            simulPopulation.printState();
            
            simulPopulation.getFittest().printState();
        }
        
        System.out.print("Path of the best fit individual: ");
        System.out.println(simulPopulation.getFittest());
        
    }

}
