package pl.agh.edu.bo.projekt;

import java.util.Collection;
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

		//while (true) {
			Graph<Integer, String> graph = ourGraph.getGraph();

			int vertexCount = graph.getVertexCount();
			int firstVertexId = random.nextInt(Integer.MAX_VALUE)
					% vertexCount;
			
			Vertex firstVertex = graph.(firstVertexId);
			
			while(firstVertex == n)
			Collection<Vertex> neighborsList = graph.getNeighbors(firstVertex);
			int neighborCount = graph.getNeighborCount(firstVertex);
			
			if(neighborCount > 0){
				Vertex neighbor;
				for(Vertex v : neighborsList){
					if(Math.random() < 0.1){
						neighbor = v;
						break;
					}
				}	
				if(neighbor == null){
					neighbor = neighborsList.iterator().next();
				}				
			}
			
			
			
			
		
			 ourGraph.getVertexById(randomNeighorId);

			
			
			// losowa lista wierzcholkow dla ktorych sprawdzimy trase
			/*ArrayList<Vertex> possibleToVisit = new ArrayList<Vertex>();

			for (int i = 0; i <= possibleToSearch; i++) {
				Vertex v = ourGraph.getVertexById(random.nextInt(vertexCount));
				if (!possibleToVisit.contains(v))
					possibleToVisit.add(v);
			}
			
			graph.*/
			
	//	}

		// Vertex firstVertex = ourGraph.getVertexById(firstVertexId);
		// Vertex currentVertex = firstVertex;

		// losuj wierzcholek poczatkowy
		// losuj ile wierzcholkow odwiedzic

	}

}
