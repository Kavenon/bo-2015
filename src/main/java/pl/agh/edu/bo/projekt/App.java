/*
 * 
 */
package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.Random;

public class App {

	public static void main(String[] args) {

		Random random = new Random();

		// tworzymy graf o losowej losci wierzcholkow (max,min)
		int randomGraphVerticlesNumber = random
				.nextInt((Constants.MAX_GRAPH_VERTICLES - Constants.MIN_GRAPH_VERTICLES) + 1)
				+ Constants.MIN_GRAPH_VERTICLES;

		OurGraph graph = new OurGraph(randomGraphVerticlesNumber);
		graph.generateRandomGraph();

		// tworzymy populację o losowej ilości, ścieżek (Individual)
		int randomInitPathNumber = random
				.nextInt((Constants.MAX_INIT_PATH - Constants.MIN_INIT_PATH) + 1)
				+ Constants.MIN_INIT_PATH;

		Population population = new Population(randomInitPathNumber, graph);

		// warunkiem końcowym jest ilość iteracji
		int iterations = 0;
		while (iterations < Constants.MAX_ITERATIONS) {

			// ocena osobnikow
			for (Individual indiv : population.individuals) {
				indiv.evaluate();
			}

			if (Constants.ENV == 0) {
				System.out.println("====== START ======");
				System.out.println(population);
			}

			Population newPopulation = new Population();

			// krzyzowanie
			try {
				for (int i = 0; i < population.individuals.size(); i++) {
					// szukamy metoda turniejową 2 najlepszych osobnikow
					Individual indiv1 = population.tournamentSelection();
					Individual indiv2 = population.tournamentSelection();

					// pobieramy ich sciezki
					ArrayList<Vertex> lst1 = new ArrayList<Vertex>(indiv1.path);
					ArrayList<Vertex> lst2 = new ArrayList<Vertex>(indiv2.path);

					// krzyzowanie 2 podanych sciezek
					Individual newIndiv = Individual.crossover(lst1, lst2);

					// dodaj nowa sciezke do nowej populacji
					newPopulation.individuals.add(newIndiv);
				}

			} catch (Exception e) {
				System.err.println("Wrong configuration");
			}

			if (Constants.ENV == 0) {
				System.out.println("====== AFTER CROSS BEFORE MUTATION======");
				System.out.println(newPopulation);
			}

			// mutowanie
			for (int i = 0; i < newPopulation.individuals.size(); i++) {
				if (Math.random() <= Constants.MUTATION_RATE) {
					newPopulation.individuals.get(i).mutate(graph);
				}
			}

			if (Constants.ENV == 0) {
				System.out.println("====== AFTER MUTATION ======");
				System.out.println(newPopulation);
			}

			population = newPopulation;
			iterations++;
		}


		// oblicz dlugosc tras
		for (Individual indiv : population.individuals) {
			indiv.evaluate();
		}
		
		// znajdz najlepsza
		Individual bestIndividual = population.getBestIndividual();

		// utworz krawedzie
		graph.createEdges(bestIndividual);

		// wyswietl graf
		graph.printGraph();
		

		if (Constants.ENV == 0) {
			System.out.println("========= FINAL POPULATION ===========");
			System.out.println(population);
		}

	}
}
