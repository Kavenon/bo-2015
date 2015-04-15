package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;

/**
 * Class that represents single path in graph.
 */
public class CopyOfIndividual {

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
		Graph<Integer, String> graph = ourGraph.getGraph();
		ArrayList<Integer> chromosome = new ArrayList<Integer>();

		ArrayList<Integer> lst = new ArrayList<Integer>();

		for (int i = 0; i < graph.getVertexCount(); i++) {
			lst.add(i);
		}
		
		

		// rand.nextInt((max - min) + 1) + min;
		int randomPathLength = random.nextInt((graph.getVertexCount() - 2) + 1) + 2;
		if (randomPathLength > 0) {
			for (int i = 0; i < randomPathLength; i++) {
				int index = random.nextInt(lst.size());
				int item = lst.get(index);
				chromosome.add(item);
				lst.remove(index);

			}

			if (chromosome.size() >= 2) {
				if (!chromosome.get(chromosome.size() - 1).equals(
						chromosome.get(0))) {
					chromosome.add(chromosome.get(0));
				}
			}

		}

		for (Integer i : chromosome) {
			System.out.print(i + " ");
		}
		System.out.println();

		// sprawdz czy pierwszy jest tez ostatnim, a jak nie to dopisz go na
		// koncu
	}

	public void evaluate() {
		// dodac krawedzie i je policzyc
	}
}
