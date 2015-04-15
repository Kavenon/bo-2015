package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;

/**
 * Class that represents single path in graph.
 */
public class Individual {

	double length;

	ArrayList<Vertex> path;
	
	public Individual(){
		path = new ArrayList<Vertex>();
	}

	public void create(OurGraph ourGraph) {
		Random random = new Random();

		Graph<Vertex, String> graph = ourGraph.getGraph();
		path = new ArrayList<Vertex>();

		ArrayList<Vertex> lst = new ArrayList<Vertex>(graph.getVertices());

		int maxPathLength = (Constants.MAX_PATH_LENGTH == 0) ? graph
				.getVertexCount() : Constants.MAX_PATH_LENGTH;
		int minPathLength = Constants.MIN_PATH_LENGTH;

		int randomPathLength = random
				.nextInt((maxPathLength - minPathLength) + 1) + minPathLength;
		if (randomPathLength > 0) {
			for (int i = 0; i < randomPathLength; i++) {
				int index = random.nextInt(lst.size());
				Vertex item = lst.get(index);
				path.add(item);
				lst.remove(index);
			}

			if (path.size() >= 2) {
				if (!path.get(path.size() - 1).equals(path.get(0))) {
					path.add(path.get(0));
				}
			}

		}

	}

	// count path length
	public void evaluate() {
		length = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			length += Vertex.distance(path.get(i), path.get(i + 1));
		}
	}

	public static Individual crossover(Individual indiv1, Individual indiv2) {

		Individual newIndividual = new Individual();
		ArrayList<Vertex> lst1 = new ArrayList<Vertex>(indiv1.path);
		ArrayList<Vertex> lst2 = new ArrayList<Vertex>(indiv2.path);
		// Loop through genes
		for (int i = 0; i < indiv1.path.size() + indiv2.path.size(); i++) {
			// Crossover
			if ((lst2.size() > 0 && Math.random() <= Constants.CROSSOVER_RATE) || lst2.size() == 0) {
				Vertex firstFromIndiv1 = lst1.get(0);
				if (!newIndividual.path.contains(firstFromIndiv1)) {
					newIndividual.path.add(firstFromIndiv1);			
				}
				
			} else {
				Vertex firstFromList = lst2.get(0);			
				if (!newIndividual.path.contains(firstFromList)) {					
					newIndividual.path.add(firstFromList);
					lst2.remove(firstFromList);
				}

			}
		}

		if (newIndividual.path.size() >= 2) {
			if (!newIndividual.path.get(newIndividual.path.size() - 1).equals(
					newIndividual.path.get(0))) {
				newIndividual.path.add(newIndividual.path.get(0));
			}
		}
		 System.out.println("asfadfadfadfd");
		return newIndividual;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Vertex i : path) {
			sb.append(i.getId() + " ");
		}

		sb.append("length:" + this.length);

		return sb.toString();
	}

	public void debug() {
		if (Constants.ENV == 0)
			System.out.println(this);
	}
}
