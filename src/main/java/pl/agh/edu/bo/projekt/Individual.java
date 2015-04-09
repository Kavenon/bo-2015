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
		Graph<Integer, String> graph = ourGraph.getGraph();
		ArrayList<Integer> chromosome = new ArrayList<Integer>();
		
		ArrayList<Integer> lst = new ArrayList<Integer>();
		

		for(int i = 0; i < graph.getVertexCount(); i++){
			lst.add(i);	
		}
		
		for(int i = 0; i < graph.getVertexCount(); i++){
			int index = random.nextInt(lst.size());			
            int item = lst.get(index);            
            chromosome.add(item);
            lst.remove(index);	
            
            System.out.print(item+ " ");
		}
		System.out.println();
	}

	public void evaluate(){
		
	}
}
