/*
 * 
 */
package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

	public static void main(String[] args) {

		Random random = new Random();

        OurGraph graph = createRandomGraph(random);

        Population population = createInitialPopulation(random, graph);

		// warunkiem końcowym jest ilość iteracji
		int iterations = 0;
		while (iterations < Constants.MAX_ITERATIONS) {

			// ocena osobnikow
            evaluatePopulation(population);

            if (Constants.ENV == 0) {
				System.out.println("====== START ======");
				System.out.println(population);
			}

			Population newPopulation = new Population();

			// krzyzowanie
			try {
				for (int i = 0; i < population.individuals.size(); i++) {
                    Individual indiv1 = population.tournamentSelection();
                    Individual indiv2 = population.tournamentSelection();
                    if (Math.random() <= Constants.CROSSOVER_RATE) {
                        // szukamy metoda turniejową 2 najlepszych osobnikow


                        // pobieramy ich sciezki
                        ArrayList<Vertex> lst1 = new ArrayList<Vertex>(indiv1.path);
                        ArrayList<Vertex> lst2 = new ArrayList<Vertex>(indiv2.path);

                        // krzyzowanie 2 podanych sciezek
                        List<Individual> newIndiv = Individual.crossoverNew(lst1, lst2);

                        // dodaj nowa sciezke do nowej populacji
                        if(newIndiv.get(0).validatePath(newIndiv.get(0).path, graph.getGraph())){
                            newPopulation.individuals.add(newIndiv.get(0));
                            System.err.println("cross :))");
                        }
                        else {
                            newPopulation.individuals.add(indiv1);
                            System.err.println("cross fail :/");
                        }

                       // newPopulation.individuals.add(newIndiv.get(1));
                    }
                    else {
                        newPopulation.individuals.add(indiv1);
                        System.err.println("cross skip :/");
                    }


				}

			} catch (Exception e) {
				System.err.println("Wrong configuration");
			}

			if (Constants.ENV == 0) {
				System.out.println("====== AFTER CROSS BEFORE MUTATION======");
				System.out.println(newPopulation + "graph: " + graph.getGraph().getVertexCount());
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
        evaluatePopulation(population);

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

    private static void evaluatePopulation(Population population) {
        for (Individual indiv : population.individuals) {
            indiv.evaluate();
        }
    }

    private static Population createInitialPopulation(Random random, OurGraph graph) {
        int randomInitPathNumber = random
                .nextInt((Constants.MAX_INIT_PATH - Constants.MIN_INIT_PATH) + 1)
                + Constants.MIN_INIT_PATH;

        return new Population(randomInitPathNumber, graph);
    }

    protected static OurGraph createRandomGraph(Random random) {
        int randomGraphVerticlesNumber = random
                .nextInt((Constants.MAX_GRAPH_VERTICLES - Constants.MIN_GRAPH_VERTICLES) + 1)
                + Constants.MIN_GRAPH_VERTICLES;

        OurGraph graph = new OurGraph(randomGraphVerticlesNumber);
        graph.generateRandomGraph();
        return graph;
    }
}
