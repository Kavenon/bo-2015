package pl.agh.edu.bo.projekt;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {    	
    	// make it random
        Random random = new Random();        
     
        int verticles = random.nextInt(20)+5;
        
        float probability = 0.5f;
       
    	// CREATE GRAPH
    	OurGraph graph = new OurGraph(verticles, probability);
    	graph.generateRandomGraph();
    	
    	//Initialization
    	int initPaths = random.nextInt(5)+2;
    	
    	Population population = new Population(initPaths, graph);
    	
		
		
    	
    	
    	
    	
    }
}
