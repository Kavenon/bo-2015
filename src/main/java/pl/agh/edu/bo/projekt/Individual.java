package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;

/**
 * Class that represents single path in graph.
 */
public class Individual {

	/**
	 * path length
	 */
	int rating;

	Vertex[] path;

	public Vertex[] getPath() {
		return path;
	}

	public void setPath(Vertex[] path) {
		this.path = path;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void create(OurGraph ourGraph) {
		Random random = new Random();

		while (true) {
			Graph<Vertex, String> graph = ourGraph.getGraph();

			int vertexCount = graph.getVertexCount();
			int possibleToSearch = random.nextInt(Integer.MAX_VALUE)
					% vertexCount;

			ArrayList<Vertex> possibleToVisit = new ArrayList<Vertex>();

			for (int i = 0; i <= possibleToSearch; i++) {
				Vertex v = ourGraph.getVertexById(random.nextInt(vertexCount));
				if (!possibleToVisit.contains(v))
					possibleToVisit.add(v);
			}
		}

		// Vertex firstVertex = ourGraph.getVertexById(firstVertexId);
		// Vertex currentVertex = firstVertex;

		// losuj wierzcholek poczatkowy
		// losuj ile wierzcholkow odwiedzic

	}

}
