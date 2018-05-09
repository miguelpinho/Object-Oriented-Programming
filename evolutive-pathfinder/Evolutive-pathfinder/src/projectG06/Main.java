package projectG06;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import path.Map;
import path.Path;
import population.PopulationSimulation;

public class Main {

    public static void main(String[] args) throws Exception {
       
        String fileName = "test5.xml";
        
        SAXParserFactory fact = SAXParserFactory.newInstance();
        SAXParser saxParser = fact.newSAXParser();
        
        ParseSimulationInput input = new ParseSimulationInput();
        saxParser.parse(new File(fileName), input);
        
        // Create map
        Map inputMap = new Map(input.n, input.m, input.cmax, input.k, input.i, input.f, input.z, input.o);
        
        // Create initial population
        LinkedList<Path> initialPopulation = new LinkedList<Path>();
        for (int i = 0; i < input.v; i++) {
                        
            initialPopulation.add(new Path(inputMap));
        }
        
        // Initialize the population simulation
        PopulationSimulation simulPopulation = new PopulationSimulation(input.tau, input.vmax, input.u, input.p, input.delta, initialPopulation);
        
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
        }
        
    }

}
