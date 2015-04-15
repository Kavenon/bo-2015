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
		
		/*try {
			Individual indiv1 = population.tournamentSelection();
			Individual indiv2 = population.tournamentSelection();
			System.out.println("\n best" + indiv1);
			System.out.println("\n best" + indiv2);
			
			System.out.println("\n cross: " + Individual.crossover(indiv1, indiv2));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		// Stop condition, maximum number of iterations
		int iterations = 0;
		while(iterations < Constants.MAX_ITERATIONS){
			
			Population newPopulation = new Population();		
			
			// crossover
	        for (int i = 0; i < population.individuals.size(); i++) {
	        	Individual indiv1 = population.tournamentSelection();
				Individual indiv2 = population.tournamentSelection();
	            //Individual newIndiv = Individual.crossover(indiv1, indiv2);
	            newPopulation.individuals.add(indiv1);
	        }
			
	        System.out.println("iteration:" + iterations + "\n" + newPopulation);
	        
	        // mutate 
	        /*for (int i = elitismOffset; i < newPopulation.size(); i++) {
	            mutate(newPopulation.getIndividual(i));
	        }*/
	        
	        population = newPopulation;
			
			iterations++;
		}
		
		

	}
}
