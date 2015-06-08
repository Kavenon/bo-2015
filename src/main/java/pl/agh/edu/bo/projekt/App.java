/*
 * 
 */
package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App implements Runnable {

    double crossoverProbability ;
    double mutationProbability ;
    int maxIterations ;
    int initPathLength ;
    int populationSize ;
    OurGraph ourGraph;

    private Population population;
    Individual bestIndividual;

    boolean running = false;

    public App() {
    }

    @Override
    public void run() {
        main();
    }

	public void main() {

		Random random = new Random();
        int mutationCounter = 0;
        int crossoverCounter = 0;


        population = createInitialPopulation(random, ourGraph,populationSize,initPathLength);

		// warunkiem końcowym jest ilość iteracji
		int iterations = 0;
        long start = System.currentTimeMillis();
		while (running && iterations < maxIterations) {

			// ocena osobnikow
            evaluatePopulation(population);
            bestIndividual = population.getBestIndividual();

           if(population.getBestIndividual() != null && population.getBestIndividual().path.size() >= ourGraph.getGraph().getVertexCount()+1){
                running = false;
                break;
            }

            Population newPopulation = new Population();

			// krzyzowanie
            for (int i = 0; i < population.individuals.size(); i++) {
                // szukamy metoda turniejową 2 najlepszych osobnikow
                Individual indiv1 = population.tournamentSelection();
                Individual indiv2 = population.tournamentSelection();
                if (Math.random() <= crossoverProbability) {

                    // pobieramy ich sciezki
                    ArrayList<Vertex> lst1 = new ArrayList<Vertex>(indiv1.path);
                    ArrayList<Vertex> lst2 = new ArrayList<Vertex>(indiv2.path);

                    // krzyzowanie 2 podanych sciezek
                    List<Individual> newIndiv = Individual.crossoverNew(lst1, lst2);

                    // dodaj nowa sciezke do nowej populacji
                    if(newIndiv.get(0).validatePath(newIndiv.get(0).path, ourGraph.getGraph())){
                        newPopulation.individuals.add(newIndiv.get(0));
                        crossoverCounter++;
                     //   System.err.println("cross :))");
                    }
                    else {
                        // sciezka po krzyzowaniu jest niepoprawna
                        newPopulation.individuals.add(indiv1);
                    }
                }
                else {
                    newPopulation.individuals.add(indiv1);
                }
            }


			// mutowanie
			for (int i = 0; i < newPopulation.individuals.size(); i++) {
				if (Math.random() <= mutationProbability) {
					newPopulation.individuals.get(i).mutate(ourGraph);
                    mutationCounter++;
				}
			}

			population = newPopulation;

			iterations++;
		}

        running = false;
        evaluatePopulation(population);



        AlgorithmGUI.logTextArea.append("========= Najlepszy osobnik ===========\n");
        AlgorithmGUI.logTextArea.append(""+ population.getBestIndividual() + "\n");
        AlgorithmGUI.logTextArea.append("Krzyżowań: "+ crossoverCounter + "\n");
        AlgorithmGUI.logTextArea.append("Mutacji: "+ mutationCounter + "\n");
        AlgorithmGUI.logTextArea.append("Czas: " + (System.currentTimeMillis() - start) + "ms\n");

        return;

	}

    private static void evaluatePopulation(Population population) {
        for (Individual indiv : population.individuals) {
            indiv.evaluate();
        }
    }

    private static Population createInitialPopulation(Random random, OurGraph graph, int populationSize, int initPathLength) {

        return new Population(populationSize,initPathLength, graph);
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
