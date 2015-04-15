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

	public void create(OurGraph ourGraph) {
		Random random = new Random();

		Graph<Vertex, String> graph = ourGraph.getGraph();
		path = new ArrayList<Vertex>();

		ArrayList<Vertex> lst = new ArrayList<Vertex>(graph.getVertices());

		int maxPathLength = (Constants.MAX_PATH_LENGTH==0) ? graph.getVertexCount() : Constants.MAX_PATH_LENGTH;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Vertex i : path) {
			sb.append(i.getId() + " ");
		}

		sb.append("length:" + this.length);

		return sb.toString();
	}

	public void debug() {
		if(Constants.ENV == 0) System.out.println(this);
	}
}
