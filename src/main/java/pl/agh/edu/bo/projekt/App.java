package pl.agh.edu.bo.projekt;

import java.util.Random;

public class App {
	
	public static void main(String[] args) {

		Random random = new Random();

		// CREATE RANDOM GRAPH
		int randomGraphVerticlesNumber = random
				.nextInt((Constants.MAX_GRAPH_VERTICLES - Constants.MIN_GRAPH_VERTICLES) + 1)
				+ Constants.MIN_GRAPH_VERTICLES;
		OurGraph graph = new OurGraph(randomGraphVerticlesNumber);
		graph.generateRandomGraph();

		// Init population
		int randomInitPathNumber = random
				.nextInt((Constants.MAX_INIT_PATH - Constants.MIN_INIT_PATH) + 1)
				+ Constants.MIN_INIT_PATH;

		Population population = new Population(randomInitPathNumber, graph);		
		
		try {
			System.out.println("\n best" + population.tournamentSelection());
		}
		catch(Exception e){
			
		}
		// Stop condition, maximum number of iterations
		int iterations = 0;
		while(iterations < Constants.MAX_ITERATIONS){
			
			
			
			iterations++;
		}
		
		

	}
}
