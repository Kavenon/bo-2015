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
		
		// Stop condition, maximum number of iterations
		int iterations = 0;
		while(iterations < Constants.MAX_ITERATIONS){
			
			
			for (Individual indiv : population.individuals) {
				indiv.evaluate();			  
			}
			

			System.out.println("====== START ======");
			System.out.println(population);
			
			Population newPopulation = new Population();		
			
			// for tests
			newPopulation = population;
			// crossover
	        /*for (int i = 0; i < population.individuals.size(); i++) {
	        	Individual indiv1 = population.tournamentSelection();
				Individual indiv2 = population.tournamentSelection();
	            //Individual newIndiv = Individual.crossover(indiv1, indiv2);
	            newPopulation.individuals.add(indiv1);
	        }*/
			

			System.out.println("====== AFTER CROSS BEFORE MUTATION======");
			System.out.println(newPopulation);
	    
	        
	        // mutate 
	        for (int i = 0; i < newPopulation.individuals.size(); i++) {
	        	 if (Math.random() <= Constants.MUTATION_RATE) {
	        		 	System.err.println("before: (" +graph.getGraph().getVertexCount() +") " + newPopulation.individuals.get(i));
	        		 	newPopulation.individuals.get(i).mutate(graph);
	        		 	newPopulation.individuals.get(i).evaluate();
	        			System.err.println("after :" + Integer.toHexString(newPopulation.individuals.get(i).hashCode()) + " "+ newPopulation.individuals.get(i));
	        	 }
	        }
	        

			System.out.println("====== AFTER MUTATION ======");
			System.out.println(newPopulation);
			
	        population = newPopulation;
	        //System.out.println("iteration:" + iterations + "\n" + newPopulation);
	        
			iterations++;
		}
		
		

	}
}
